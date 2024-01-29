package com.example.filmquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState != null){
            val score = savedInstanceState.getInt("score")
            Toast.makeText(this, "Score: $score", Toast.LENGTH_SHORT).show()
        }


        val buttonFalse = findViewById<Button>(R.id.button_false)
        val buttonTrue = findViewById<Button>(R.id.button_true)

        buttonFalse.setOnClickListener {
            Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show()
        }

        buttonTrue.setOnClickListener {
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
        }

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.cheat){
            val intent = Intent(this, CheatActivity::class.java)


            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}