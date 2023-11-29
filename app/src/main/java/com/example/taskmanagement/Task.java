package com.example.taskmanagement;

import java.util.ArrayList;

public class Task {
    private String subject;
    private String status;
    public static ArrayList<Task> listTasks = new ArrayList<>() ;

    public Task() {}

    public Task(String subject, String status) {
        this.subject = subject;
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status.equals("Done") || status.equals("Due")) {
            this.status = status;
        } else {
            System.out.println("Invalid status. Status should be either 'Done' or 'Due'.");
        }
    }

    @Override
    public String toString() {
        return "Task : " +
                "subject is '" + subject + '\'' +
                ", status is '" + status + '\'' ;
    }
}

