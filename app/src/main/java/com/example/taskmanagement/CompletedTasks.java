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

public class CompletedTasks extends AppCompatActivity {
    private ListView listViewDone;
    private Button btnLoad;
    private TextView txtResultDone;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_tasks);
        Intent intent = getIntent();
        setupAttribute();
        setupSharedPrefs();
    }

    private void setupAttribute() {
        listViewDone = findViewById(R.id.listViewDone);
        btnLoad = findViewById(R.id.btnLoad);
        txtResultDone = findViewById(R.id.txtResultDone);
    }

    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();

    }

    public void btnLoadOnClick(View view){
        Gson gson = new Gson();
        String str = prefs.getString("DATA", "");
        if(!str.equals("")){
            ArrayList<Task> allTasks = gson.fromJson(str, new TypeToken<ArrayList<Task>>(){}.getType());
            if(allTasks.isEmpty()){
                txtResultDone.setVisibility(View.VISIBLE);
                txtResultDone.setText("We Don`t Have Any Task ... \uD83D\uDE14");
                listViewDone.setVisibility(View.GONE);
            }
            else{
                ArrayList<Task> doneTasks = new ArrayList<>();
                for(Task task : allTasks){
                    if(task.getStatus().equals("Done")){
                        doneTasks.add(task);
                    }
                }
                if(doneTasks.isEmpty()){
                    txtResultDone.setVisibility(View.VISIBLE);
                    txtResultDone.setText("There Is No Done Task ... \uD83D\uDE14");
                    listViewDone.setVisibility(View.GONE);
                }
                else{
                    ArrayAdapter<Task> adapterDone = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, doneTasks);
                    listViewDone.setAdapter(adapterDone);
                    listViewDone.setVisibility(View.VISIBLE);
                }
            }
        }
        else{
            txtResultDone.setVisibility(View.VISIBLE);
            txtResultDone.setText("We Don`t Have Any Task ... \uD83D\uDE14");
            listViewDone.setVisibility(View.GONE);
        }
    }
}
