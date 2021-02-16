/**
 * Assignment #: HW02
 * File name: group#13_HW02
 * Student: Carlos Del Carpio
 */
package com.example.todolist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    ArrayList<Task> tasks;
    TextView numberOfUpcomingTasksLabel;
    TextView nextTaskLabel;
    TextView nextTaskDateLabel;
    TextView nextTaskPriority;
    Button viewTasksButton;
    Button createATaskButton;
    String[] taskTitles;
    String TAG = "Carlos";

    final static public String SENT_TASK = "SENT_TASK";
    final static public int CREATE_A_TASK_REQUEST_CODE = 1;
    final static public int VIEW_A_TASK_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing variables
        AlertDialog.Builder tasksAlertDialogBuilder = new AlertDialog.Builder(this);
        numberOfUpcomingTasksLabel = findViewById(R.id.numberOfUpcomingTasksLabel);
        nextTaskLabel = findViewById(R.id.nextTaskLabel);
        nextTaskDateLabel = findViewById(R.id.nextTaskDateLabel);
        nextTaskPriority = findViewById(R.id.nextTaskPriorityLabel);
        viewTasksButton = findViewById(R.id.viewTasksButton);
        createATaskButton = findViewById(R.id.createATaskButton);
        tasks = new ArrayList<Task>();

        //Update tasks count
        updateTaskCount(tasks.size());

        //Intent for when a task is deleted
        if (getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(DisplayTask.DELETE_TASK))  {
            Task task = (Task)getIntent().getSerializableExtra(DisplayTask.DELETE_TASK);
            tasks.remove(tasks.indexOf(tasks));
        }


        //On click listener to view task. Takes user to Display Task Activity or returns to MainActivity.
        viewTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tasksAlertDialogBuilder.setTitle(R.string.select_task);
                generateTaskArray();
                tasksAlertDialogBuilder.setItems(taskTitles, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Task task = tasks.get(which);
                        Intent goToDisplayTask = new Intent(MainActivity.this, DisplayTask.class);
                        goToDisplayTask.putExtra(SENT_TASK, tasks.get(which));
                        startActivityForResult(goToDisplayTask, VIEW_A_TASK_REQUEST_CODE);
                    }
                });

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


        //Takes user to Create Task Activity
        createATaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCreateATask = new Intent(MainActivity.this, CreateTask.class);
                startActivityForResult(goToCreateATask, CREATE_A_TASK_REQUEST_CODE);
            }
        });
    }


    //Awaits for result of task created or task deleted.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            if (requestCode == 1 && data != null && data.hasExtra(CreateTask.CREATED_TASK)) {
                Task receivedTask = (Task)data.getSerializableExtra(CreateTask.CREATED_TASK);
                tasks.add(receivedTask);
                generateUpcomingTasks();
            } else if (requestCode == 2 && data != null && data.hasExtra(MainActivity.SENT_TASK)) {
                Task receivedTask = (Task)data.getSerializableExtra(MainActivity.SENT_TASK);
                if (tasks.contains(receivedTask)) {
                    tasks.remove(tasks.indexOf(receivedTask));
                    generateUpcomingTasks();
                }
            }
        }
    }

    //Updates task count label depending on size of tasks arraylist.
    public void updateTaskCount (int count) {
        String numberOfTasks = "None";

        if (count > 0) {
            numberOfTasks = getString(R.string.you_have) + " " + String.valueOf(count) + " " + getString(R.string.tasks);
        }

        numberOfUpcomingTasksLabel.setText(numberOfTasks);
    }

    //Generates array to pass into alert dialog
    public void generateTaskArray() {
        taskTitles = new String[tasks.size()];

        for (int i = 0; i< tasks.size(); i++) {
            taskTitles[i] = tasks.get(i).task;
        }
    }

    //Sets detail of the next task, sorts before doing so.
    public void generateUpcomingTasks() {
        Log.d(TAG, "Generating tasks");
        if (tasks.size() > 0) {
            Collections.sort(tasks, Task::compareTo);
            tasks.sort(Task::compareTo);
            nextTaskLabel.setText(tasks.get(0).task);
            nextTaskDateLabel.setText(tasks.get(0).dueDate);
            nextTaskPriority.setText(tasks.get(0).priority);
            updateTaskCount(tasks.size());
        } else if (tasks.size() == 0){
            updateTaskCount(0);
            nextTaskLabel.setText("");
            nextTaskDateLabel.setText("");
            nextTaskPriority.setText("");
        }
    }
}