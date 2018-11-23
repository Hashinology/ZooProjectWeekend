package com.example.hashi.zooproject.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.hashi.zooproject.R;


public class CategoryViewHolder extends RecyclerView.ViewHolder {

    public TextView categoryName;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        categoryName = itemView.findViewById(R.id.tvMajorCat);
    }
}
