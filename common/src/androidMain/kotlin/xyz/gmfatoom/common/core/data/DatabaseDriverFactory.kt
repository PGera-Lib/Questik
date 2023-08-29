package xyz.gmfatoom.common.core.data

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import xyz.gmfatoom.common.database.reQuestikDatabase

actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(
            reQuestikDatabase.Schema,
            context,
            "requestik.db"
        )
    }
}