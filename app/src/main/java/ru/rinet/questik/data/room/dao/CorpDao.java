package ru.rinet.questik.data.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ru.rinet.questik.data.room.models.Corp;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface CorpDao {
    //запрос всей таблицы
    @Query("SELECT * FROM Corp")
    List<Corp> getAll();

    //запрос на каегорию по его id
    @Query("SELECT * FROM Corp WHERE corp_id = :id")
    long getById(Long id);

    @Query("SELECT * FROM Corp")
    Corp getCorp();

    @Query("SELECT * FROM Corp")
    List<Corp> getCorpList();

    //Wipe
    @Query("DELETE FROM Corp")
    void wipeCorpTable();

    @Insert(onConflict = REPLACE)
    Long insert(Corp corp);

    @Insert
    List<Long> insertCarpList(List<Corp> corpList);

    @Update
    int update(Corp corp);

    @Delete
    int delete(Corp corp);


}