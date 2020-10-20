package ru.rinet.questik.data.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import ru.rinet.questik.data.room.models.Category;
import ru.rinet.questik.data.room.models.CategoryWithJob;

import static androidx.room.OnConflictStrategy.REPLACE;


@Dao
public interface CategoryDao {

    //запрос на каегорию по его id
    @Query("SELECT * FROM Category WHERE category_id = :id")
    Category getById(Long id);

    @Query("SELECT * FROM Category")
    Category getCategory();

    @Query("SELECT * FROM Category")
    List<Category> getCategoryList();

   @Transaction
    @Query("SELECT * FROM Category")
    List<CategoryWithJob> getCategoryWithJob();

    @Insert(onConflict = REPLACE)
    Long insert(Category category);

    @Insert
    List<Long> insertCategoryList(List<Category> categoryList);

    @Update
    int update(Category category);

    @Delete
    int delete(Category category);

    //Wipe
    @Query("DELETE FROM Category")
    void wipeCategoryTable();
}
