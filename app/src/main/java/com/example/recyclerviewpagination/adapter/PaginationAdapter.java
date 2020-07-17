package com.example.recyclerviewpagination.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.recyclerviewpagination.R;
import com.example.recyclerviewpagination.model.Data;

import java.util.List;

public class PaginationAdapter extends RecyclerView.Adapter<PaginationAdapter.Holder>{

    private List<Data> list;
    private Context context;

    public PaginationAdapter(List<Data> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_pagination,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.name.setText(list.get(position).getName());
        holder.phone.setText(list.get(position).getPhone());
    }

    @Override
    public int getItemCount() {

        if (list == null) {
            return 0;
        }
        return list.size();
    }

    static class Holder extends RecyclerView.ViewHolder{

        TextView name,phone;
        Holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
        }
    }

    public void addpeople(List<Data> data)
    {
        for (Data d : data)
        {
            list.add(d);
        }
        notifyDataSetChanged();
    }


}
