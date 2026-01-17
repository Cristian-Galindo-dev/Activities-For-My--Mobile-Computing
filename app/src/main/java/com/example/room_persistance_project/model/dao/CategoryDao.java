package com.example.room_persistance_project.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import com.example.room_persistance_project.model.entity.Category;
import com.example.room_persistance_project.model.relations.CategoryWithNotes;
import java.util.List;

@Dao
public interface CategoryDao {

    @Insert
    long insert(Category category);

    @Query("SELECT * FROM categories")
    List<Category> getAll();

    @Transaction
    @Query("SELECT * FROM categories")
    List<CategoryWithNotes> getCategoriesWithNotes();
}
