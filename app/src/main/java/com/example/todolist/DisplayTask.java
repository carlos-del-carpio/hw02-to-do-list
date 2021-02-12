package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DisplayTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_task);
        setTitle(getString(R.string.display_task));
    }
}