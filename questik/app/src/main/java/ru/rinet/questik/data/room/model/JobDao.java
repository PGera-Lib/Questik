package ru.rinet.questik.data.room.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface JobDao {

    @Query("SELECT * FROM job")
    LiveData<List<Job>> getAll();
    //List<Job> getAll();
    @Query("SELECT job.name, job.price, job.count ,category.name AS category_name, metrics.name AS metrics_name, project.name AS project_name " +
            "FROM job, category, metrics, project " +
            "WHERE category._id == job.category_id AND metrics._id == job.metrics_id AND project._id == job.project_id" )
    LiveData<List<JobFull>> getJobFull();

    @Query("SELECT * FROM job WHERE job_id = :id")
    Job getById(int id);

    @Query("SELECT * FROM job WHERE project_id IN (:jobByProject)")
    LiveData<List<Job>> getJobByProjectId(int jobByProject);

    @Insert
    void insert(Job job);

    @Update
    void update(Job job);

    @Delete
    void delete(Job job);

}
