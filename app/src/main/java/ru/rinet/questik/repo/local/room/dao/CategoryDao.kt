package ru.rinet.questik.repo.local.room.dao

import androidx.paging.PagingSource
import androidx.room.*
import ru.rinet.questik.repo.local.room.entity.CategoryEntity

@Dao
interface CategoryDao {

    @Query("SELECT COUNT(id) FROM categories")
    fun getCategoriesCount(): Int

    @Query("SELECT * FROM categories")
    fun getAllCategories(): PagingSource<Int, CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCategories(items:List<CategoryEntity>)

    @Query("DELETE FROM categories")
    fun clearCategories()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: CategoryEntity)

    @Update
    fun updateCategory(category: CategoryEntity)

    @Delete
    fun deleteCategory(category: CategoryEntity)

    @Query("SELECT * FROM categories WHERE id == :id")
    fun getCategoryById(id: Int): List<CategoryEntity>

    @Query("SELECT * FROM categories")
    fun getCategories(): List<CategoryEntity>
}