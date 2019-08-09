package com.mohil.attendomate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AttendanceViewAdapter extends RecyclerView.Adapter {

    private ArrayList<Student> students;
    int pos;
    public AttendanceViewAdapter(ArrayList<Student> students, int pos) {
        this.students = students;
        this.pos = pos;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View view=layoutInflater.inflate(R.layout.list_item_view,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView rno;
        ImageView imageView;
        Button absent;
        Button present;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.tvName);
            rno=itemView.findViewById(R.id.tvRno);
            imageView=itemView.findViewById(R.id.imgPic);
            absent=itemView.findViewById(R.id.btAbsent);
            present=itemView.findViewById(R.id.btPresent);
        }
    }
}
