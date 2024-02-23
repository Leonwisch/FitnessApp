package com.example.fitnessapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.view.Menu;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/*
Author: Leon Fässler
Date: 23.02.2024
Description: This is a Fitness App where you can put in your training data. 
*/

public class MainActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDatabaseHelper myDB;
    ArrayList<String> fitness_id, fitness_date;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_Button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        fitness_id = new ArrayList<>();
        fitness_date = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this, fitness_date, fitness_id);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllDataTraining();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "Keine Daten.", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                fitness_id.add(cursor.getString(0));
                fitness_date.add(cursor.getString(1));
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete_all) {
            myDB.deleteAllData();
            recreate();
        }
        return super.onOptionsItemSelected(item);
    }}
