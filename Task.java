package com.freddie.to_do_list_app.Task;

public class


Task {
    private int taskId;
    private String taskName;
    private String taskDescription;
    private String Duedate;
    private String taskStatus;


    public static void setstatus(String status) {
    }


    public void setTId(int TId) {
        this.taskId = TId;
    }
    public int getTId() {
        return taskId;
    }


    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getDuedate() {
        return Duedate;
    }

    public void setDuedate(String duedate) {
        Duedate = duedate;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }
    public Task(){

    }

    @Override
    public String toString() {
        return "Task{" +
                "TId='" + taskId + '\'' +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", Duedate='" + Duedate + '\'' +
                ", taskStatus='" + taskStatus + '\'' +
                '}';
    }
}



