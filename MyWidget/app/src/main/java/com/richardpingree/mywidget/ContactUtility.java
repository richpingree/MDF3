package com.richardpingree.mywidget;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Richard Pingree MDF3 1504 Week 3 on 4/20/15.
 */
public class ContactUtility {

    public static void saveFile(Context context, Contact contact){

        ArrayList<Contact> contactArrayList = loadFile(context);

        contactArrayList.add(contact);

        try{
            FileOutputStream fos = context.openFileOutput("contacts", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(contactArrayList);
            oos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Contact> loadFile(Context context){
        ArrayList<Contact> contactArrayList = null;
        try{
            FileInputStream fis = context.openFileInput("contacts");
            ObjectInputStream ois = new ObjectInputStream(fis);
            contactArrayList = (ArrayList<Contact>) ois.readObject();
            ois.close();
            if(contactArrayList == null){
                contactArrayList = new ArrayList<Contact>();
            }

            return contactArrayList;
        }catch (Exception e){
            e.printStackTrace();
            Log.i("ContactUtility", "error with loading");
            return null;
        }
    }
}
