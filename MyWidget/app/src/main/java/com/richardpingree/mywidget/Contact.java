package com.richardpingree.mywidget;

import java.io.Serializable;

/**
 * Created by Richard Pingree MDF3 1504 Week 3 on 4/20/15.
 */
public class Contact implements Serializable {

    private static final long serialVersionID = 1234567890987654321L;

    public String mFirst;
    public String mLast;
    public String mEmail;

    public Contact (String first, String last, String email){
        mFirst = first;
        mLast = last;
        mEmail = email;
    }

    public Contact(){

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
