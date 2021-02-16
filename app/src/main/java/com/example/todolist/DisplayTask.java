/**
 * Assignment #: HW02
 * File name: group#13_HW02
 * Student: Carlos Del Carpio
 */
package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.badge.BadgeUtils;

public class DisplayTask extends AppCompatActivity {
    TextView nameLabel;
    TextView dateLabel;
    TextView priorityLabel;
    Button cancelButton;
    Button deleteButton;
    final static public String DELETE_TASK = "DELETE_TASK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_task);
        setTitle(getString(R.string.display_task));


        //Initialized variables
        nameLabel = findViewById(R.id.nameLabel);
        dateLabel = findViewById(R.id.dateLabel);
        priorityLabel = findViewById(R.id.priorityLabel);
        cancelButton = findViewById(R.id.cancelButton);
        deleteButton = findViewById(R.id.deleteButton);


        //Sets data taken from Main Activity's passed task
        if (getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(MainActivity.SENT_TASK))  {
            Task task = (Task)getIntent().getSerializableExtra(MainActivity.SENT_TASK);

            nameLabel.setText(task.task);
            dateLabel.setText(task.dueDate);
            priorityLabel.setText(task.priority);
        }

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //Passes object back to be deleted
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task taskToBeDeleted = (Task)getIntent().getSerializableExtra(MainActivity.SENT_TASK);
                Intent goToMainActivity = new Intent(DisplayTask.this, MainActivity.class);
                goToMainActivity.putExtra(MainActivity.SENT_TASK, taskToBeDeleted);
                setResult(RESULT_OK, goToMainActivity);
                finish();
            }
        });
    }
}