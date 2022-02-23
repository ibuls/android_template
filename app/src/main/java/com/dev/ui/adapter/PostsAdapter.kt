package com.dev.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dev.core.models.Children
import com.dev.databinding.ItemPostsBinding

class PostsAdapter(val context:Context):PagingDataAdapter<Children,PostsAdapter.ItemVH>(PostsComparator) {

    var listData:ArrayList<Children> = ArrayList()

    inner class ItemVH(val binding: ItemPostsBinding):RecyclerView.ViewHolder(binding.root){
        fun setData(){
            val data = listData[bindingAdapterPosition]
            binding.tvTitle.text = data.data.name
        }
    }


    object PostsComparator: DiffUtil.ItemCallback<Children>(){
        override fun areItemsTheSame(oldItem: Children, newItem: Children): Boolean {
            return oldItem.data.id == newItem.data.id
        }

        override fun areContentsTheSame(oldItem: Children, newItem: Children): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: ItemVH, position: Int) {
        holder.setData()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {
        val inflator = LayoutInflater.from(context)
        return ItemVH(ItemPostsBinding.inflate(inflator))
    }
}