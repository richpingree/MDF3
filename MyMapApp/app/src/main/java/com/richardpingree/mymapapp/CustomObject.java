package com.richardpingree.mymapapp;

import java.io.Serializable;

/**
 * Created by Richard Pingree MDF3 1504 Week 4 on 4/28/15.
 */
public class CustomObject implements Serializable{

    private static final long serialVersionID = 1234567890987654321L;

    public String mTitle;
    public String mNote;
    public String mImageName;
    public Double mLatitude;
    public Double mLongitude;

    public CustomObject(){

    }

    public CustomObject(String title, String note, String imageName, Double latitude, Double longitude){
        mTitle = title;
        mNote = note;
        mImageName = imageName;
        mLatitude = latitude;
        mLongitude = longitude;
    }

    public String getmTitle(){
        return mTitle;
    }

    public void setmTitle(){
        this.mTitle = mTitle;
    }

    public String getNote(){
        return mNote;
    }

    public void setNote(){
        this.mNote = mNote;
    }

    public String getImageName(){
        return mImageName;
    }

    public void setmImageName(){
        this.mImageName = mImageName;
    }

    public Double getmLatitude(){
        return mLatitude;
    }

    public void setmLatitude(){
        this.mLatitude = mLatitude;
    }

    public Double getmLongitude(){
        return mLongitude;
    }

    public void setmLongitude(){
        this.mLongitude = mLongitude;
    }
}
