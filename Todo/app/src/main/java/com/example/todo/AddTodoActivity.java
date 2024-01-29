package com.example.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Objects;

public class AddTodoActivity extends AppCompatActivity {

    Button btnAddTodo;
    Button btnCancel;

    Spinner spinnerUrgency;

    EditText todoName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Create Todo");

        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);

        btnAddTodo = findViewById(R.id.add_button);
        btnCancel = findViewById(R.id.cancel_button);

        spinnerUrgency = findViewById(R.id.urgency_spinner);

        todoName = findViewById(R.id.todo_text);

        btnAddTodo.setOnClickListener(v -> {
            String name = todoName.getText().toString();
            if (name.isEmpty() || name.length() < 3) {
                todoName.setError("Please enter a name");
                return;
            }

            String urgency = spinnerUrgency.getSelectedItem().toString();
            Todo todo = new Todo();
            todo.setName(name);
            todo.setUrgency(urgency);

            TodoDatabase.getDbInstance(this).getTodoDAO().add(todo);

            finish();
        });

        btnCancel.setOnClickListener(v -> finish());

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}