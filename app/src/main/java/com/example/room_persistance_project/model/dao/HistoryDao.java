package com.example.room_persistance_project.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.room_persistance_project.model.entity.History;
import java.util.List;

@Dao
public interface HistoryDao {

    @Insert
    void insert(History history);

    @Query("SELECT * FROM history ORDER BY created_at DESC")
    List<History> getAll();

    @Query("SELECT * FROM history WHERE date(created_at) = :date ORDER BY created_at DESC")
    List<History> filterByDate(String date);

    @Query("SELECT * FROM history WHERE action = :actionType ORDER BY created_at DESC")
    List<History> filterByAction(String actionType);
}
