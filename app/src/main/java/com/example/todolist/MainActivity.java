package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Task> Tasks;
    TextView numberOfUpcomingTasksLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Tasks = new ArrayList<>();
        updateTaskCount(Tasks.size());
    }

    public void updateTaskCount (int count) {
        String numberOfTasks;
        numberOfUpcomingTasksLabel = findViewById(R.id.numberOfUpcomingTasksLabel);
        numberOfTasks = getString(R.string.you_have) + " " + String.valueOf(count) + " " + getString(R.string.tasks);
        numberOfUpcomingTasksLabel.setText(numberOfTasks);
    }
}