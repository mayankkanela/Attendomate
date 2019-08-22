package com.mohil.attendomate;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CalculateAttendanceAdapter extends RecyclerView.Adapter <CalculateAttendanceAdapter.RecyclerViewHolder>{
   ArrayList<Student> students;
   Context context;
   public  CalculateAttendanceAdapter(ArrayList<Student> students , Context context)
   {
       this.students=students;
       this.context=context;

   }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.list_item_view_mark,parent,false);
            return new RecyclerViewHolder(view);
   }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Student student=students.get(position);
        if(student!=null)
        {
            holder.name.setText("Name  :"+student.getName());
            holder.sNo.setText("Roll No :"+student.getrNo());
            Long temp =student.getAttCount();
            temp=(temp/30)*100;
            holder.percentage.setText(temp.toString()+"%");
            if(student.getImageUrl()!=null)
                Glide.with(context).load(student.getImageUrl()).into(holder.pic);


        }
    }


    @Override
    public int getItemCount() {
       return students.size();
    }
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView sNo;
        TextView percentage;
        ImageView pic;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.tvName);
            sNo=itemView.findViewById(R.id.tvSno);
            percentage=itemView.findViewById(R.id.tvPercentage);
            pic=itemView.findViewById(R.id.imgPicture);
        }
    }
}
