package com.example.room_persistance_project.controller;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.room_persistance_project.model.database.AppDatabase;
import com.example.room_persistance_project.model.entity.History;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class HistoryController {

    private AppDatabase appDatabase;
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private Handler handler = new Handler(Looper.getMainLooper());

    public HistoryController(Context context) {
        appDatabase = AppDatabase.getInstance(context);
    }

    public void getAllHistory(Consumer<List<History>> callback) {
        executor.execute(() -> {
            List<History> historyList = appDatabase.historyDao().getAll();
            handler.post(() -> callback.accept(historyList));
        });
    }

    public void filterByDate(String date, Consumer<List<History>> callback) {
        executor.execute(() -> {
            List<History> historyList = appDatabase.historyDao().filterByDate(date);
            handler.post(() -> callback.accept(historyList));
        });
    }

    public void filterByAction(String action, Consumer<List<History>> callback) {
        executor.execute(() -> {
            List<History> historyList = appDatabase.historyDao().filterByAction(action);
            handler.post(() -> callback.accept(historyList));
        });
    }
}
