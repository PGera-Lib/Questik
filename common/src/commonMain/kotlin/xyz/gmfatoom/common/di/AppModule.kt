package xyz.gmfatoom.common.di

import xyz.gmfatoom.common.requestik.domain.RequestikDataSource


expect class AppModule {
    val requestikDataSource: RequestikDataSource
}