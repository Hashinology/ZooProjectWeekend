package com.example.hashi.zooproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hashi.zooproject.AnimalsDetailsActivity;

import com.example.hashi.zooproject.R;
import com.example.hashi.zooproject.model.AnimalsList;
import com.example.hashi.zooproject.viewholder.CategoryViewHolder;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

//    private String[] data;
    private Context context;
    private LayoutInflater inflater;
    private AnimalsList current;
    private List<AnimalsList> data;
    private CategoryViewHolder viewHolder;

    public CategoryAdapter(Context context, List<AnimalsList> data) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        this.context= context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View view = inflater.inflate(R.layout.item_view, parent, false);
//        return new CategoryViewHolder(view);

        View view = inflater.inflate(R.layout.item_view, parent, false);
        viewHolder = new CategoryViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryViewHolder holder, int position) {
//        String title = data[position];
//        holder.categoryName.setText(title);

        current = data.get(position);
        final String id = current.getId();
        final String name = current.getName();
        final String category = current.getCategory();
        final String detail = current.getDetail();

        viewHolder.categoryName.setText(name);

        //any animal name click will launch another activity
        //below code will share the data of that animal from this activity to AnimalsDetailsActivity
        //where getExtra will be called to receive data
        viewHolder.categoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AnimalsDetailsActivity.class);
                int pos=holder.getAdapterPosition();
                String id=data.get(pos).getId();
                String name=data.get(pos).getName();
                String category=data.get(pos).getCategory();
                String detail=data.get(pos).getDetail();
                intent.putExtra("ANIMAL_ID", id);
                intent.putExtra("ANIMAL_NAME", name);
                intent.putExtra("ANIMAL_CATEGORY", category);
                intent.putExtra("ANIMAL_DETAIL", detail);
                context.startActivity( intent );
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
