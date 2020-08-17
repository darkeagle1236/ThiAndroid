package com.example.thiandroid

import Data
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter(val users: List<Data>,var context: Context): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            var intent = Intent(context,DetailActivity::class.java)
            intent.putExtra("name",users[position].first_name +users[position].last_name)
            intent.putExtra("avatar",users[position].avatar)
            intent.putExtra("email",users[position].email)
            intent.putExtra("id",users[position].id)
            context.startActivity(intent)
        }
        return holder.bind(users[position])

    }

    class UserViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        private val photo: ImageView = itemView.ivThumb
        private val fullName: TextView = itemView.tvName

        fun bind(user: Data) {
            Picasso.get().load(user.avatar).into(photo)
            fullName.text = user.first_name + user.last_name
        }

    }
}
