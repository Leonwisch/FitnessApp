package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView recyclerView2;
    FloatingActionButton add_button2;

    MyDatabaseHelper myDB2;
    ArrayList<String> uebung_id,uebung_name, uebung_weight;
    CustomAdapter2 customAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView2 = findViewById(R.id.recyclerView2);
        add_button2 = findViewById(R.id.add_Button2);
        add_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity2.this, AddActivity2.class);
                startActivity(intent2);
            }
        });

        myDB2 = new MyDatabaseHelper(MainActivity2.this);
        uebung_id = new ArrayList<>();
        uebung_name = new ArrayList<>();
        uebung_weight = new ArrayList<>();

        storeDataInArrays2();

        customAdapter2 = new CustomAdapter2(MainActivity2.this, uebung_name, uebung_weight);
        recyclerView2.setAdapter(customAdapter2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(MainActivity2.this));
    }

    void storeDataInArrays2(){
        Cursor cursor2 = myDB2.readAllDataUebung();
        if (cursor2.getCount() == 0){
            Toast.makeText(this, "Keine Daten.", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor2.moveToNext()){
                uebung_id.add(cursor2.getString(0));
                uebung_name.add(cursor2.getString(1));
                uebung_weight.add(cursor2.getString(2));
            }
        }
    }
}