package com.example.taskmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;

public class AddTask extends AppCompatActivity {

    public static final String DATA = "DATA";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    private EditText edtTxtSubject;
    private Button btnAdd;
    private RadioGroup radioGroupStatus;
    private RadioButton radio_done;
    private RadioButton radio_due;
    private TextView txtResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Intent intent = getIntent();
        setupAttribute();
        setupSharedPrefs();
    }

    private void setupAttribute() {
        edtTxtSubject = findViewById(R.id.edtTxtSubject);
        btnAdd = findViewById(R.id.btnAdd);
        radioGroupStatus = findViewById(R.id.radioGroupStatus);
        radio_done = findViewById(R.id.radio_done);
        radio_due = findViewById(R.id.radio_due);
        txtResult = findViewById(R.id.txtResult);
    }

    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();

    }

    public void btnAddOnClick(View view){
        String subject = edtTxtSubject.getText().toString().trim();
        int selectedId = radioGroupStatus.getCheckedRadioButtonId();


        if(!subject.isEmpty() && selectedId != -1) {
            RadioButton selectedStatus = findViewById(selectedId);
            String status = selectedStatus.getText().toString().trim();

            Task checkTask = searchTaskBySubject(subject);
            if(checkTask != null){
                txtResult.setVisibility(View.VISIBLE);
                txtResult.setText("This Subject already exists \uD83D\uDE14");
            }
            else{
                Task newTask = new Task(subject, status);
                Task.listTasks.add(newTask);

                Gson gson = new Gson();
                String tasksString = gson.toJson(Task.listTasks);

                editor.putString(DATA, tasksString);
                editor.commit();

                txtResult.setVisibility(View.VISIBLE);
                txtResult.setText("The Task Has Been Added Successfully \uD83D\uDE04");
            }

        }
        else{
            txtResult.setVisibility(View.VISIBLE);
            txtResult.setText("Please Enter The Subject and Status For Your Task \uD83D\uDE14");
        }
    }
    private Task searchTaskBySubject(String subject) {
        for(Task task : Task.listTasks){
            if(task.getSubject().equals(subject)){
                return task;
            }
        }
        return null;
    }
}