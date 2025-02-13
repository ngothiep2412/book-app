package com.plcoding.bookpedia

import androidx.compose.runtime.*
import com.plcoding.bookpedia.book.presentation.booklist.BookListScreenRoot
import com.plcoding.bookpedia.book.presentation.booklist.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * 🧐
 * Dùng remember để giữ lại một instance của BookListViewModel trong suốt vòng đời của composable.
 * Tránh việc khởi tạo lại BookListViewModel() khi UI recompose.
 */

@Composable
@Preview
fun App() {
    BookListScreenRoot(

        viewModel = remember {  BookListViewModel() },
        onBookClick =  {}
    )
}