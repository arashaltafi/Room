package com.arash.altafi.room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.arash.altafi.room.java.java_main;
import com.arash.altafi.room.kotlin.kotlin_main;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    MaterialButton btn_java , btn_kotlin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FindView();
        Listener();
    }

    private void FindView() {
        btn_java = findViewById(R.id.btn_java);
        btn_kotlin = findViewById(R.id.btn_kotlin);
    }

    private void Listener() {
        btn_java.setOnClickListener(view -> startActivity(new Intent(MainActivity.this , java_main.class)));
        btn_kotlin.setOnClickListener(view -> startActivity(new Intent(MainActivity.this , kotlin_main.class)));
    }

}