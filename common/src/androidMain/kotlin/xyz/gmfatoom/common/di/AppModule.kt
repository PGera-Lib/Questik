package xyz.gmfatoom.common.di

import android.content.Context
import xyz.gmfatoom.common.core.data.DatabaseDriverFactory
import xyz.gmfatoom.common.database.reQuestikDatabase
import xyz.gmfatoom.common.requestik.data.local.SqlDelightRequestsDataSource
import xyz.gmfatoom.common.requestik.domain.RequestikDataSource

actual class AppModule(
    private val context: Context
) {

    actual val requestikDataSource: RequestikDataSource by lazy {
        SqlDelightRequestsDataSource(
            db = reQuestikDatabase(
                driver = DatabaseDriverFactory(context).create(),
            )
        )
    }
}