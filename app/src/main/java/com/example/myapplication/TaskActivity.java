package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class TaskActivity extends AppCompatActivity {

    TextView list ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        list = findViewById(R.id.test) ;
        String description = getIntent().getStringExtra("DESCRIPTION");
        list.setText(description);
    }
}