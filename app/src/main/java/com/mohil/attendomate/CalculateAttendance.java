package com.mohil.attendomate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class CalculateAttendance extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Student> students=new ArrayList<>();
    StorageReference storageReference;
    FirebaseStorage firebaseStorage;
    CalculateAttendanceAdapter calculateAttendanceAdapter;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_attendance);
        initView();
        initData();
        initListeners();
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(calculateAttendanceAdapter);
    }

    private void initListeners() {
    }

    private void initData() {
        calculateAttendanceAdapter =new CalculateAttendanceAdapter(students,CalculateAttendance.this);
        db=FirebaseFirestore.getInstance();
          firebaseStorage=FirebaseStorage.getInstance();
          storageReference=firebaseStorage.getReference();
            db.collection("Student").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                if(!task.getResult().isEmpty())
                {
                     for(final QueryDocumentSnapshot queryDocumentSnapshot:task.getResult())
                     {
                         storageReference.child("images/"+queryDocumentSnapshot.getData().get("RollNo").toString()+".jpg").getDownloadUrl()
                                 .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                     @Override
                                     public void onSuccess(Uri uri) {
                                         students.add(new Student(queryDocumentSnapshot.getData().get("RollNo").toString(),queryDocumentSnapshot.getData().get("Name").toString()
                                                 ,queryDocumentSnapshot.getLong("Attendance"),uri.toString()));
                                         calculateAttendanceAdapter.notifyDataSetChanged();
                                     }
                                 }).addOnFailureListener(new OnFailureListener() {
                             @Override
                             public void onFailure(@NonNull Exception e) {
                                 Log.i("Test :","Image Load Failed");
                             }
                         });

                     }

                }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.i("Test","Failed");
                }
            });
    }

    private void initView() {
    recyclerView=findViewById(R.id.rvAdapter2);
    }
}
