package com.example.room_persistance_project.model.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.room_persistance_project.model.dao.CategoryDao;
import com.example.room_persistance_project.model.dao.HistoryDao;
import com.example.room_persistance_project.model.dao.NoteDao;
import com.example.room_persistance_project.model.dao.TaskDao;
import com.example.room_persistance_project.model.entity.Category;
import com.example.room_persistance_project.model.entity.History;
import com.example.room_persistance_project.model.entity.Note;
import com.example.room_persistance_project.model.entity.Task;

@Database(entities = {Task.class, Category.class, Note.class, History.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TaskDao taskDao();
    public abstract NoteDao noteDao();
    public abstract CategoryDao categoryDao();
    public abstract HistoryDao historyDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "task-database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
