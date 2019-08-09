package com.mohil.attendomate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class MarkAttendance extends AppCompatActivity {
    RecyclerView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
      initView();
      initData();
      initListeners();
    }

    private void initView() {
        adapter=findViewById(R.id.rvAdapter);

    }

    private void initData() {

    }

    private void initListeners() {

    }


}
