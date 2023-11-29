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

public class ChangeTaskStatus extends AppCompatActivity {

    public static final String DATA = "DATA";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    private EditText edtTxtSubject;
    private Button btnGet;
    private TextView txtResultStatus;
    private Button btnChange;
    private TextView txtResultChange;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_task_status);
        Intent intent = getIntent();
        setupAttribute();
        setupSharedPrefs();
    }

    private void setupAttribute() {
        edtTxtSubject = findViewById(R.id.TxtSubject);
        btnGet = findViewById(R.id.btnGet);
        txtResultStatus = findViewById(R.id.txtResultStatus);
        btnChange = findViewById(R.id.btnChange);
        txtResultChange = findViewById(R.id.txtResultChange);
    }

    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();

    }

    public void btngetStatusOnClick(View view){
        String subject = edtTxtSubject.getText().toString().trim();
        if(!subject.isEmpty()){
            Task taskSubject = searchTaskBySubject(subject);
            if(taskSubject != null){
                if(taskSubject.getStatus().equals("Done")){
                    txtResultChange.setVisibility(View.VISIBLE);
                    txtResultChange.setText("The Status Is : "+ taskSubject.getStatus() + " So You Don`t Need To Make Change \uD83D\uDE04");
                    btnChange.setVisibility(View.GONE);
                    txtResultStatus.setVisibility(View.GONE);
                }
                else{
                    txtResultStatus.setVisibility(View.VISIBLE);
                    txtResultStatus.setText("The Status Is : "+ taskSubject.getStatus() + " \uD83D\uDE04");
                    btnChange.setVisibility(View.VISIBLE);
                    txtResultChange.setVisibility(View.GONE);
                }

            }
            else{
                txtResultChange.setVisibility(View.VISIBLE);
                txtResultChange.setText("There Is No Task With This Subject \uD83D\uDE14");
                btnChange.setVisibility(View.GONE);
                txtResultStatus.setVisibility(View.GONE);
            }
        }
        else{
            txtResultChange.setVisibility(View.VISIBLE);
            txtResultChange.setText("Please, Enter Subject Task \uD83D\uDE14");
            btnChange.setVisibility(View.GONE);
            txtResultStatus.setVisibility(View.GONE);
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

    public void setBtnChangeStatusOnClick(View view){
        String subject = edtTxtSubject.getText().toString().trim();


        if(!subject.isEmpty()) {
            txtResultStatus.setVisibility(View.GONE);

            String status = "Done";
            updateTaskBySbject(subject,status);

            Gson gson = new Gson();
            String tasksString = gson.toJson(Task.listTasks);

            editor.putString(DATA, tasksString);
            editor.commit();

            txtResultChange.setVisibility(View.VISIBLE);
            txtResultChange.setText("The Task Status Has Been Updated Successfully To Done \uD83D\uDE04");
        }
        else{
            txtResultChange.setVisibility(View.VISIBLE);
            txtResultChange.setText("Please Enter The Subject For Your Task \uD83D\uDE14");
        }
    }

    private void updateTaskBySbject(String subject , String status) {
        for(Task task : Task.listTasks){
            if(task.getSubject().equals(subject)){
                task.setStatus(status);
                break;
            }
        }
    }

}




