package com.arash.altafi.room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.arash.altafi.room.java.MainActivityJava;
import com.arash.altafi.room.kotlin.MainActivityKotlin;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    MaterialButton btn_java, btn_kotlin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FindView();
        Listener();
    }

    private void FindView() {
        btn_java = findViewById(R.id.btnJava);
        btn_kotlin = findViewById(R.id.btnKotlin);
    }

    private void Listener() {
        btn_java.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, MainActivityJava.class)));
        btn_kotlin.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, MainActivityKotlin.class)));
    }

}