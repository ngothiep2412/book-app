package com.plcoding.bookpedia

import androidx.compose.runtime.*
import com.plcoding.bookpedia.book.presentation.booklist.BookListScreenRoot
import com.plcoding.bookpedia.book.presentation.booklist.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    BookListScreenRoot(
        viewModel = remember {  BookListViewModel() },
        onBookClick =  {}
    )
}