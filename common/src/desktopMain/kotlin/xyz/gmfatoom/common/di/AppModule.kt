package xyz.gmfatoom.common.di

import xyz.gmfatoom.common.core.data.DatabaseDriverFactory
import xyz.gmfatoom.common.database.reQuestikDatabase
import xyz.gmfatoom.common.requestik.data.local.SqlDelightRequestsDataSource
import xyz.gmfatoom.common.requestik.domain.RequestikDataSource

actual class AppModule(
) {

    actual val requestikDataSource: RequestikDataSource by lazy {
        SqlDelightRequestsDataSource(
            db = reQuestikDatabase(
                driver = DatabaseDriverFactory().create(),
            )
        )
    }
}