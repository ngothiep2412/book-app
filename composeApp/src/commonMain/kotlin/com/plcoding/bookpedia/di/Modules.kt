package com.plcoding.bookpedia.di

import com.plcoding.bookpedia.book.data.network.KtorRemoteBookDataSource
import com.plcoding.bookpedia.book.data.network.RemoteBookDataSource
import com.plcoding.bookpedia.book.presentation.book_list.BookListViewModel
import com.plcoding.bookpedia.book.data.repository.DefaultRepository
import com.plcoding.bookpedia.book.domain.BookRepository
import com.plcoding.bookpedia.book.presentation.SelectedBookViewModel
import com.plcoding.bookpedia.book.presentation.book_detail.BookDetailViewModel
import com.plcoding.bookpedia.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>():
 *
 * singleOf(::KtorRemoteBookDataSource): Đây là cách khác để khai báo một singleton trong Koin, trong đó ::KtorRemoteBookDataSource là constructor reference của lớp KtorRemoteBookDataSource.
 *
 * bind<RemoteBookDataSource>(): Sau khi tạo instance của KtorRemoteBookDataSource, bạn ràng buộc nó với interface RemoteBookDataSource. Điều này có nghĩa là khi bạn yêu cầu RemoteBookDataSource, Koin sẽ cung cấp instance của KtorRemoteBookDataSource.
 *
 */
expect val platformModule: Module

val sharedModule = module {
    single {HttpClientFactory.create(get())}

//    single {
//        KtorRemoteBookDataSource(get())
//    }
    singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()
    singleOf(::DefaultRepository).bind<BookRepository>()

    viewModelOf(::BookListViewModel)
    viewModelOf(::BookDetailViewModel)
    viewModelOf(::SelectedBookViewModel)
}