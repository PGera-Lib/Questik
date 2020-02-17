package ru.rinet.questik.data.room.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import org.joda.time.LocalDate;

import java.util.List;

@Dao
public interface ProjectDao {
    @Query("SELECT * FROM project")
    LiveData<List<Project>> getAll();

    @Query("SELECT * FROM project WHERE _id = :id")
    Project getById(int id);

    @Transaction
    @Query("SELECT _id, name from project")
    LiveData<List<ProjectWithJob>> getProjectWithJob();

    @Query("SELECT _id, name FROM project")
    LiveData<List<ProjectWithUser>> getProjectWithUser();

    @Query("SELECT * FROM project WHERE startDate=:date")
    LiveData<List<Project>> getProjectForDate(final LocalDate date);

    @Query("SELECT * FROM project WHERE startDate=:date ORDER BY name")
    List<Project> queryTasksForDate(final LocalDate date);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Project project);


    @Update
    void update(Project project);

    @Delete
    void delete(Project project);




}
