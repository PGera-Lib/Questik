package xyz.gmfatoom.questik.repo.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import xyz.gmfatoom.questik.repo.local.room.entity.RemoteKeys


@Dao
interface RemoteKeysDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKeys: List<RemoteKeys>)
    @Query("SELECT * FROM remote_keys WHERE id = :id")
    suspend fun remoteKeysId(id:Int):RemoteKeys?
    @Query("DELETE FROM remote_keys")
     fun clearKeys()
}