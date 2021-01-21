package com.example.babyneed.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.babyneed.contract.Contract;
import com.example.babyneed.model.Model;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {
    public DataBaseHandler( Context context) {
        super(context, Contract.DB_NAME, null, Contract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE "+Contract.TABLE_NAME + "(" + Contract.KEY_ID + "INTEGER PRIMARY KEY,"
                 + Contract.KEY_NAME + " TEXT," + Contract.KEY_COLOR + " TEXT," + Contract.KEY_QUANTITY + " TEXT,"
                 + Contract.KEY_SIZE + " TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Contract.TABLE_NAME);
        onCreate(db);
    }
    public void deleteTable(){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+Contract.TABLE_NAME);
        onCreate(db);
    }
    //    CRUD
    public void addItem(Model Item){
        ContentValues cv=new ContentValues();
        cv.put(Contract.KEY_NAME,Item.getP_name());
        cv.put(Contract.KEY_COLOR,Item.getColor());
        cv.put(Contract.KEY_QUANTITY,Item.getQuantity());
        cv.put(Contract.KEY_SIZE,Item.getSize());

        SQLiteDatabase db=getWritableDatabase();
        db.insert(Contract.TABLE_NAME,null,cv);
        db.close();
    }

    public Model getItem(int ID){
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.query(Contract.TABLE_NAME,new String[]{Contract.KEY_ID,Contract.KEY_NAME,Contract.KEY_COLOR,Contract.KEY_SIZE,Contract.KEY_QUANTITY},
                Contract.KEY_ID+"=?",new String[]{String.valueOf(ID)},null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();
        Model Item = new Model();
        Item.setID(cursor.getInt(0));
        Item.setP_name(cursor.getString(1));
        Item.setColor(cursor.getString(2));
        Item.setSize(cursor.getInt(3));
        Item.setQuantity(cursor.getInt(4));
        db.close();
        return Item;
    }
    public List<Model> getAllItems(){
        SQLiteDatabase db=getReadableDatabase();
        List<Model> items=new ArrayList();
        Cursor cursor=db.rawQuery("SELECT * FROM "+Contract.TABLE_NAME,null);
        if(cursor.moveToFirst()){
            do{
                Model Item = new Model();
                Item.setID(cursor.getInt(0));
                Item.setP_name(cursor.getString(1));
                Item.setColor(cursor.getString(2));
                Item.setSize(cursor.getInt(3));
                Item.setQuantity(cursor.getInt(4));
                items.add(Item);
            }while(cursor.moveToNext());
        }
        db.close();
        return items;
    }

    public void updateItem(Model Item){
        ContentValues cv=new ContentValues();
        cv.put(Contract.KEY_NAME,Item.getP_name());
        cv.put(Contract.KEY_COLOR,Item.getColor());
        cv.put(Contract.KEY_QUANTITY,Item.getQuantity());
        cv.put(Contract.KEY_SIZE,Item.getSize());
        SQLiteDatabase db=getWritableDatabase();
        db.update(Contract.TABLE_NAME,cv,Contract.KEY_ID+"=?",new String[]{String.valueOf(Item.getID())});
        db.close();
    }

    public void deleteItem(Model Item){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(Contract.TABLE_NAME,Contract.KEY_ID+"=?",new String[]{String.valueOf(Item.getID())});
        db.close();
    }
    public boolean checkIfPresent(Model model){
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+Contract.TABLE_NAME + " WHERE "+ Contract.KEY_NAME + " = " + model.getP_name()+ " AND "+
                Contract.KEY_COLOR +" = "+ model.getColor() + " AND "+Contract.KEY_SIZE + " = " + model.getSize() + " AND "+
                Contract.KEY_QUANTITY+ " = "+ model.getSize(),null);
        if(cursor!=null)
            return true;
        return false;
    }
}
