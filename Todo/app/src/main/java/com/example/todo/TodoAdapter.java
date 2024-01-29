package com.example.todo;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private List<Todo> todoList; // Assuming Todo is the name of your data class

    public TodoAdapter(List<Todo> todoList) {
        this.todoList = todoList;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_item, parent, false);
        return new TodoViewHolder(itemView);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todo = todoList.get(position);
        holder.tvName.setText(todo.getName());
        holder.tvUrgency.setText(todo.getUrgency());
        holder.tvBtnDelete.setOnClickListener(v -> {
            TodoDatabase.getDbInstance(v.getContext()).getTodoDAO().delete(todo);
            todoList.remove(todo);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, todoList.size());
        });
    }


    @Override
    public int getItemCount() {
        return todoList.size();
    }

    static class TodoViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvUrgency;

        public Button tvBtnDelete;
        // Other views for the todo item

        TodoViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tvName);
            tvUrgency = view.findViewById(R.id.tvUrgency);
            tvBtnDelete = view.findViewById(R.id.tvBtnDelete);
            // Initialize other views for the todo item
        }
    }
}
