package com.mohil.attendomate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Calender extends AppCompatActivity {

    Button next;
    Calender calender;
    Intent intent;
    Intent intent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        initView();
        initData();
        initListeners();
    }

    private void initListeners() {
       next.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(intent);
           }
       });
    }

    private void initData() {
     intent=new Intent(Calender.this,MarkAttendance.class);
    }
    private void initView() {

        next=findViewById(R.id.btNext);
    }
}