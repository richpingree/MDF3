package com.richardpingree.widget;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Richard Pingree MDF3 1503 Week 3 on 3/19/15.
 */
public class PersonUtility{

    public static void saveFile(Context context, Person person){

        ArrayList<Person> people = loadFile(context);

        people.add(person);

        try{
            FileOutputStream fos = context.openFileOutput("people", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(people);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Person> loadFile(Context context)  {
        ArrayList<Person> people;

        try{

            FileInputStream fis = new FileInputStream("people");
            ObjectInputStream ois = new ObjectInputStream(fis);
            people = (ArrayList<Person>) ois.readObject();
            ois.close();

            return people;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
