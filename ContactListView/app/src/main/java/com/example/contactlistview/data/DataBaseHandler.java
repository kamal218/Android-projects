package com.example.contactlistview.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.contactlistview.contract.Contract;
import com.example.contactlistview.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {

    public DataBaseHandler( Context context) {
        super(context, "db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + Contract.TABLE_NAME + "("
                + Contract.KEY_ID + " INTEGER PRIMARY KEY," + Contract.KEY_NAME + " TEXT,"
                + Contract.KEY_NUMBER + " TEXT" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP = "DROP TABLE IF EXISTS ";
        db.execSQL(DROP+Contract.TABLE_NAME);
        onCreate(db);
    }

    //    CRUD
    public void addContact(Contact contact){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Contract.KEY_NAME,contact.getName());
        cv.put(Contract.KEY_NUMBER,contact.getNumer());
        db.insert(Contract.TABLE_NAME,null,cv);
        db.close();
    }
    public Contact getContact(int ID){
        Contact contact=new Contact();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.query(Contract.TABLE_NAME,new String[]{Contract.KEY_ID,Contract.KEY_NAME,Contract.KEY_NUMBER},Contract.KEY_ID+"=?",new String[]{String.valueOf(ID)},null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();
        contact.setID(Integer.parseInt(cursor.getString(0)));
        contact.setName(cursor.getString(1));
        contact.setNumer(cursor.getString(2));
        return contact;
    }
    public void deleteContact(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(Contract.TABLE_NAME,Contract.KEY_ID+"=?",new String[]{String.valueOf(contact.getID())});
        db.close();
    }

    public void updateContact(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Contract.KEY_NAME,contact.getName());
        cv.put(Contract.KEY_NUMBER,contact.getNumer());
        db.update(Contract.TABLE_NAME,cv,Contract.KEY_ID+"=?",new String[]{String.valueOf(contact.getID())});
    }
    public int getNumberOfContacts(){
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+Contract.TABLE_NAME,null);
        return cursor.getCount();
    }
    public List<Contact> getAllContacts(){
        SQLiteDatabase db=getReadableDatabase();
        List<Contact> ans=new ArrayList();
        Cursor cursor=db.rawQuery("SELECT * FROM "+Contract.TABLE_NAME,null);
        if(cursor.moveToFirst()){
            do{
                Contact contact=new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setNumer(cursor.getString(2));
                ans.add(contact);
            }while(cursor.moveToNext());
        }
        return ans;
    }
    public void deleteTable(){
        SQLiteDatabase db=getWritableDatabase();
        String DROP = "DROP TABLE IF EXISTS ";
        db.execSQL(DROP+Contract.TABLE_NAME);
        onCreate(db);
    }
}

