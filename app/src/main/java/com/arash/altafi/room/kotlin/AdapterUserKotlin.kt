package com.arash.altafi.room.kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arash.altafi.room.R
import com.google.android.material.textview.MaterialTextView

class AdapterUserKotlin(val list: List<UserModel>, private var onClickItems: OnClickItems) :
    RecyclerView.Adapter<UserHolder>() {

    interface OnClickItems {
        fun onDelete(users: UserModel)
        fun onUpdate(users: UserModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserHolder(view)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(list[position])

        holder.itemView.setOnClickListener {
            onClickItems.onUpdate(list[position])
        }

        holder.itemView.setOnLongClickListener {
            onClickItems.onDelete(list[position])
            true
        }
    }

    override fun getItemCount(): Int = list.size

}

class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var txtName: MaterialTextView = itemView.findViewById(R.id.txt_name)
    private var txtFamily: MaterialTextView = itemView.findViewById(R.id.txt_family)
    private var txtAge: MaterialTextView = itemView.findViewById(R.id.txt_age)

    fun bind(users: UserModel) {
        txtAge.text = users.age.toString()
        txtName.text = users.name
        txtFamily.text = users.family
    }
}