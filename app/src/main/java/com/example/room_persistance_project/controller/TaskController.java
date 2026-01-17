package com.example.room_persistance_project.controller;

import android.content.Context;
import android.text.TextUtils;

import com.example.room_persistance_project.model.database.AppDatabase;
import com.example.room_persistance_project.model.entity.History;
import com.example.room_persistance_project.model.entity.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TaskController {

    private AppDatabase appDatabase;

    public TaskController(Context context) {
        appDatabase = AppDatabase.getInstance(context);
    }

    public void insertTask(Task task) {
        appDatabase.taskDao().insertTask(task);
        addHistoryRecord("create_task", "Task: " + task.task_title);
    }

    public void updateTask(Task task) {
        appDatabase.taskDao().updateTask(task);
        addHistoryRecord("update_task", "Task: " + task.task_title);
    }

    public void deleteTask(Task task) {
        appDatabase.taskDao().deleteTask(task);
        addHistoryRecord("delete_task", "Task: " + task.task_title);
    }

    public boolean validateFields(Task task) {
        if (TextUtils.isEmpty(task.task_title)) {
            return false;
        }
        if (TextUtils.isEmpty(task.created_at)) {
            return false;
        }
        return true;
    }

    private void addHistoryRecord(String action, String details) {
        History history = new History();
        history.action = action;
        history.details = details;
        history.createdAt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        appDatabase.historyDao().insert(history);
    }
}
