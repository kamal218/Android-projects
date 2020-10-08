package com.example.contactlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contactlistview.contract.Contract;
import com.example.contactlistview.data.DataBaseHandler;
import com.example.contactlistview.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ListView listView;
    private ArrayAdapter<String> Listadapter;
    ArrayList<String> contactString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBaseHandler db=new DataBaseHandler(this);
//        db.addContact(new Contact("kamal","8759"));
//        db.addContact(new Contact("pinku","5487"));
//        db.addContact(new Contact("gaurav","99456"));
//        db.addContact(new Contact("naman","65478"));
//        db.addContact(new Contact("priya","2145"));
//        db.addContact(new Contact("rahul","12478"));
//        db.addContact(new Contact("ashish","9876"));
//        db.addContact(new Contact("ravi","5973"));
//        Log.d("number", "onCreate: "+db.getNumberOfContacts());
        method1();// using ArrayAdapter
//        method2();// using simpleCursorAdapter
    }
    public void method1 (){
        DataBaseHandler db=new DataBaseHandler(this);
        List<Contact> list=db.getAllContacts();
        contactString=new ArrayList<>();
        for(Contact contact:list){
            contactString.add(contact.getName());
        }
        Log.d("list", "onCreate: "+contactString);
        Listadapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,contactString);
        listView=findViewById(R.id.List_View);
        listView.setAdapter(Listadapter);
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"hello",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "name : "+contactString.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void method2(){
        DataBaseHandler db=new DataBaseHandler(this);
        SQLiteDatabase rdb=db.getReadableDatabase();
        Cursor cursor=rdb.rawQuery("SELECT *  FROM "+ Contract.TABLE_NAME,null);
        String[] columns={Contract.KEY_NAME,Contract.KEY_NUMBER};
        int[] toViews={R.id.name,R.id.number};
        SimpleCursorAdapter ListAdapter=new SimpleCursorAdapter(this,R.layout.onecontact,cursor,columns,toViews,0);
        listView=findViewById(R.id.List_View);
        listView.setAdapter(ListAdapter);
    }
}