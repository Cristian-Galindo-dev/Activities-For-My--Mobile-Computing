package com.example.room_persistance_project.model.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.room_persistance_project.model.entity.Category;
import com.example.room_persistance_project.model.entity.Note;

import java.util.List;

public class CategoryWithNotes {

    @Embedded
    public Category category;

    @Relation(
        parentColumn = "category_id",
        entityColumn = "category_id"
    )
    public List<Note> notes;
}
