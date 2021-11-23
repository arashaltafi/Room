package com.arash.altafi.room.java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.arash.altafi.room.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class java_main extends AppCompatActivity implements Adapter_user.onClickItem {

    TextInputEditText edt_name , edt_family , edt_age;
    MaterialButton btn_show , btn_save , btn_update;
    RecyclerView recy_user;
    user_database database;
    Adapter_user adapter_user;
    user_dao dao;
    user_model model;
    List<user_model> modelList;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_main);

        model = new user_model();
        database = user_database.getDatabase(getApplicationContext());
        dao = database.dao();

        FindView();
        Listener();
    }

    private void FindView() {
        btn_save = findViewById(R.id.btn_save);
        btn_show = findViewById(R.id.btn_show);
        btn_update = findViewById(R.id.btn_update);
        edt_age = findViewById(R.id.edt_Age);
        edt_family = findViewById(R.id.edt_Family);
        edt_name = findViewById(R.id.edt_Name);
        recy_user = findViewById(R.id.recy_user);
    }

    private void Listener() {
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
            }
        });
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });
    }

    private void insert()
    {
        model.age = Integer.parseInt(edt_age.getText().toString().trim());
        model.name = edt_name.getText().toString().trim();
        model.family = edt_family.getText().toString().trim();
        dao.insert(model);
        edt_age.setText("");
        edt_family.setText("");
        edt_name.setText("");
        Toast.makeText(this, "ثبت نام با موفقیت انجام شد" , Toast.LENGTH_SHORT).show();
    }

    private void show()
    {
        modelList = database.dao().getAll();
        adapter_user = new Adapter_user(modelList,java_main.this);
        recy_user.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recy_user.setAdapter(adapter_user);
    }

    private void update()
    {
        model.id = id;
        model.age = Integer.parseInt(edt_age.getText().toString().trim());
        model.family = edt_family.getText().toString().trim();
        model.name = edt_name.getText().toString().trim();
        dao.update(model);
        Toast.makeText(this, "اطلاعات کاربر ویراش شد" , Toast.LENGTH_SHORT).show();
        edt_name.setText("");
        edt_family.setText("");
        edt_age.setText("");
        show();
    }

    @Override
    public void onDelete(user_model model) {
        Toast.makeText(this, "کاربر مورد نظر حذف شد", Toast.LENGTH_SHORT).show();
        dao.delete(model);
        show();
    }

    @Override
    public void onUpdate(user_model model) {
        id = model.id;
        edt_name.setText(model.name);
        edt_family.setText(model.family);
        edt_age.setText(String.valueOf(model.age));
    }
}