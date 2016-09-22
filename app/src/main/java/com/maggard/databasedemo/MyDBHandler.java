package com.maggard.databasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 660252397 on 9/21/2016.
 */
public class MyDBHandler extends SQLiteOpenHelper{
    //define variables for the database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "products.db";  //makes sure your name uses a db extension
    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "product name";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, DATABASE_NAME, factory, DATABASE_VERSION); //replace name and version with variable from above
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_PRODUCTS + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT,"+ COLUMN_NAME + " TEXT" + ");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(sqLiteDatabase);
    }

    //we need to add three methods that will handle interacting with the database
    //add a new row to the databases
    public void addProduct(Products product){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, product.getProductName());
        //get reference to the database define your database
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    public void deleteProduct(String product){
        SQLiteDatabase db = getWritableDatabase();
        //call SQL to delete
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS +
                " WHERE" + COLUMN_NAME + "=\"" + product + "\";"); //may be an issue
    }

    //print out database as a string
    public List<Products> databaseToString(){
        List<Products> products = new ArrayList<Products>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";

        //cursor point to a location in your results
        Cursor c = db.rawQuery(query,null);
        //move to the first row in your result
        c.moveToFirst();

        //make sure you have something to go through
        while(!c.isAfterLast()){
            Products product = cursorToProduct(c);
            products.add(product);
            c.moveToNext();
        }
        //then close out your database and return the information
        db.close();
        return products;
    }

    private Products cursorToProduct(Cursor c) {
        Products product = new Products();
        product.set_id(c.getInt(0));
        product.setProductName(c.getString(1));
        return product;
    }
}
