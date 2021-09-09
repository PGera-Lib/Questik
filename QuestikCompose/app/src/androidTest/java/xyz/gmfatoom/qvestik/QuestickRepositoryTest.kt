package xyz.gmfatoom.qvestik

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import xyz.gmfatoom.questik.repo.local.room.AppDatabase
import xyz.gmfatoom.questik.repo.local.room.dao.RequestItemsDao
import xyz.gmfatoom.questik.repo.local.room.entity.RequestItemEntity
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class QuestickRepositoryTest {

    private lateinit var requestDao: RequestItemsDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        requestDao = db.requestsItemsDao()
    }

    @After
    @Throws(IOException::class)
    fun deleteDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetTodo() = runBlocking {
        val chernovikEntity = RequestItemEntity(id = 1, name = "TestName", categoryId = "1",    jobId = "1",     materialId = "1",     metricsId= "1",     itemCount = "1",     itemPrice = "1",     plu = "001",     isChecked = false,     isMaterial = false)
        CoroutineScope(Dispatchers.IO).launch {
            requestDao.insertRequestItem(chernovikEntity)
            val oneItem = requestDao.getRequestItemById(1)
            assertEquals(oneItem?.id, 1L)
        }
    }
}
