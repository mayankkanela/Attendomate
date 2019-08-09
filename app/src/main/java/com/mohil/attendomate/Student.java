package com.mohil.attendomate;

import android.widget.ImageView;

public class Student {
    int rNo;
    String name;
    ImageView imageView;

    public Student(int rNo, String name, ImageView imageView) {
        this.rNo = rNo;
        this.name = name;
        this.imageView = imageView;
    }

    public int getrNo() {
        return rNo;
    }

    public void setrNo(int rNo) {
        this.rNo = rNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
