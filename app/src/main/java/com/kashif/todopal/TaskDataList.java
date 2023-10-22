package com.kashif.todopal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TaskDataList extends AppCompatActivity {

    private RecyclerView recViewTask;
    private RecyclerTaskAdapter adapter;
    private DatabaseHelper databaseHelper;
    private ArrayList<TaskModel> taskModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_data_list);

        recViewTask = findViewById(R.id.recViewTask);
        recViewTask.setLayoutManager(new LinearLayoutManager(this));

        // Initialize DataBase
        databaseHelper = new DatabaseHelper(this);
        taskModels = new ArrayList<>();
        adapter = new RecyclerTaskAdapter(this, taskModels);
        recViewTask.setAdapter(adapter);
        displayData();

        /*ArrayList<TaskModel> taskModels = (ArrayList<TaskModel>) getIntent().getSerializableExtra("taskList");
        if (taskModels != null) {
            // Set up the RecyclerView adapter with the task list
            adapter = new RecyclerTaskAdapter(this , taskModels);
            recViewTask.setAdapter(adapter);
        }*/


    }

    private void displayData() {
        taskModels.clear();
        SQLiteDatabase db =databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ DatabaseHelper.TASK_TABLE_COL, null);
        while (cursor.moveToNext()) {
            String tasks = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.TASKS_COL));
            taskModels.add(new TaskModel(tasks));
        }
        cursor.close();
        db.close();
        adapter.notifyDataSetChanged();
    }
}