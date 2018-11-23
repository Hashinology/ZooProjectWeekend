package com.example.hashi.zooproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.example.hashi.zooproject.adapter.CategoryAdapter;
import com.example.hashi.zooproject.model.AnimalsList;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity {

    private ArrayList<AnimalsList> animalsLists;
    private CategoryAdapter categoryAdapter;
    private RecyclerView recyclerView;
    private Context context;

    private String category;

//    String[] categoriesMammal = {"Lion", "Cow"};
//    String[] categoriesBird = {"Sparrow", "Pigeon"};
//    String[] categoriesAmphibion = {"Frog"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        context = CategoriesActivity.this;

        Intent intent = getIntent();
        category = intent.getStringExtra("CATEGORY");

        //the below code will populate recycler view with the data given in adapter through SQLite
        animalsLists = new ArrayList<>();
        recyclerView  = findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(recyclerView.getContext(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        AppDataBase appDataBase = new AppDataBase(context);
        appDataBase.open();
        categoryAdapter = new CategoryAdapter(context, appDataBase.getAnimals(category));
        recyclerView.setAdapter(categoryAdapter);
        appDataBase.close();

//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        if (category.equalsIgnoreCase("mammal")){
//            recyclerView.setAdapter(new CategoryAdapter(categoriesMammal));
//        }
//        if (category.equalsIgnoreCase("bird")){
//            recyclerView.setAdapter(new CategoryAdapter(categoriesBird));
//        }
//        if (category.equalsIgnoreCase("amphibion")){
//            recyclerView.setAdapter(new CategoryAdapter(categoriesAmphibion));
//        }
    }
}
