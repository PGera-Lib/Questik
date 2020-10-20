package ru.rinet.questik.data.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ru.rinet.questik.data.room.models.User;

@Dao
public interface UserDao {    //запрос всей таблицы
    @Query("SELECT * FROM User")
    LiveData<List<User>> getAll();

    //запрос на каегорию по его id
    @Query("SELECT * FROM User WHERE user_id = :id")
    User getById(Long id);

    @Query("SELECT * FROM User")
    User getUser();

    @Query("SELECT * FROM User")
    List<User> getUserList();

    @Query("SELECT COUNT(user_id) FROM User")
    Long getRecordsCount();

    //Wipe
    @Query("DELETE FROM User")
    void wipeUserTable();

    @Insert
    Long insert(User user);

    @Insert
    List<Long> insertUserList(List<User> userList);

    @Update
    int update(User user);

    @Delete
    int delete(User user);
}