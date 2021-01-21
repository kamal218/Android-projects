package com.example.babyneed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.babyneed.adapter.RecyclerViewAdapter;
import com.example.babyneed.data.DataBaseHandler;
import com.example.babyneed.model.Model;

import java.util.List;

public class ListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    List<Model> Items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        DataBaseHandler db=new DataBaseHandler(this);
        Items=db.getAllItems();
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter=new RecyclerViewAdapter(this,Items);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}