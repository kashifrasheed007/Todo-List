package com.kashif.todopal;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recViewTask;
    private EditText etTask;
    private Button btnAdd, btnViewTask;
    private ArrayList<TaskModel> taskModels = new ArrayList<>();
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.etTask);
        btnAdd = findViewById(R.id.btnAdd);
        btnViewTask = findViewById(R.id.btnViewTask);

        databaseHelper = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTasks();
                Toast.makeText(MainActivity.this, "Task added", Toast.LENGTH_SHORT).show();
            }
        });

        btnViewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaskDataList.class);
                startActivity(intent);
            }
        });

    }

    private void addTasks() {
        String tasks = etTask.getText().toString().trim();
        if (tasks.equals("")){
            Toast.makeText(MainActivity.this, "Task is Empty, Add some Task...", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.TASKS_COL, tasks);

        try {
            long newRowId = db.insert(DatabaseHelper.TASK_TABLE_COL, null , cv);

            if (newRowId != -1){
                // Data inserted successfully
                etTask.setText("") ;
            }else {
                // Error inserting data
            }
        }catch (Exception e){
            // Handle exception if data insertion fails
            e.printStackTrace();
        }finally {
            db.close();
        }
        /*else {
            taskModels.add(new TaskModel(tasks));
            etTask.setText("");
            Toast.makeText(MainActivity.this, "Task Added Successfully", Toast.LENGTH_SHORT).show();
            // Pass the data from this activity to another
            Intent intent = new Intent(MainActivity.this, TaskDataList.class);
            intent.putExtra("taskList", taskModels);
            startActivity(intent);
        }*/
    }


}
