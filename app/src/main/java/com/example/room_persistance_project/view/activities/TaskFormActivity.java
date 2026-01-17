package com.example.room_persistance_project.view.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.room_persistance_project.R;
import com.example.room_persistance_project.controller.TaskController;
import com.example.room_persistance_project.model.entity.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskFormActivity extends AppCompatActivity {

    private EditText editTextTitle, editTextDescription;
    private Button buttonSave;
    private TaskController taskController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_form);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("");
        }

        taskController = new TaskController(this);

        editTextTitle = findViewById(R.id.edit_text_task_title);
        editTextDescription = findViewById(R.id.edit_text_task_description);
        buttonSave = findViewById(R.id.button_save_task);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void saveTask() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());

        Task task = new Task();
        task.task_title = title;
        task.task_description = description;
        task.created_at = currentDate;
        task.is_completed = false;

        if (taskController.validateFields(task)) {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                taskController.insertTask(task);
                runOnUiThread(() -> finish());
            });
        }
    }
}
