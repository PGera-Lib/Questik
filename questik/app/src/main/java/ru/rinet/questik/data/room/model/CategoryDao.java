package ru.rinet.questik.data.room.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CategoryDao {

    @Query("SELECT * FROM category")
    List<Category> getAll();

    @Query("SELECT * FROM category WHERE _id = :id")
    Category getById(int id);
    @Transaction
    @Query("SELECT * FROM category WHERE _id IN (:categoryWithJob)")
    List<CategoryWithJob> getCategoryWithJob(int categoryWithJob);

    @Insert
    void insert(Category category);


    @Update
    void update(Category category);

    @Delete
    void delete(Category category);
}
