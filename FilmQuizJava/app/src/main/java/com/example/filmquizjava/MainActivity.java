package com.example.filmquizjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    public static final String KEY_INDEX = "index";
    public static final String KEY_SCORE = "score";

    public static final String KEY_QUESTION = "question";

    final int[] score = {0};

    final int[] index = {0};




    Question[] questions;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);

        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);

        questions = new Question[]{
                new Question(getString(R.string.question_ai), true),
                new Question(getString(R.string.question_taxi_driver), true),
                new Question(getString(R.string.question_2001), false),
                new Question(getString(R.string.question_reservoir_dogs), true),
                new Question(getString(R.string.question_citizen_kane), false)
        };


        if (savedInstanceState != null) {
            index[0] = savedInstanceState.getInt(KEY_INDEX);
            score[0] = savedInstanceState.getInt(KEY_SCORE);
        }

        Button buttonFalse = findViewById(R.id.button_false);
        buttonFalse.setText(R.string.button_false);
        Button buttonTrue = findViewById(R.id.button_true);
        buttonTrue.setText(R.string.button_true);
        Button buttonReload = findViewById(R.id.button_reload);
        buttonReload.setText(R.string.button_reload);
        buttonReload.setVisibility(View.INVISIBLE);

        TextView tvQuestion = findViewById(R.id.tv_question);
        TextView tvScore = findViewById(R.id.tv_score);
        tvScore.setText("Score: " + score[0]);

        tvQuestion.setText(questions[index[0]].getText());

        buttonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check answer
                boolean answer = questions[index[0]].checkAnswer(false);
                if (answer) {
                    Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                    //add 1 to score
                    score[0] += 1;
                    tvScore.setText("Score: " + score[0]);
                } else {
                    Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_SHORT).show();
                }
                if (index[0] == questions.length - 1) {
                    buttonReload.setVisibility(View.VISIBLE);
                    buttonFalse.setVisibility(View.INVISIBLE);
                    buttonTrue.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "You have reached the end of the quiz", Toast.LENGTH_SHORT).show();
                } else {
                    index[0] += 1;
                    tvQuestion.setText(questions[index[0]].getText());
                }
            }
        });
        buttonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answer = questions[index[0]].checkAnswer(true);
                if (answer) {
                    Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                    //add 1 to score
                    score[0] += 1;
                    tvScore.setText("Score: " + score[0]);
                } else {
                    Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_SHORT).show();
                }
                if (index[0] == questions.length - 1) {
                    buttonReload.setVisibility(View.VISIBLE);
                    buttonFalse.setVisibility(View.INVISIBLE);
                    buttonTrue.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "You have reached the end of the quiz", Toast.LENGTH_SHORT).show();
                } else {
                    index[0] += 1;
                    tvQuestion.setText(questions[index[0]].getText());
                }
            }
        });

        buttonReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonReload.setVisibility(View.INVISIBLE);
                index[0] = 0;
                score[0] = 0;
                tvQuestion.setText(questions[index[0]].getText());
                tvScore.setText("Score: " + score[0]);
            }
        });



    }
    @Override
    protected void onSaveInstanceState (@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, index[0]);
        outState.putInt(KEY_SCORE, score[0]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.cheat) {
            Intent intent = new Intent(this, CheatActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(KEY_QUESTION, questions[index[0]]);
            intent.putExtras(bundle);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}

class Question implements Serializable {
    int id;
    String text;
    boolean answer;

    public Question(String text, boolean answer) {
        this.text = text;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public boolean checkAnswer(boolean answer) {
        return this.answer == answer;
    }
}