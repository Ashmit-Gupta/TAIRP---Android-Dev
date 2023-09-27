package com.ashmit.to_dolist;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.ashmit.to_dolist.Adapter.TodoAdapter;
import com.ashmit.to_dolist.Model.TodoModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Utils.DatabaseHandler;

public class MainActivity extends AppCompatActivity implements DialogCloseListener {
    private RecyclerView taskRecyclerView;
    private TodoAdapter tasksAdapter;
    private List<TodoModel> taskLists;

    private DatabaseHandler db;

    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null){
        assert actionBar != null;
        actionBar.setTitle("Todo List");
        actionBar.setIcon(R.drawable.todolist);
//        }
        db = new DatabaseHandler(this);
        db.openDatabase();
        taskLists = new ArrayList<>();

        taskRecyclerView = findViewById(R.id.tasksRecyclerView);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        tasksAdapter = new TodoAdapter(db, this);
        taskRecyclerView.setAdapter(tasksAdapter);


//        TodoModel task = new TodoModel();
//        task.setTask("This is a test task");
//        task.setStatus(0);
//        task.setId(1);

//        taskLists.add(task);
//        taskLists.add(task);
//        taskLists.add(task);
//        taskLists.add(task);
//        taskLists.add(task);
//
//        tasksAdapter.setTask(taskLists);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerItemTouchHelper(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(taskRecyclerView);

        fab = findViewById(R.id.fab);

        taskLists = db.getAllTasks();
        Collections.reverse(taskLists);
        tasksAdapter.setTask(taskLists);

        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AddNewTask.newInstance().show(getSupportFragmentManager() , AddNewTask.TAG);

            }
        });
    }
    @Override
    public void handleDialogClose(DialogInterface dialog){
        taskLists = db.getAllTasks();
        Collections.reverse(taskLists);
        tasksAdapter.setTask(taskLists);
        tasksAdapter.notifyDataSetChanged();

    }
}


