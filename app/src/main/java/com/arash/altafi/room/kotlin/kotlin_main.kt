package com.arash.altafi.room.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arash.altafi.kotlin.room.Adapter_user_kt
import com.arash.altafi.room.R
import com.arash.altafi.room.java.Adapter_user
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class kotlin_main : AppCompatActivity() {

    var edt_name: TextInputEditText?= null
    var edt_family: TextInputEditText?= null
    var edt_age: TextInputEditText?= null
    var btn_save: MaterialButton?= null
    var btn_show: MaterialButton?= null
    var btn_update: MaterialButton?= null
    var database:user_database ?= null
    var dao:user_dao ?= null
    var model:user_model ?= null
    var recy_user: RecyclerView?= null
    var adapter: Adapter_user_kt?= null
    var list:List<user_model> ?= null
    var id:Int ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_main)

        model = user_model()
        database = user_database.getAppDataBase(this)
        dao = database?.UserDao()

        FindView()
        Listener()
    }

    fun FindView()
    {
        btn_save = findViewById(R.id.btn_save)
        btn_show = findViewById(R.id.btn_show)
        btn_update = findViewById(R.id.btn_update)
        edt_family = findViewById(R.id.edt_Family)
        edt_name = findViewById(R.id.edt_Name)
        edt_age = findViewById(R.id.edt_Age)
        recy_user = findViewById(R.id.rc_data)
    }

    fun Listener()
    {
        btn_save?.setOnClickListener { insert() }

        btn_show?.setOnClickListener { show() }

        btn_update?.setOnClickListener { update() }
    }

    fun insert()
    {
        model?.age = edt_age?.text.toString().toInt()
        model?.name = edt_name?.text.toString()
        model?.family = edt_family?.text.toString()
        dao?.insert(model!!)
        Toast.makeText(this , "ثبت نام با موفقیت انجام شد" , Toast.LENGTH_SHORT).show()
        edt_family?.text  = null
        edt_name?.text  = null
        edt_age?.text  = null
    }

    fun show()
    {
        list = dao?.getAll()
        recy_user?.layoutManager = LinearLayoutManager(this)
        adapter = Adapter_user_kt(list!!,object :Adapter_user_kt.OnClickItems{
            override fun onDelete(users: user_model) {
                Toast.makeText(this@kotlin_main , "کاربر مورد نظر حذف شد" , Toast.LENGTH_SHORT).show()
                dao?.delete(users)
                show()
            }

            override fun onUpdate(users: user_model) {
                id = users.id
                edt_age?.setText(users.age.toString())
                edt_name?.setText(users.name.toString())
                edt_family?.setText(users.family.toString())
            }
        })
        recy_user?.adapter = adapter
    }

    fun update()
    {
        model?.id = id
        model?.age = edt_age?.text.toString().toInt()
        model?.name = edt_name?.text.toString()
        model?.family = edt_family?.text.toString()
        dao?.update(model!!)
        Toast.makeText(this , "اطلاعات کاربر ویراش شد" , Toast.LENGTH_SHORT).show()
        edt_family?.text  = null
        edt_name?.text  = null
        edt_age?.text  = null
        show()
    }

}