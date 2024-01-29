package com.example.todo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvTodos;
    private List<Todo> todoList;
    private TodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Todo");
    }

    @Override
    protected void onStart() {
        rvTodos = findViewById(R.id.rvTodos);

        rvTodos.setHasFixedSize(true);
        rvTodos.setLayoutManager(new LinearLayoutManager(this));

        adapter = new TodoAdapter(TodoDatabase.getDbInstance(this).getTodoDAO().findAll());
        rvTodos.setAdapter(adapter);

        loadTodos();
        super.onStart();
    }


    @SuppressLint("NotifyDataSetChanged")
    private void loadTodos() {
        // This is where you would fetch your data from the TodoDatabase and update the adapter
        // For example:
        TodoDatabase db = TodoDatabase.getDbInstance(this.getApplicationContext());
        todoList = db.getTodoDAO().findAll(); // Assuming you have a method to get all todos
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.createTodo) {
            // Start the ActivityAddTodo when the menu item is selected
            Intent intent = new Intent(this, AddTodoActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);

    }
}