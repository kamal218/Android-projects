package com.example.babyneed;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.babyneed.data.DataBaseHandler;
import com.example.babyneed.model.Model;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
        Button addButton;
        AlertDialog.Builder builder;
        AlertDialog alertDialog;
        EditText pName;
        EditText pColor;
        EditText pSize;
        EditText pQuantity;
        Button saveButton;
        DataBaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DataBaseHandler(this);
        addButton=findViewById(R.id.add_button_main);
        addButton.setOnClickListener(this);
//        db.deleteTable();
//        Log.d("data", "onCreate: "+db.getAllItems());
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==addButton.getId()){
            createAlertDialog();
            alertDialog.show();
        }else if(v.getId()==saveButton.getId()){
            if(!pName.getText().toString().isEmpty() && !pColor.getText().toString().isEmpty()
                    && !pSize.getText().toString().isEmpty() && !pQuantity.getText().toString().isEmpty() ){
                Model Item = new Model();
                Item.setP_name(pName.getText().toString());
                Item.setColor(pColor.getText().toString());
                Item.setSize(Integer.parseInt(pSize.getText().toString()));
                Item.setQuantity(Integer.parseInt(pQuantity.getText().toString()));
//                if(!db.checkIfPresent(Item)) {
//                    db.addItem(Item);
//                    Toast.makeText(this, "wohoo! Your Item Has Been Saved", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Toast.makeText(this,"Sorry You have already added this item in the list",Toast.LENGTH_SHORT).show();
//                }
                db.addItem(Item);
                Toast.makeText(this, "wohoo! Your Item Has Been Saved", Toast.LENGTH_SHORT).show();
                goToNextActivity();
            }else{
                Toast.makeText(this,"PLEASE FILL ALL THE CHOICES",Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void createAlertDialog(){
        builder=new AlertDialog.Builder(this);
        View dialog=getLayoutInflater().inflate(R.layout.additionxml,null);
        pName=dialog.findViewById(R.id.name_id);
        pColor=dialog.findViewById(R.id.color_id);
        pSize=dialog.findViewById(R.id.size_id);
        pQuantity=dialog.findViewById(R.id.quantity_id);
        saveButton=dialog.findViewById(R.id.save_button);
        saveButton.setOnClickListener(this);
        builder.setView(dialog);
        alertDialog=builder.create();
    }
    public void goToNextActivity(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                alertDialog.dismiss();
                startActivity(new Intent(MainActivity.this,ListActivity.class));
            }
        },3000);
//        startActivity(new Intent(MainActivity.this,ListActivity.class));
    }
}