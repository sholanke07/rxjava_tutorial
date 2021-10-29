package com.lateef.rxjavatutorial.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lateef.rxjavatutorial.R
import com.lateef.rxjavatutorial.model.Post

class PostAdapter(internal var context: Context, internal var postList:List<Post>) : RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.post_layout, parent, false)

        return PostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.txt_author.text = postList[position].userId.toString()
        holder.txt_title.text = postList[position].title
        holder.txt_content.text = (postList[position].body.substring(0,20))
            .apply { "..." }.toString()
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}