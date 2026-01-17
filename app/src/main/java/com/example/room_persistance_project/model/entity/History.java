package com.example.room_persistance_project.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "history")
public class History {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "history_id")
    public int historyId;

    @ColumnInfo(name = "action")
    public String action;

    @ColumnInfo(name = "created_at")
    public String createdAt;

    @ColumnInfo(name = "details")
    public String details;
}
