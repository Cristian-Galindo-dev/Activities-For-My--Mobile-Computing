package com.example.room_persistance_project.model.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class Task {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String task_title;

    public String task_description;

    public String created_at;

    public boolean is_completed;
}
