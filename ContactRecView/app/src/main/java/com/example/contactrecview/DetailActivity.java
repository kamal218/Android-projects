package com.example.contactrecview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView  name_;
    TextView number_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        name_=findViewById(R.id.name_iid_1);
        number_=findViewById(R.id.number_id_1);
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle!=null){
            name_.setText(bundle.getString("name"));
            number_.setText(bundle.getString("number"));
        }
    }
}