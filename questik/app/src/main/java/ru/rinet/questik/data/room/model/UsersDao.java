package ru.rinet.questik.data.room.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UsersDao {
    @Query("SELECT * FROM users")
    LiveData<List<Users>> getll();

    @Query("SELECT * FROM users WHERE _id = :id")
    Users getById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Metrics metrics);

    @Update
    void update(Metrics metrics);

    @Delete
    void delete(Metrics metrics);
}
