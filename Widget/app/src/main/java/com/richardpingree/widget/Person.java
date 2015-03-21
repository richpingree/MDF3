package com.richardpingree.widget;

import java.io.Serializable;

/**
 * Created by Richard Pingree MDF3 1503 Week 3 on 3/14/15.
 */
public class Person implements Serializable{

    private static final long serialVersionUID = 1234567890123456L;

    public String mFirst;
    public String mLast;
    public String mEmail;

    public Person(){
        mFirst = "";
        mLast = "";
        mEmail = "";
    }

    public Person(String first, String last, String email){
        mFirst = first;
        mLast = last;
        mEmail = email;
    }

    public String getFirst(){
        return mFirst;
    }

    public void setFirst(){
        this.mFirst = mFirst;
    }

    public String getLast(){
        return mLast;
    }

    public void setLast(){
        this.mLast = mLast;
    }

    public String getEmail(){
        return mEmail;
    }

    public void setEmail(){
        this.mEmail = mEmail;
    }

    @Override
    public String toString() {
        return mFirst + " " + mLast;
    }
}
