package com.arash.altafi.kotlin.room

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arash.altafi.room.R
import com.arash.altafi.room.kotlin.user_model
import java.security.AccessController.getContext

class  Adapter_user_kt (val list:List<user_model>,var onClickItems: OnClickItems) : RecyclerView.Adapter<userHolder>() {

    interface OnClickItems {
        fun onDelete(users: user_model)
        fun onUpdate(users: user_model)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
        return userHolder(view)
    }

    override fun onBindViewHolder(holder: userHolder, position: Int) {
        holder.bind(list.get(position))

        holder.itemView.setOnClickListener {
            onClickItems.onUpdate(list.get(position))
        }

        holder.itemView.setOnLongClickListener {
            onClickItems.onDelete(list.get(position))
            true
        }
    }

    override fun getItemCount(): Int = list.size

}

class userHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var txt_name = itemView.findViewById<TextView>(R.id.txt_name)
    var txt_family = itemView.findViewById<TextView>(R.id.txt_family)
    var txt_age = itemView.findViewById<TextView>(R.id.txt_age)

    fun bind(users:user_model) {
        txt_age.text = users.age.toString()
        txt_name.text = users.name
        txt_family.text = users.family
    }
}