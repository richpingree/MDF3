package com.fullsail.android.collectionwidgetdemo;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Richard Pingree MDF3 1503 Week 3 on 3/21/15.
 */
public class PersonUtility {

    public static void saveFile(Context context, Person person){

        ArrayList<Person> personArrayList = loadFile(context);

        personArrayList.add(person);

        try{
            FileOutputStream fos = context.openFileOutput("people", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(personArrayList);
            oos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static ArrayList<Person> loadFile(Context context) {
        ArrayList<Person> personArrayList = null;

        try{

            FileInputStream fis = new FileInputStream("people");
            ObjectInputStream ois = new ObjectInputStream(fis);
            personArrayList = (ArrayList<Person>) ois.readObject();
            ois.close();

            return personArrayList;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
