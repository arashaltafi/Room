package com.arash.altafi.room.java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.arash.altafi.room.R;
import com.arash.altafi.room.utils.WindowInsetsHelper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Objects;

public class MainActivityJava extends AppCompatActivity implements AdapterUserJava.onClickItem {

    TextInputEditText edt_name, edt_family, edt_age;
    TextInputLayout age;
    MaterialButton btn_show, btn_save, btn_update;
    RecyclerView recy_user;
    userDatabase database;
    AdapterUserJava adapter_user;
    userDao dao;
    userModel model;
    List<userModel> modelList;
    int id;
    ConstraintLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_main);

        model = new userModel();
        database = userDatabase.getDatabase(getApplicationContext());
        dao = database.dao();

        handleKeyboardSize();
        findView();
        listener();
    }

    private void handleKeyboardSize() {
        WindowInsetsHelper windowInsetsHelper = new WindowInsetsHelper(getWindow(), root);
        windowInsetsHelper.setFullScreen(true);
        windowInsetsHelper.setAutoResizeKeyboard(true);
    }

    private void findView() {
        root = findViewById(R.id.root);
        btn_save = findViewById(R.id.btn_save);
        btn_show = findViewById(R.id.btn_show);
        btn_update = findViewById(R.id.btn_update);
        edt_age = findViewById(R.id.edt_Age);
        edt_family = findViewById(R.id.edt_Family);
        edt_name = findViewById(R.id.edt_Name);
        age = findViewById(R.id.age);
        recy_user = findViewById(R.id.recy_user);
    }

    private void listener() {
        btn_save.setOnClickListener(view -> {
            boolean name = edt_name.getText().toString().trim().equals("");
            boolean family = edt_family.getText().toString().trim().equals("");
            boolean age = edt_age.getText().toString().trim().equals("");
            if (!name && !family && !age) {
                insert();
            } else {
                Toast.makeText(this, "Please Complete!!!", Toast.LENGTH_SHORT).show();
            }
        });

        btn_update.setOnClickListener(view -> {
            boolean name = edt_name.getText().toString().trim().equals("");
            boolean family = edt_family.getText().toString().trim().equals("");
            boolean age = edt_age.getText().toString().trim().equals("");
            if (!name && !family && !age) {
                update();
            } else {
                Toast.makeText(this, "Please Complete!!!", Toast.LENGTH_SHORT).show();
            }
        });

        btn_show.setOnClickListener(view -> show());

        age.getEditText().setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEND) {
                btn_save.performClick();
            }
            return false;
        });
    }

    private void insert() {
        model.age = Integer.parseInt(Objects.requireNonNull(edt_age.getText()).toString().trim());
        model.name = Objects.requireNonNull(edt_name.getText()).toString().trim();
        model.family = Objects.requireNonNull(edt_family.getText()).toString().trim();
        dao.insert(model);
        edt_age.setText("");
        edt_family.setText("");
        edt_name.setText("");
        Toast.makeText(this, "Registration was successful.", Toast.LENGTH_SHORT).show();
    }

    private void show() {
        modelList = database.dao().getAll();
        adapter_user = new AdapterUserJava(modelList, MainActivityJava.this);
        recy_user.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recy_user.setAdapter(adapter_user);
    }

    private void update() {
        model.id = id;
        model.age = Integer.parseInt(Objects.requireNonNull(edt_age.getText()).toString().trim());
        model.family = Objects.requireNonNull(edt_family.getText()).toString().trim();
        model.name = Objects.requireNonNull(edt_name.getText()).toString().trim();
        dao.update(model);
        Toast.makeText(this, "User information has been edited", Toast.LENGTH_SHORT).show();
        edt_name.setText("");
        edt_family.setText("");
        edt_age.setText("");
        show();
    }

    @Override
    public void onDelete(userModel model) {
        Toast.makeText(this, "The desired user was deleted.", Toast.LENGTH_SHORT).show();
        dao.delete(model);
        show();
    }

    @Override
    public void onUpdate(userModel model) {
        id = model.id;
        edt_name.setText(model.name);
        edt_family.setText(model.family);
        edt_age.setText(String.valueOf(model.age));
    }
}