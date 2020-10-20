package ru.rinet.questik.data.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import ru.rinet.questik.data.room.models.CategoryWithJob;
import ru.rinet.questik.data.room.models.Departament;
import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.data.room.models.ProjectWithJobs;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface JobDao {
    //запрос всей таблицы
    @Query("SELECT * FROM Job")
    List<Job> getAll();

    //запрос на каегорию по его id
    @Query("SELECT * FROM Job WHERE job_id = :id")
    Job getById(Long id);

    @Query("SELECT * FROM Job")
    Job getJob();

    @Query("SELECT * FROM Job")
    List<Job> getJobList();

    @Query("SELECT COUNT(job_id) FROM Job")
    Long getRecordsCount();

/*    @Transaction
    @Query("SELECT * FROM Job WHERE mCategory = :id")
    public abstract List<CategoryWithJob> getJobWithCategory(Long id);

    @Transaction
    @Query("SELECT * FROM Job WHERE mProjectId = :id")
    public abstract List<ProjectWithJobs> getJobWithProject(Long id);*/
    //Wipe
    @Query("DELETE FROM Job")
    void wipeJobTable();

   @Insert(onConflict = REPLACE)
    Long insert(Job job);

    @Insert
    List<Long> insertJobList(List<Job> jobList);

    @Update
    int update(Job job);

    @Delete
    int delete(Job job);

}