package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    define sharedPreferance
   SharedPreferences prefs ;
     SharedPreferences.Editor edit ;
    Gson gson ;
     ArrayList<Task> myTask;

     private ListView list ;

     private TextView test ;
     private Button btnAdd ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        edit  = prefs.edit() ;
        gson = new Gson();
        list = findViewById(R.id.listV);
        btnAdd = findViewById(R.id.btnAddT) ;

            myTask = readFromLS() ;

             if(myTask.size() > 0){
                 TaskAdapter adapter = new TaskAdapter(this,myTask) ;
                 list.setAdapter(adapter);
//                 list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                     @Override
//                     public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                         Task selectedTask = myTask.get(position);
//
//                         // Open a new activity and pass the description
//                         Intent intent = new Intent(MainActivity.this, TaskActivity.class);
//                         intent.putExtra("DESCRIPTION", selectedTask.getDescription());
//                         startActivity(intent);
//                     }
//                 });
             }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this , AddTaskActivity.class) ;
                startActivity(intent);
            }
        });
    }


    public ArrayList<Task> readFromLS(){

        if( prefs.contains("tasks")){

        String jstr =  prefs.getString("tasks" , "") ;
        Type listType = new TypeToken<ArrayList<Task>>(){}.getType();
        ArrayList<Task> tasks = gson.fromJson(jstr, listType);
        return  tasks ;

        }
       return new ArrayList<Task>() ;
    }


}