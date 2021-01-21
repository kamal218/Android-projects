package com.example.contactrecview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.contactrecview.Adapter.RecyclerViewAdapter;
import com.example.contactrecview.data.DataBaseHandler;
import com.example.contactrecview.model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter recycler_view_adapter;
    List<Contact> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBaseHandler db=new DataBaseHandler(this);
//        db.addContact(new Contact("kamal","93578"));
//        db.addContact(new Contact("ram","5478"));
//        db.addContact(new Contact("naman","9658"));
//        db.addContact(new Contact("rahul","2547"));
//        db.addContact(new Contact("deep","8745"));
//        db.addContact(new Contact("gaurav","9658"));
//        db.addContact(new Contact("harsh","93578"));
//        db.addContact(new Contact("daksh","2547"));
//        db.addContact(new Contact("ankit","8745"));
//        db.addContact(new Contact("sumit","93578"));
//        db.addContact(new Contact("amit","9857"));
//        db.addContact(new Contact("parag","6871"));
//        db.addContact(new Contact("badal","5971"));
//        db.addContact(new Contact("babu","2987"));
//        db.addContact(new Contact("kamal","5792"));
//        db.addContact(new Contact("pinku","5364"));
//        db.addContact(new Contact("piku","4931"));
//        db.addContact(new Contact("raju","7948"));
        Log.d("number", "onCreate: "+db.getNumberOfContacts());
        list=db.getAllContacts();
        recyclerView=findViewById(R.id.recyclerview_id);
        recycler_view_adapter=new RecyclerViewAdapter(this,list);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(recycler_view_adapter);
    }
}