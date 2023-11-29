package com.example.taskmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {
                if(position == 0){
                    Intent intent = new Intent(MainActivity.this, AddTask.class);
                    startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(MainActivity.this, ChangeTaskStatus.class);
                    startActivity(intent);
                }
                else if (position == 2) {
                    Intent intent = new Intent(MainActivity.this, CompletedTasks.class);
                    startActivity(intent);
                }
                else if (position == 3) {
                    Intent intent = new Intent(MainActivity.this, UnfinishedTasks.class);
                    startActivity(intent);
                }

            }
        };
        ListView listView = (ListView)findViewById(R.id.main_menu);
        listView.setOnItemClickListener(itemClickListener);
    }

}