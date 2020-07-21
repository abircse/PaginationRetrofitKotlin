package com.example.recyclerviewpagination.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.recyclerviewpagination.R
import com.example.recyclerviewpagination.model.Data

class PaginationAdapter(private val list: MutableList<Data>?, private val context: Context) : RecyclerView.Adapter<PaginationAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_pagination, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.name.text = list!![position].name
        holder.phone.text = list[position].phone
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    class Holder(itemView: View) : ViewHolder(itemView) {
        var name: TextView
        var phone: TextView

        init {
            name = itemView.findViewById(R.id.name)
            phone = itemView.findViewById(R.id.phone)
        }
    }

    fun addpeople(data: List<Data>) {
        for (d in data) {
            list!!.add(d)
        }
        notifyDataSetChanged()
    }

}