package xyz.gmfatoom.common.core.data


import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import xyz.gmfatoom.common.database.reQuestikDatabase

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(reQuestikDatabase.Schema, "requestik.db")
    }
}