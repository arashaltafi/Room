package com.arash.altafi.room.kotlin

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arash.altafi.room.R
import com.arash.altafi.room.utils.WindowInsetsHelper
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivityKotlin : AppCompatActivity() {

    private var edtName: TextInputEditText? = null
    private var edtFamily: TextInputEditText? = null
    private var edtAge: TextInputEditText? = null
    private var age: TextInputLayout? = null
    private var btnSave: MaterialButton? = null
    private var btnShow: MaterialButton? = null
    private var btnUpdate: MaterialButton? = null
    private var database: UserDatabase? = null
    private var dao: UserDao? = null
    private var model: UserModel? = null
    private var recyUser: RecyclerView? = null
    private var adapter: AdapterUserKotlin? = null
    private var list: List<UserModel>? = null
    private var id: Int? = null
    private var root: ConstraintLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_main)

        model = UserModel()
        database = UserDatabase.getAppDataBase(this)
        dao = database?.UserDao()

        handleKeyboardSize()
        findView()
        listener()
    }

    private fun handleKeyboardSize() {
        val windowInsetsHelper = WindowInsetsHelper(window, root)
        windowInsetsHelper.isFullScreen = true
        windowInsetsHelper.isAutoResizeKeyboard = true
    }

    private fun findView() {
        root = findViewById(R.id.root)
        btnSave = findViewById(R.id.btn_save)
        btnShow = findViewById(R.id.btn_show)
        btnUpdate = findViewById(R.id.btn_update)
        edtFamily = findViewById(R.id.edt_Family)
        edtName = findViewById(R.id.edt_Name)
        edtAge = findViewById(R.id.edt_Age)
        age = findViewById(R.id.age)
        recyUser = findViewById(R.id.rc_data)
    }

    private fun listener() {
        btnSave?.setOnClickListener {
            val name = edtName?.text.toString().trim() == ""
            val family = edtFamily?.text.toString().trim() == ""
            val age = edtAge?.text.toString().trim() == ""
            if (!name && !family && !age) {
                insert()
            } else {
                Toast.makeText(this, "Please Complete!!!", Toast.LENGTH_SHORT).show()
            }
        }

        btnUpdate?.setOnClickListener {
            val name = edtName?.text.toString().trim() == ""
            val family = edtFamily?.text.toString().trim() == ""
            val age = edtAge?.text.toString().trim() == ""
            if (!name && !family && !age) {
                update()
            } else {
                Toast.makeText(this, "Please Complete!!!", Toast.LENGTH_SHORT).show()
            }
        }

        btnShow?.setOnClickListener { show() }

        age?.editText!!.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    btnSave?.performClick()
                }
            }
            false
        }
    }

    private fun insert() {
        model?.age = edtAge?.text.toString().toInt()
        model?.name = edtName?.text.toString()
        model?.family = edtFamily?.text.toString()
        dao?.insert(model!!)
        edtFamily?.text = null
        edtName?.text = null
        edtAge?.text = null
        Toast.makeText(this, "Registration was successful.", Toast.LENGTH_SHORT).show()
    }

    fun show() {
        list = dao?.getAll()
        recyUser?.layoutManager = LinearLayoutManager(this)
        adapter = AdapterUserKotlin(list!!, object : AdapterUserKotlin.OnClickItems {
            override fun onDelete(users: UserModel) {
                Toast.makeText(
                    this@MainActivityKotlin,
                    "The desired user was deleted.",
                    Toast.LENGTH_SHORT
                ).show()
                dao?.delete(users)
                show()
            }

            override fun onUpdate(users: UserModel) {
                id = users.id
                edtAge?.setText(users.age.toString())
                edtName?.setText(users.name.toString())
                edtFamily?.setText(users.family.toString())
            }
        })
        recyUser?.adapter = adapter
    }

    private fun update() {
        model?.id = id
        model?.age = edtAge?.text.toString().toInt()
        model?.name = edtName?.text.toString()
        model?.family = edtFamily?.text.toString()
        dao?.update(model!!)
        Toast.makeText(this, "User information has been edited", Toast.LENGTH_SHORT).show()
        edtFamily?.text = null
        edtName?.text = null
        edtAge?.text = null
        show()
    }

}