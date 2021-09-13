package ru.rinet.questik.data.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ru.rinet.questik.data.room.models.Corp;
import ru.rinet.questik.data.room.models.Metrics;

@Dao
public interface MetricsDao {
    @Query("SELECT * FROM Metrics")
    List<Metrics> getAll();

    //запрос на каегорию по его id
    @Query("SELECT * FROM Metrics WHERE metrics_id = :id")
    Metrics getById(Long id);

    @Query("SELECT * FROM Metrics")
    Metrics getMetrics();

    @Query("SELECT * FROM Metrics")
    List<Metrics> getMetricsList();

    //Wipe
    @Query("DELETE FROM Metrics")
    void wipeMetricsTable();

    @Insert
    Long insert(Metrics metrics);

    @Insert
    List<Long> insertMetricsList(List<Metrics> metricsList);

    @Update
    int update(Metrics metrics);

    @Delete
    int delete(Metrics metrics);

}
