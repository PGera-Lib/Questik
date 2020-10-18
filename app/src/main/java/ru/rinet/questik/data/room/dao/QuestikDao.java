package ru.rinet.questik.data.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ru.rinet.questik.data.retrofit.pojo.QuestikResponse;

@Dao
public interface QuestikDao {

    //запрос всей таблицы
    @Query("SELECT * FROM QuestikResponse")
    List<QuestikResponse> getAll();

    //запрос на каегорию по его id
    @Query("SELECT * FROM QuestikResponse WHERE mVersion = :id")
    QuestikResponse getById(String id);

    //запрос на список работ по ид проекта

    @Query("SELECT * FROM QuestikResponse")
    QuestikResponse getQuestik();

/*    @Transaction
    @Query("SELECT * FROM Questik")
    List<ProjectWithJobs> getProjectJobs();*/

    @Query("SELECT COUNT(mVersion) FROM QuestikResponse")
    Long getRecordsCount();

    //Wipe
    @Query("DELETE FROM QuestikResponse")
    void wipeQuestikTable();

    @Insert
    Long insert(QuestikResponse questik);

    @Insert
    List<Long >insertQuestikList(List<QuestikResponse> questikResponseList);

/*
    @Insert
    int insertProjectAndJobs(Project project, List<Job> jobs);
*/

    @Update
    int update(QuestikResponse questik);

    @Delete
    int delete(QuestikResponse questik);

}

