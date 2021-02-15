package com.example.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.Format;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Task> Tasks;
    TextView numberOfUpcomingTasksLabel;
    Button viewTasksButton;
    Button createATaskButton;

    final static public int CREATE_A_TASK_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlertDialog.Builder tasksAlertDialogBuilder = new AlertDialog.Builder(this);
        numberOfUpcomingTasksLabel = findViewById(R.id.numberOfUpcomingTasksLabel);
        viewTasksButton = findViewById(R.id.viewTasksButton);
        createATaskButton = findViewById(R.id.createATaskButton);
        Tasks = new ArrayList<Task>();
        generateTasks(3);

        updateTaskCount(Tasks.size());

        viewTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tasksAlertDialogBuilder.setTitle(R.string.select_task);
//                tasksAlertDialogBuilder.setItems(Tasks.toArray().toString(), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });

                tasksAlertDialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog viewTasksAlertDialog = tasksAlertDialogBuilder.create();
                viewTasksAlertDialog.show();
            }
        });

        createATaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCreateATask = new Intent(MainActivity.this, CreateTask.class);
                startActivityForResult(goToCreateATask, CREATE_A_TASK_REQUEST_CODE);
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

    public void generateTasks(int num) {
        for (int i = 0; i < num; i++) {
            Tasks.add(new Task("New Task " + String.valueOf(num)));
        }
    }
}