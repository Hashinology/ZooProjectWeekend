package com.example.hashi.zooproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.example.hashi.zooproject.adapter.CustomListAdapter;

public class MajorCategoriesActivity extends AppCompatActivity {

    //this string will populate the listView's items's textviews
    public String[] nameArray = {"Mammal", "Bird", "Amphibian"};;
    private ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major_categories);


        CustomListAdapter adapter = new CustomListAdapter(this, nameArray);
        listView = findViewById(R.id.listviewID);
        listView.setAdapter(adapter);

        //on any item click in the list view will take you to another activity having
        //animals related to that category
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MajorCategoriesActivity.this, CategoriesActivity.class);
                String category = nameArray[position];
                intent.putExtra("CATEGORY", category );
                startActivity(intent);
            }
        });
    }
}
