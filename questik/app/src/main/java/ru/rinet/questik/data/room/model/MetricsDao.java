package ru.rinet.questik.data.room.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MetricsDao {
    @Transaction
    @Query("SELECT _id, name FROM metrics")
    List<MetricsWithJob> getMetricsWithJob();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Metrics metrics);

    @Update
    void update(Metrics metrics);

    @Delete
    void delete(Metrics metrics);
}
