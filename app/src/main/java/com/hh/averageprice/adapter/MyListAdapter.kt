package com.hh.averageprice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hh.averageprice.databinding.ListItemBinding
import com.hh.averageprice.network.model.Item
import com.hh.averageprice.network.model.Properties

class MyListAdapter (val clickListener: MyAdapterClickListener) :
    ListAdapter<Item, MyListAdapter.MyViewHolder>(MyDiffCallback()) {

        class MyViewHolder(private val binding: ListItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun bind(item: Item, clickListener: MyAdapterClickListener) {
                binding.item =item
                binding.click = clickListener
                binding.executePendingBindings()
            }

            companion object {
                fun from(parent: ViewGroup): MyViewHolder {
                    val layoutInflater = LayoutInflater.from(parent.context)
                    val binding = ListItemBinding.inflate(layoutInflater, parent, false)
                    return MyViewHolder(binding)
                }

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder.from(parent)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.bind(getItem(position), clickListener)
        }
    }

    class MyDiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.address == newItem.address
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

    }

    class MyAdapterClickListener(val clickListener: (properties: Item) -> Unit) {
        fun onclick(properties: Item) = clickListener(properties)
    }

