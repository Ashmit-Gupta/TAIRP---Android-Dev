package com.ashmit.to_dolist.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashmit.to_dolist.AddNewTask;
import com.ashmit.to_dolist.MainActivity;
import com.ashmit.to_dolist.Model.TodoModel;
import com.ashmit.to_dolist.R;

import java.util.List;

import Utils.DatabaseHandler;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private List<TodoModel> todoList;
    private MainActivity activity;
    private DatabaseHandler db;

    public TodoAdapter(DatabaseHandler db , MainActivity activity) {
        this.db = db;
        this.activity = activity;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout , parent , false);
        return new ViewHolder(itemView);
    }


    public void onBindViewHolder(ViewHolder holder, int position) {
        db.openDatabase();
        TodoModel item = todoList.get(position);
        holder.task.setText(item.getTask());
        holder.task.setChecked(toBoolean(item.getStatus()));
        holder.task.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        db.updateStatus(item.getId() , 1);
                    }
                    else{
                        db.updateStatus(item.getId() , 0);
                    }
            }
        });
    }


    private boolean toBoolean(int n){
        return n!=0;
    }

    public void setTask(List<TodoModel>todoList){
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    public Context getContext(){
        return activity;
    }

    public void deleteItem(int position){
        TodoModel item = todoList.get(position);
        db.deleteTasks(item.getId());
        todoList.remove(position);
        notifyItemRemoved(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox task;
        ViewHolder(View view){
            super(view);
            task = view.findViewById(R.id.todoCheckBox);
        }
    }


    public int getItemCount(){
            return todoList.size();
    }

//    public void setTasks(List<TodoModel> tododList){
//        this.todoList = tododList;
//    }

    public void editItem(int position){
        TodoModel item = todoList.get(position);
         Bundle bundle = new Bundle();
         bundle.putInt("id" ,item.getId());
         bundle.putString("task", item.getTask());
         AddNewTask fragment = new AddNewTask();
         fragment.setArguments(bundle);
         fragment.show(activity.getSupportFragmentManager() , AddNewTask.TAG);
    }
}
