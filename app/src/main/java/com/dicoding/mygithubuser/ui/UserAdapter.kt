package com.dicoding.mygithubuser.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.mygithubuser.data.response.ItemsItem
import com.dicoding.mygithubuser.databinding.ItemUserBinding
import com.dicoding.mygithubuser.ui.DetailUser.Companion.KEY_NAME

class UserAdapter : ListAdapter<ItemsItem, UserAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)

        //INTENT ONCLICK KE HALAMAN LAIN
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intentDetail = Intent(context, DetailUser::class.java)
            intentDetail.putExtra(KEY_NAME, user.login) // Mengirim data yang diperlukan ke DetailActivity
            context.startActivity(intentDetail)
        }

    }
    class MyViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ItemsItem){
            binding.tvItem.text = "${user.login}"
            Glide.with(itemView)
                .load(user.avatarUrl)
                .into(binding.imgPhoto)
            binding.tvId.text = "${user.id}"
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }


}


