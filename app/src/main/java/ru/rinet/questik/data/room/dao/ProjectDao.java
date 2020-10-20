package ru.rinet.questik.data.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import ru.rinet.questik.data.room.models.Corp;
import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.data.room.models.Project;
import ru.rinet.questik.data.room.models.ProjectWithJobs;
import ru.rinet.questik.data.room.models.ProjectWithUser;
import ru.rinet.questik.data.room.models.User;

@Dao
public interface ProjectDao {
    //запрос всей таблицы
    @Query("SELECT * FROM Project")
    LiveData<List<Project>> getAll();

    //запрос на каегорию по его id
    @Query("SELECT * FROM Project WHERE project_id = :id")
    Project getById(Long id);

    //запрос на список работ по ид проекта

    @Query("SELECT * FROM Project")
    Project getProject();

    @Query("SELECT * FROM Project")
    List<Project> getProjectList();

    @Transaction
    @Query("SELECT * FROM Project")
    List<ProjectWithJobs> getProjectJobs();

    @Transaction
    @Insert
    void insertFullProject(Project project, Corp corp, User user, List<Job> jobList);


    @Query("SELECT COUNT(project_id) FROM Project")
    Long getRecordsCount();

    @Transaction
    @Query("SELECT project_id, mName from Project")
    List<ProjectWithJobs> getProjectWithJobs();

    @Query("SELECT * from Project")
    List<ProjectWithUser> getProjectWithUser();

    //Wipe
    @Query("DELETE FROM Project")
    void wipeProjectTable();
/*    @Transaction
    @Query("SELECT * FROM Job WHERE mProjectId = :mId")
    List<ProjectWithJobs> getJobByProject(long mId);*/

    @Insert
    long insert(Project project);

/*    @Transaction
    void insertFull (Project project, Corp corp, User user, List<Job> joblist) {
        project.set
    }*/

/*
    @Insert
    int insertProjectAndJobs(Project project, List<Job> jobs);
*/
    @Insert
    List<Long> insertProjectList(List<Project> projectList);

    @Update
    int update(Project project);

    @Delete
    int delete(Project project);


}