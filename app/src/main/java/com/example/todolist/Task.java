/**
 * Assignment #: HW02
 * File name: group#13_HW02
 * Student: Carlos Del Carpio
 */
package com.example.todolist;

import android.util.Log;

import java.io.Serializable;
import java.util.Comparator;

public class Task implements Serializable, Comparable<Task>, Comparator<Task> {
    String task;
    String dueDate;
    String priority;
    String comparableDueDate;
    String TAG = "Carlos";

    public Task(String task, String dueDate, String priority) {
        this.task = task;
        this.dueDate = dueDate;
        this.priority = priority;
        this.comparableDueDate = dueDate.substring(6) + dueDate.substring(0,2) + dueDate.substring(3,5);
    }

    @Override
    public String toString() {
        return "Task{" +
                "task='" + task + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }

    @Override
    public int compareTo(Task o) {
        int returnVariable = this.comparableDueDate.compareTo(o.comparableDueDate);
        Log.d(TAG, this.dueDate);
        Log.d(TAG, o.dueDate);
        Log.d(TAG, this.comparableDueDate + " " + o.comparableDueDate);
        return returnVariable;
    }

    @Override
    public int compare(Task o1, Task o2) {
        Log.d(TAG, o1.comparableDueDate + " " + o2.comparableDueDate);
        return o1.comparableDueDate.compareTo(o2.comparableDueDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task1 = (Task) o;
        return task.equals(task1.task) &&
                dueDate.equals(task1.dueDate) &&
                priority.equals(task1.priority);
    }
}
