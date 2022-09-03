package com.example.dependencyinjectionexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.dependencyinjectionexample.adapter.PostAdapter.*
import com.example.dependencyinjectionexample.databinding.EachRowBinding
import com.example.dependencyinjectionexample.model.Post

class PostAdapter(private var postList: List<Post>) : RecyclerView.Adapter<ViewHolder>() {
    private lateinit var binding: EachRowBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = EachRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding.root)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        binding.tasks.text = postList[position].body

    }

    override fun getItemCount(): Int = postList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }
    fun setData(postList: List<Post>){
        this.postList = postList
        notifyDataSetChanged()
    }
}