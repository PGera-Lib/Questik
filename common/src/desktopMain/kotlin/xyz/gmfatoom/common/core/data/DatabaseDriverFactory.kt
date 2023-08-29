package xyz.gmfatoom.common.core.data

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import xyz.gmfatoom.common.database.reQuestikDatabase

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        reQuestikDatabase.Schema.create(driver)
        return driver
    }
}

