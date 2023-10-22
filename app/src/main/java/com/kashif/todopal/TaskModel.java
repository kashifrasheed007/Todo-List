package com.kashif.todopal;

import java.io.Serializable;

public class TaskModel implements Serializable {
    String task;

    public TaskModel(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "TaskModel{" +
                "task='" + task + '\'' +
                '}';
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
