package com.example.taskmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class UnfinishedTasks extends AppCompatActivity {
    private ListView listViewDue;
    private Button btnLoadd;
    private TextView txtResultDue;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unfinished_tasks);
        Intent intent = getIntent();
        setupAttribute();
        setupSharedPrefs();
    }

    private void setupAttribute() {
        listViewDue = findViewById(R.id.listViewDue);
        btnLoadd = findViewById(R.id.btnLoadd);
        txtResultDue = findViewById(R.id.txtResultDue);
    }

    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();

    }

    public void btnLoaddOnClick(View view){
        Gson gson = new Gson();
        String str = prefs.getString("DATA", "");
        if(!str.equals("")){
            ArrayList<Task> allTasks = gson.fromJson(str, new TypeToken<ArrayList<Task>>(){}.getType());
            if(allTasks.isEmpty()){
                txtResultDue.setVisibility(View.VISIBLE);
                txtResultDue.setText("We Don`t Have Any Task ... \uD83D\uDE14");
                listViewDue.setVisibility(View.GONE);
            }
            else{
                ArrayList<Task> dueTasks = new ArrayList<>();
                for(Task task : allTasks){
                    if(task.getStatus().equals("Due")){
                        dueTasks.add(task);
                    }
                }
                if(dueTasks.isEmpty()){
                    txtResultDue.setVisibility(View.VISIBLE);
                    txtResultDue.setText("There Is No Due Task ... \uD83D\uDE14");
                    listViewDue.setVisibility(View.GONE);
                }
                else{
                    ArrayAdapter<Task> adapterDone = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dueTasks);
                    listViewDue.setAdapter(adapterDone);
                    listViewDue.setVisibility(View.VISIBLE);
                }
            }
        }
        else{
            txtResultDue.setVisibility(View.VISIBLE);
            txtResultDue.setText("We Don`t Have Any Task ... \uD83D\uDE14");
            listViewDue.setVisibility(View.GONE);
        }
    }
}
