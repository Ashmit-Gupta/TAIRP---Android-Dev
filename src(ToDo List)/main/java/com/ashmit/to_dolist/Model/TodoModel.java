package com.ashmit.to_dolist.Model;

public class TodoModel {
    private int id , status;// id is used to uniquely identify the each and every tasks and status is storing o -> false and 1 -> true and we cant give this as boolean because the values in the further code is not able to take boolean value
    private String task;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
