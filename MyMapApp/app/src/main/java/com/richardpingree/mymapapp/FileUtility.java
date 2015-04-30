package com.richardpingree.mymapapp;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Richard Pingree MDF3 1504 Week 4 on 4/30/15.
 */
public class FileUtility {

    public static void saveFile(Context context, CustomObject object){

        ArrayList<CustomObject> objectArrayList = loadFile(context);
        if(objectArrayList == null){
            objectArrayList = new ArrayList<CustomObject>();
        }

        objectArrayList.add(object);

        try{
            FileOutputStream fos = context.openFileOutput("objects", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(objectArrayList);
            oos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<CustomObject> loadFile(Context context) {
        ArrayList<CustomObject> objectArrayList = null;
        try{
            FileInputStream fis = context.openFileInput("objects");
            ObjectInputStream ois = new ObjectInputStream(fis);
            objectArrayList = (ArrayList<CustomObject>)ois.readObject();
            ois.close();
            if(objectArrayList == null){
                objectArrayList = new ArrayList<CustomObject>();
                return objectArrayList;
            }else{
                return objectArrayList;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
