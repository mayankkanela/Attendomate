package com.mohil.attendomate;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class MarkAttendance extends AppCompatActivity {
    RecyclerView adapter;
    FirebaseFirestore db;
    FirebaseStorage storage;
    StorageReference storageReference;
    CollectionReference collectionReference;
   ArrayList<Student> students=new ArrayList<>();
     AttendanceViewAdapter attendanceViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
      initView();
      initData();
      initListeners();
        attendanceViewAdapter= new AttendanceViewAdapter(MarkAttendance.this,students);
        adapter.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        adapter.setAdapter(attendanceViewAdapter);

    }

    private void initView() {
        adapter=findViewById(R.id.rvAdapter);

    }

    private void initData() {

          FirebaseApp.initializeApp(this);
         db=FirebaseFirestore.getInstance();
         storage=FirebaseStorage.getInstance();
         storageReference=storage.getReference();

         db.collection("Student").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
             @Override
             public void onComplete(@NonNull Task<QuerySnapshot> task) {
                 if (task.isSuccessful())
                     if(!task.getResult().isEmpty())
                     for (final QueryDocumentSnapshot document:task.getResult())
                     {
                        storageReference.child("images/"+document.getData().get("RollNo").toString()+".jpg").getDownloadUrl()
                                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                       students.add(new Student(document.getData().get("RollNo").toString(),document.getData().get("Name").toString()
                                       ,document.getLong("Attendance"),uri.toString()));
                                            attendanceViewAdapter.notifyDataSetChanged();
                                     }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });


                     }
             }
         }).addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {
                 Log.i("Attendomate","Retrieval failed");
             }
         });
    }

    private void initListeners() {

    }


}
