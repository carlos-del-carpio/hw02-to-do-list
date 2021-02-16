/**
 * Assignment #: HW02
 * File name: group#13_HW02
 * Student: Carlos Del Carpio
 */
package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

public class CreateTask extends AppCompatActivity {
    EditText taskNameInput;
    TextView dateLabel;
    Button setDateButton;
    Button submitButton;
    Button cancelButton;
    RadioGroup priorityGroup;
    RadioButton selectedRadioButton;
    DatePickerDialog.OnDateSetListener setDateListener;
    final static public String CREATED_TASK = "CREATED_TASK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        setTitle(getString(R.string.create_task));

        //Initialized variables
        taskNameInput = findViewById(R.id.editTextTextPersonName);
        setDateButton = findViewById(R.id.createTaskSetDateButton);
        submitButton = findViewById(R.id.createTaskSubmitButton);
        cancelButton = findViewById(R.id.createTaskCancelButton);
        dateLabel = findViewById(R.id.dateSetLabel);
        priorityGroup = findViewById(R.id.priorityRadioGroup);


        //Gets calendar data
        setDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_WEEK);

                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateTask.this, setDateListener, year, month, day);
                datePickerDialog.show();
            }
        });


        //Event listener to put date in label
        setDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                String m = "0";
                String d = "0";

                if (month < 10) {
                    m = 0 + String.valueOf(month);
                }

                if (dayOfMonth < 10) {
                    d = 0 + String.valueOf(dayOfMonth);
                }

                dateLabel.setText(m + "/" + d + "/" + year);
            }
        };


        //Creates a task to send back to MainActivity
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMainActivity = new Intent(CreateTask.this, MainActivity.class);

                if (taskNameInput.getText().toString().isEmpty()) {
                    Toast.makeText(getBaseContext(), R.string.enter_a_task_name, Toast.LENGTH_LONG).show();
                } else if (dateLabel.getText().toString().isEmpty()) {
                    Toast.makeText(getBaseContext(), R.string.select_a_date, Toast.LENGTH_LONG).show();
                } else if (priorityGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getBaseContext(), R.string.select_a_priority, Toast.LENGTH_LONG).show();
                } else {
                    int selectedPriority = priorityGroup.getCheckedRadioButtonId();
                    selectedRadioButton = (RadioButton)findViewById(selectedPriority);
                    Task task = new Task(taskNameInput.getText().toString(), dateLabel.getText().toString(), selectedRadioButton.getText().toString());

                    goToMainActivity.putExtra(CREATED_TASK, task);
                    setResult(RESULT_OK, goToMainActivity);
                    finish();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}