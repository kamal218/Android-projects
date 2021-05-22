package com.example.userpassgenerator;

import androidx.appcompat.app.AppCompatActivity;
import java.util.*;
import android.content.ContentProviderClient;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    private TextView reference;
    private TextView userName;
    private TextView answer;
    private Button button;
    private RadioButton userPressed;
    private RadioButton passPressed;
    private String ref;
    private String user;
    private String ans="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reference=findViewById(R.id.reference_string);
        userName=findViewById(R.id.username_string);
        answer=findViewById(R.id.answer_string);
        button=findViewById(R.id.button);
        userPressed=findViewById(R.id.username);
        passPressed=findViewById(R.id.password);
        ref=String.valueOf(reference.getText());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                huffman control=new huffman(ref);
                user=String.valueOf(userName.getText());
               if(userPressed.isChecked()){
                   ans=control.generatePasswordFromUsername(user,4,true,true,true);
               }else{
                    ans=control.generateUsernameFromPassword(user);
               }
                Log.d("answer", "onClick: "+ans);
                answer.setText(String.valueOf(ans));
//                Log.d("ref", "onClick: "+reference.getText()+"kamal");
//                Log.d("user", "onClick: "+userName.getText()+"kamal");
            }
        });
        Log.d("reference", "onCreate: "+reference.getText());
        Log.d("username", "onCreate: "+userName.getText());
    }
}