package xyz.gmfatoom.questik.repo.local.room.dao

import androidx.paging.PagingSource
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import xyz.gmfatoom.questik.repo.local.room.entity.ContactsEntity

@Dao
interface ContactsDao {
    @Query("SELECT COUNT(contact_id) FROM contacts")
    fun getContactsCount(): Int
    @Query("SELECT COUNT(contact_id) FROM contacts")
    fun getContactsCountFlow(): Flow<Int>


/*    @Query("SELECT * FROM contacts")
    fun getAllContacts(): PagingSource<Int, ContactsEntity>*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllContacts(items:List<ContactsEntity>)

    @Query("DELETE FROM contacts")
    fun clearContacts()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContacts(contacts: ContactsEntity):Long

    @Update
    fun updateContacts(contacts: ContactsEntity)

    @Delete
    fun deleteContacts(contacts: ContactsEntity)

    @Query("SELECT * FROM contacts WHERE contact_id == :id")
    fun getContactsById(id: Int): ContactsEntity

    @Query("SELECT * FROM contacts")
    fun getContacts(): List<ContactsEntity>




}