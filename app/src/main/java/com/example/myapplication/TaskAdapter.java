package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class TaskAdapter extends ArrayAdapter<Task> {
    private Context context;

    public TaskAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Task task = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_item, parent, false);
        }

        TextView titleTextView = convertView.findViewById(R.id.titleTextView);
        titleTextView.setText(task.getTitle());

        CheckBox checkBox = convertView.findViewById(R.id.checkBox);
        checkBox.setChecked(task.getDone());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Update the 'isDone' status of the task
                task.setDone(isChecked);

            }
        });

        ImageView deleteIcon = convertView.findViewById(R.id.deleteIcon);
        deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle delete icon click: Remove the task from SharedPreferences and notify the adapter
                removeTaskFromSharedPreferences(task);
                remove(task);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    private void removeTaskFromSharedPreferences(Task task) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String jstr  = sharedPreferences.getString("tasks", "");

        Type listType = new TypeToken<ArrayList<Task>>() {}.getType();
        ArrayList<Task>  myTask = new Gson().fromJson(jstr, listType);
        myTask.remove(task);

            String updatedJson = new Gson().toJson(myTask);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("tasks", updatedJson);
            editor.apply();


    }

}