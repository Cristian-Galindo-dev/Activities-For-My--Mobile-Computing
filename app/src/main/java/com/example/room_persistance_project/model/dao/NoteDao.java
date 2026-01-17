package com.example.room_persistance_project.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.room_persistance_project.model.entity.Note;
import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Query("SELECT * FROM notes WHERE category_id = :categoryId")
    List<Note> getNotesByCategory(int categoryId);

    @Query("SELECT * FROM notes WHERE note_title LIKE '%' || :text || '%' OR note_content LIKE '%' || :text || '%'")
    List<Note> searchNotes(String text);
}
