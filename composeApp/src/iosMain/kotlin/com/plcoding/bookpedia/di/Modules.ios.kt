package com.plcoding.bookpedia.di

import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>():
 *
 * singleOf(::KtorRemoteBookDataSource): Đây là cách khác để khai báo một singleton trong Koin, trong đó ::KtorRemoteBookDataSource là constructor reference của lớp KtorRemoteBookDataSource.
 *
 * bind<RemoteBookDataSource>(): Sau khi tạo instance của KtorRemoteBookDataSource, bạn ràng buộc nó với interface RemoteBookDataSource. Điều này có nghĩa là khi bạn yêu cầu RemoteBookDataSource, Koin sẽ cung cấp instance của KtorRemoteBookDataSource.
 *
 */
actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> {Darwin.create()}
    }