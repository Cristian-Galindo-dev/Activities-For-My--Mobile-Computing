package com.example.room_persistance_project.controller;

import android.content.Context;

import com.example.room_persistance_project.model.database.AppDatabase;
import com.example.room_persistance_project.model.entity.Note;
import com.example.room_persistance_project.model.relations.CategoryWithNotes;

import java.util.List;
//main controller
public class MainController {

    private AppDatabase appDatabase;

    public MainController(Context context) {
        appDatabase = AppDatabase.getInstance(context);
    }

    public List<CategoryWithNotes> loadCategoriesWithNotes() {
        return appDatabase.categoryDao().getCategoriesWithNotes();
    }

    public List<Note> searchNotes(String query) {
        return appDatabase.noteDao().searchNotes(query);
    }

}
