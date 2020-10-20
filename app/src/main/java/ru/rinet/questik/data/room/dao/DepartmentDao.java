package ru.rinet.questik.data.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ru.rinet.questik.data.room.models.Departament;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface DepartmentDao {
    //запрос всей таблицы
    @Query("SELECT * FROM Departament")
    LiveData<List<Departament>> getAll();

    //запрос на каегорию по его id
    @Query("SELECT * FROM Departament WHERE departament_id = :id")
    Departament getById(Long id);

    @Query("SELECT * FROM Departament")
    Departament getDepartament();

    @Query("SELECT * FROM Departament")
    List<Departament> getDepartamentList();

    //Wipe
    @Query("DELETE FROM Departament")
    void wipeDepartamentTable();

    @Insert(onConflict = REPLACE)
    Long insert(Departament departament);

    @Insert
    List<Long> insertDepartamentList(List<Departament> departamentList);

    @Update
    int update(Departament departament);

    @Delete
    int delete(Departament departament);
}