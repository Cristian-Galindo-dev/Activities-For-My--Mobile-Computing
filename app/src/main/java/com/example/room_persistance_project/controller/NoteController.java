package com.example.room_persistance_project.controller;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.room_persistance_project.model.database.AppDatabase;
import com.example.room_persistance_project.model.entity.Category;
import com.example.room_persistance_project.model.entity.History;
import com.example.room_persistance_project.model.entity.Note;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class NoteController {

    private AppDatabase appDatabase;
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private Handler handler = new Handler(Looper.getMainLooper());

    public NoteController(Context context) {
        appDatabase = AppDatabase.getInstance(context);
    }

    public void getAllCategories(Consumer<List<Category>> callback) {
        executor.execute(() -> {
            List<Category> categories = appDatabase.categoryDao().getAll();
            handler.post(() -> callback.accept(categories));
        });
    }

    public void saveNoteWithCategory(String newCategoryName, String selectedCategoryName, String noteTitle, String noteContent, List<Category> existingCategories, Runnable onSaveFinished) {
        executor.execute(() -> {
            long categoryId;
            if (!newCategoryName.isEmpty()) {
                Category newCategory = new Category();
                newCategory.categoryName = newCategoryName;
                categoryId = appDatabase.categoryDao().insert(newCategory);
                addHistoryRecord("create_category", "Category: " + newCategoryName);
            } else {
                Optional<Category> existingCategory = existingCategories.stream()
                        .filter(c -> c.categoryName.equals(selectedCategoryName))
                        .findFirst();
                categoryId = existingCategory.map(c -> (long)c.categoryId).orElse(-1L);
            }

            if (categoryId != -1) {
                Note note = new Note();
                note.noteTitle = noteTitle;
                note.noteContent = noteContent;
                note.categoryId = (int) categoryId;
                note.createdAt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
                appDatabase.noteDao().insert(note);
                addHistoryRecord("create_note", "Note: " + noteTitle);
            }

            handler.post(onSaveFinished);
        });
    }

    private void addHistoryRecord(String action, String details) {
        History history = new History();
        history.action = action;
        history.details = details;
        history.createdAt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        appDatabase.historyDao().insert(history);
    }
}
