package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/*
Author: Leon Fässler
Date: 23.02.2024
Description: This is a Fitness App where you can put in your training data. 
*/

public class AddActivity2 extends AppCompatActivity {

    EditText name_input, weight_input;
    Button addButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add2);

        name_input = findViewById(R.id.name_input);
        weight_input = findViewById(R.id.weight_input);
        addButton2 = findViewById(R.id.add_button2);
        addButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB2 = new MyDatabaseHelper(AddActivity2.this);
                myDB2.addUebungen(name_input.getText().toString().trim(),
                        weight_input.getText().toString().trim());
            }
        });
    }
}
