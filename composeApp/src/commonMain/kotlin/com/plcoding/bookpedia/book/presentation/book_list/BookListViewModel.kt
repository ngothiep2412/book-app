package com.plcoding.bookpedia.book.presentation.book_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.book.domain.BookRepository
import com.plcoding.bookpedia.core.domain.onError
import com.plcoding.bookpedia.core.domain.onSuccess
import com.plcoding.bookpedia.core.presentation.toUIText
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * MutableStateFlow: Là dạng có thể thay đổi giá trị và được sử dụng bên trong ViewModel hoặc bất kỳ lớp nào cần thay đổi trạng thái.
 *
 * StateFlow: Là một dạng chỉ đọc, thường được cung cấp cho UI hoặc các thành phần bên ngoài để theo dõi giá trị mà không thể thay đổi.
 */

class BookListViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {
    private val _state = MutableStateFlow(BookListState())
    val state = _state
        .onStart {
            if (cachedBooks.isEmpty()) {
                observeSearchQuery()
            }
            observeFavoriteBooks()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )


    private var cachedBooks = emptyList<Book>()
    private var searchJob: Job? = null
    private var favoriteJob: Job? = null

    fun onAction(action: BookListAction) {
        when (action) {
            is BookListAction.OnBookClick -> {

            }

            is BookListAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(searchQuery = action.query)
                }
            }

            is BookListAction.OnTabSelected -> {
                _state.update {
                    it.copy(selectedTabIndex = action.index)
                }
            }
        }
    }

    private fun observeFavoriteBooks() {
        favoriteJob?.cancel()

        favoriteJob =  bookRepository
            .getFavoriteBooks()
            .onEach { favoriteBooks ->
                _state.update {
                    it.copy(
                        favoriteBooks = favoriteBooks
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        state.map {
            it.searchQuery
        }
            .distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                when {
                    query.isBlank() -> {
                        _state.update {
                            it.copy(
                                errorMessage = null,
                                searchResults = cachedBooks
                            )
                        }
                    }

                    query.length >= 2 -> {
                        searchJob?.cancel()
                        searchJob = searchBooks(query)
                    }
                }
            }
            // Bắt đầu Flow trong viewModelScope để chạy trong vòng đời của ViewModel.
            .launchIn(viewModelScope)
    }

    private fun searchBooks(query: String) =
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            bookRepository
                .searchBooks(query)
                .onSuccess { searchResults ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = null,
                            searchResults = searchResults
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.toUIText(),
                            searchResults = emptyList()
                        )
                    }
                }
        }

}