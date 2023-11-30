package com.example.myapplication;







import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.function.Predicate;

public class AddTaskActivity extends AppCompatActivity {

    Button btnAdd;
    EditText task;
    SharedPreferences pr;
    SharedPreferences.Editor ed;
    ArrayList<Task> myTask;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        btnAdd = findViewById(R.id.btnDone);
        task = findViewById(R.id.edtT);
        pr = PreferenceManager.getDefaultSharedPreferences(this);
        ed = pr.edit();
        gson = new Gson();

        // Define the Add Button action.
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ta = task.getText().toString();

                if (!ta.isEmpty()) {

                    if (!pr.contains("tasks")) {
                        // If "tasks" key doesn't exist, create a new list and add the task
                        myTask = new ArrayList<>();
                        myTask.add(new Task(ta, ""));
                    } else {
                        // If "tasks" key exists, retrieve the existing list and add the task
                        String jstr = pr.getString("tasks", "");
                        Type listType = new TypeToken<ArrayList<Task>>() {
                        }.getType();
                        myTask = gson.fromJson(jstr, listType);
                        myTask.add(new Task(ta, ""));
                    }

                    // Convert the list to JSON and store it in SharedPreferences
                    String jsonString = gson.toJson(myTask);
                    ed.putString("tasks", jsonString);
                    ed.apply();

                    // Navigate back to MainActivity
                    Intent intent = new Intent(AddTaskActivity.this, MainActivity.class);
                    startActivity(intent);

                } else {
                    // If the task text is empty, just navigate back to MainActivity
                    Intent intent = new Intent(AddTaskActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}