package com.plcoding.bookpedia

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.book.presentation.booklist.BookListScreen
import com.plcoding.bookpedia.book.presentation.booklist.BookListState
import com.plcoding.bookpedia.book.presentation.booklist.components.BookListItem
import com.plcoding.bookpedia.book.presentation.booklist.components.BookSearchBar

@Preview
@Composable
private fun BookSearchBarPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        BookSearchBar(
            searchQuery = "",
            onSearchQueryChange = {},
            onImeSearch = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}

//private val books = (1.. 100).map {
//    Book(
//        id = it.toString(),
//        title = "Book $it",
//        imageUrl = "https://test.com",
//        authors = listOf("Thiep"),
//        description = "Description: $it",
//        languages = emptyList(),
//        firstPublishYear = null,
//        averageRating = 4.5,
//        ratingCount = 4,
//        numPages = 100,
//        numEditions = 3,
//
//    )
//}

//@Preview
//@Composable
//private fun BookListScreenPreview() {
//    BookListScreen(
//        state = BookListState(
//            searchResult = books,
//        ),
//        onAction = {}
//    )
//}