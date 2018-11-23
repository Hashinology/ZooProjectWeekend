package com.example.hashi.zooproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.hashi.zooproject.adapter.NothingSelectedSpinnerAdapter;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerCategory;
    private EditText etAnimalDetails, etAnimalName;
    private Button btnAdd;
    private String name, detail, category;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

    }

    //This method will call when you click Vist Zoo button
    // and will launch another activity that has major categories of the animals
    public void goToMajorCategries(View view) {
        Intent intent = new Intent(MainActivity.this, MajorCategoriesActivity.class);
        startActivity(intent);
    }

    //This method will add the animal record into the SQLite if all the fields are fill
    public void addAnimalFromAlert() {

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(detail) && !TextUtils.isEmpty(category)) {
            AppDataBase appDataBase = new AppDataBase(context);
            appDataBase.open();
            appDataBase.insertAnimal(name, category, detail);
            appDataBase.close();
        } else {
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
        }
    }

    //here spinner will be populated with default categories define in res >> values >> arrays.xml
    private void loadCategories() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setPrompt("Select category of the animal");

        spinnerCategory.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.contact_spinner_row_nothing_selected,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));
    }

    //this method will be called when ADD ANIMAL RECORD button will be pressed
    //it will launch a layout as an Alert
    //by pressing Add button addAnimalFromAlert() will be called to add the record into SQLite
    public void addAnimal(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.add_animal);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        spinnerCategory = alertDialog.findViewById(R.id.spinnerCategory);
        etAnimalDetails = alertDialog.findViewById(R.id.etAnimalDetails);
        etAnimalName = alertDialog.findViewById(R.id.etAnimalName);
        loadCategories();

        btnAdd = alertDialog.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etAnimalName.getText().toString();
                detail = etAnimalDetails.getText().toString();
                category = spinnerCategory.getSelectedItem().toString();
                addAnimalFromAlert();
                Toast.makeText(context, name+" added in the database", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
    }
}
