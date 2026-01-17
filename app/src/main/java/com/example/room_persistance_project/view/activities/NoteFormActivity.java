package com.example.room_persistance_project.view.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.room_persistance_project.R;
import com.example.room_persistance_project.controller.NoteController;
import com.example.room_persistance_project.model.entity.Category;

import java.util.List;
import java.util.stream.Collectors;

public class NoteFormActivity extends AppCompatActivity {

    private AutoCompleteTextView autoCompleteCategory;
    private EditText newCategoryEditText, noteTitleEditText, noteContentEditText;
    private Button saveButton;
    private NoteController noteController;
    private List<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_form);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("");
        }

        noteController = new NoteController(this);

        autoCompleteCategory = findViewById(R.id.auto_complete_category);
        newCategoryEditText = findViewById(R.id.edit_text_new_category);
        noteTitleEditText = findViewById(R.id.edit_text_note_title);
        noteContentEditText = findViewById(R.id.edit_text_note_content);
        saveButton = findViewById(R.id.button_save_note);

        loadCategories();

        saveButton.setOnClickListener(v -> saveNote());
    }

    private void loadCategories() {
        noteController.getAllCategories(categoryList -> {
            this.categories = categoryList;
            List<String> categoryNames = categories.stream().map(c -> c.categoryName).collect(Collectors.toList());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, categoryNames);
            autoCompleteCategory.setAdapter(adapter);
        });
    }

    private void saveNote() {
        String newCategoryName = newCategoryEditText.getText().toString().trim();
        String selectedCategoryName = autoCompleteCategory.getText().toString().trim();
        String noteTitle = noteTitleEditText.getText().toString().trim();
        String noteContent = noteContentEditText.getText().toString().trim();

        if (noteTitle.isEmpty() || noteContent.isEmpty()) {
            Toast.makeText(this, "Note title and content cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedCategoryName.isEmpty() && newCategoryName.isEmpty()) {
            Toast.makeText(this, "Please select or create a category", Toast.LENGTH_SHORT).show();
            return;
        }

        noteController.saveNoteWithCategory(newCategoryName, selectedCategoryName, noteTitle, noteContent, this.categories, () -> {
            Toast.makeText(this, "Note saved successfully", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
