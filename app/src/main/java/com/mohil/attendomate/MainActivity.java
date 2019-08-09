package com.mohil.attendomate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class MainActivity extends AppCompatActivity {
    Button mark;
    Button calc;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        initdata();
        initListeners();
    }

    private void initListeners() {
        mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

    private void initdata() {
        intent=new Intent(MainActivity.this, Calender.class);

    }

    private void initview() {
     mark=findViewById(R.id.btMark);
     calc=findViewById(R.id.btCalc);
    }
}
