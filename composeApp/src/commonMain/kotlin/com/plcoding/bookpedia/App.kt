package com.plcoding.bookpedia

import androidx.compose.runtime.*
import com.plcoding.bookpedia.book.data.network.KtorRemoteBookDataSource
import com.plcoding.bookpedia.book.data.repository.DefaultRepository
import com.plcoding.bookpedia.book.presentation.booklist.BookListScreenRoot
import com.plcoding.bookpedia.book.presentation.booklist.BookListViewModel
import com.plcoding.bookpedia.core.data.HttpClientFactory
import io.ktor.client.engine.*
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * 🧐
 * Dùng remember để giữ lại một instance của BookListViewModel trong suốt vòng đời của composable.
 * Tránh việc khởi tạo lại BookListViewModel() khi UI recompose.
 */

@Composable
@Preview
fun App(engine: HttpClientEngine) {
    BookListScreenRoot(

        viewModel = remember {  BookListViewModel(
            bookRepository = DefaultRepository(
                remoteBookDataSource = KtorRemoteBookDataSource(
                    httpClient = HttpClientFactory.create(
                        engine = engine
                    )
                )
            )
        ) },
        onBookClick =  {}
    )
}