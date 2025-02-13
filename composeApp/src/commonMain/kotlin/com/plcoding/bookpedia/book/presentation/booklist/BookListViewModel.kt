package com.plcoding.bookpedia.book.presentation.booklist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * MutableStateFlow: Là dạng có thể thay đổi giá trị và được sử dụng bên trong ViewModel hoặc bất kỳ lớp nào cần thay đổi trạng thái.
 *
 * StateFlow: Là một dạng chỉ đọc, thường được cung cấp cho UI hoặc các thành phần bên ngoài để theo dõi giá trị mà không thể thay đổi.
 */

class BookListViewModel: ViewModel() {
    private val _state = MutableStateFlow(BookListState())
    val state = _state.asStateFlow()

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
}