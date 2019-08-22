package com.mohil.attendomate;

import android.widget.ImageView;

public class Student {
    String rNo;
    String name;
    String imageUrl;
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }




    public Long getAttCount() {
        return attCount;
    }

    public void setAttCount(Long attCount) {
        this.attCount = attCount;
    }

    Long attCount;

    public Student(String rNo, String name, Long attCount,String imageUrl) {
        this.rNo = rNo;
        this.name = name;
        this.imageUrl = imageUrl;
        this.attCount= attCount;
    }

    public String getrNo() {
        return rNo;
    }

    public void setrNo(String rNo) {
        this.rNo = rNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
