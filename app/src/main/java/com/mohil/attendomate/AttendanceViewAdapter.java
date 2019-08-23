package com.mohil.attendomate;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AttendanceViewAdapter extends RecyclerView.Adapter<AttendanceViewAdapter.ViewHolder> {
  Context context;


  CollectionReference collectionReference;
  FirebaseFirestore db;
    private ArrayList<Student> students;
    public AttendanceViewAdapter(Context context,ArrayList<Student> students) {
        this.students = students;
        this.context=context;


    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View view=layoutInflater.inflate(R.layout.list_item_view,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Student student=students.get(i);
        if(student!=null)
        {
            viewHolder.name.setText(student.getName());
            viewHolder.rno.setText(student.getrNo());
            viewHolder.attCount.setText(String.format("%d",student.getAttCount()));
            if(student.getImageUrl()!=null)
            Glide.with(context).load(student.getImageUrl()).into(viewHolder.imageView);

        }

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView rno;
        TextView attCount;
        ImageView imageView;
        Button absent;
        Button present;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.tvName);
            rno=itemView.findViewById(R.id.tvRno);
            attCount=itemView.findViewById(R.id.tvAtt);
            imageView=itemView.findViewById(R.id.imgPic);
            absent=itemView.findViewById(R.id.btAbsent);
            FirebaseApp.initializeApp(context);
            db=FirebaseFirestore.getInstance();
            collectionReference=db.collection("Student");
            present=itemView.findViewById(R.id.btPresent);
            final int[] flag = {0};
            final int[] firstclicked = {0};
            absent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.i("Attendomate","Absent");

                    if(flag[0] ==1|| firstclicked[0] ==0)
                    {
                        absent.setBackgroundColor(Color.RED);
                        present.setBackgroundColor(0xFFC7C8C8);
                        students.get(getAdapterPosition()).setAttCount(students.get(getAdapterPosition()).getAttCount()-1);
                        attCount.setText(students.get(getAdapterPosition()).getAttCount().toString());
                        flag[0] =0;
                        firstclicked[0] =1;
                        collectionReference.whereEqualTo("RollNo",students.get(getAdapterPosition()).getrNo()).get()
                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                    @Override
                                    public void onSuccess(QuerySnapshot querySnapshot) {
                                        DocumentReference documentReference=querySnapshot.getDocuments().get(0).getReference();
                                        documentReference.update("Attendance",students.get(getAdapterPosition()).getAttCount()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.i("Attendomate","Success");

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.i("Attendomate","Failed");
                                            }
                                        });

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i("Attendomate","Failed");
                            }
                        });


                    }

                }
            });
            present.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   if(flag[0] ==0|| firstclicked[0] ==0){
                       Log.i("Attendomate","Present");
                    students.get(getAdapterPosition()).setAttCount(students.get(getAdapterPosition()).getAttCount()+1);
                    present.setBackgroundColor(Color.GREEN);
                    absent.setBackgroundColor(0xFFC7C8C8);
                       attCount.setText(students.get(getAdapterPosition()).getAttCount().toString());
                       firstclicked[0] =1;
                    flag[0] =1;
                   collectionReference.whereEqualTo("RollNo",students.get(getAdapterPosition()).getrNo()).get()
                   .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                       @Override
                       public void onSuccess(QuerySnapshot querySnapshot) {
                           DocumentReference documentReference=querySnapshot.getDocuments().get(0).getReference();
                                    documentReference.update("Attendance",students.get(getAdapterPosition()).getAttCount()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.i("Attendomate","Success");

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.i("Attendomate","Failed");
                                        }
                                    });

                       }
                   }).addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {
                      Log.i("Attendomate","Failed");
                       }
                   });}
                }
            });
        }
    }
}
