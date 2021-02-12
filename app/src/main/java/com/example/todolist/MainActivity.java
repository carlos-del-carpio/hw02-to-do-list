package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Task> Tasks;
    TextView numberOfUpcomingTasksLabel;
    Button viewTasksButton;
    Button createATaskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberOfUpcomingTasksLabel = findViewById(R.id.numberOfUpcomingTasksLabel);
        viewTasksButton = findViewById(R.id.viewTasksButton);
        createATaskButton = findViewById(R.id.createATaskButton);
        Tasks = new ArrayList<>();

        updateTaskCount(Tasks.size());

        viewTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    public void updateTaskCount (int count) {
        String numberOfTasks = "None";

        if (count > 0) {
            numberOfTasks = getString(R.string.you_have) + " " + String.valueOf(count) + " " + getString(R.string.tasks);
        }

        numberOfUpcomingTasksLabel.setText(numberOfTasks);
    }
}