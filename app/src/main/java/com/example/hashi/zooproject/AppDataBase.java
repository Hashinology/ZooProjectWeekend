package com.example.hashi.zooproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.hashi.zooproject.model.AnimalsList;

import java.util.ArrayList;

public class AppDataBase {
    private Context context;
    private SQLiteDatabase sqLiteDatabase;
    private HshiDataBase HashiDataBase;
    int total;

    public AppDataBase(Context context) {
        this.context = context;
    }

    /*DATA BASE NAME AND VERSION , CHANGE VERSION WHILE CHNAGE IN TABLE*/
    private static final String DATA_BASE_NAME = "zoo.db";
    private static final int DATA_BASE_VERSION = 1;

    /*  1 table name */
    private static final String TABLE_NAME_REGISTER = "register";

    /*2 columns of table*/
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_DETAIL = "detail";

    /* 3to drop a table*/
    /**/
    private static final String DROP_TABLE_REGISTER = "DROP TABLE IF EXISTS " + TABLE_NAME_REGISTER;

    /* 4 to creta a table*/

    private static final String CREATE_TABLE_REGISTER =
            "CREATE TABLE " + TABLE_NAME_REGISTER + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME + " TEXT ," +
                    COLUMN_CATEGORY + " TEXT ," +
                    COLUMN_DETAIL + " TEXT)";


    /*5 th*/
    public long insertAnimal(String name, String category, String detail) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_CATEGORY, category);
        contentValues.put(COLUMN_DETAIL, detail);

        long insertedId = sqLiteDatabase.insert(TABLE_NAME_REGISTER, null, contentValues);
        return insertedId;
    }

    public ArrayList<AnimalsList> getAnimals(String cat) {

        ArrayList<AnimalsList> myList = new ArrayList<>();

//        String query = "SELECT * FROM TABLE_NAME_REGISTER WHERE category =" + cat;
//        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

//        Cursor cursor = sqLiteDatabase.query(TABLE_NAME_REGISTER, null, null, null, null, null, null);

        Cursor cursor = sqLiteDatabase.query("register", null, "category=?", new String[] { cat }, null, null, null);

        while (cursor.moveToNext()) {

            String id = cursor.getString(cursor.getColumnIndex(COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String category = cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY));
            String detail = cursor.getString(cursor.getColumnIndex(COLUMN_DETAIL));

            AnimalsList animalList = new AnimalsList(id, name, category, detail);
            myList.add(animalList);
        }

        return myList;
    }


    public AppDataBase open() throws android.database.SQLException {
        HashiDataBase = new HshiDataBase(context);
        sqLiteDatabase = HashiDataBase.getWritableDatabase();
        return AppDataBase.this;
    }

    public void close() {
        HashiDataBase.close();
    }


    private class HshiDataBase extends SQLiteOpenHelper {

        public HshiDataBase(Context context) {
            super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE_REGISTER);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(DROP_TABLE_REGISTER);
        }
    }

}
