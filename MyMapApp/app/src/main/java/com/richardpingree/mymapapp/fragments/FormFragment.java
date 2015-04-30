package com.richardpingree.mymapapp.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.richardpingree.mymapapp.CustomObject;
import com.richardpingree.mymapapp.FileUtility;
import com.richardpingree.mymapapp.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Richard Pingree MDF3 1504 Week 4 on 4/29/15.
 */
public class FormFragment extends Fragment {

    private static final int REQUEST_TAKE_PICTURE = 0;
    public EditText title, notes;
    public ImageView imageView;
    public TextView latitude, longitude;
    public Button save, camera;
    public Uri imageUri;
    public CustomObject newObject;


    private FormListener mListener;

    public interface FormListener{
        public Double getLat();
        public Double getLong();
        public void addObject(CustomObject newObject);

    }

    public FormFragment(){

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof FormListener){
            mListener = (FormListener) activity;
        }else{
            throw new IllegalArgumentException("Containing activity must implement FormListener interface");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageUri = getOutputUri();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_form, container, false);
        title = (EditText)rootView.findViewById(R.id.title);
        notes = (EditText)rootView.findViewById(R.id.notes);
        imageView = (ImageView)rootView.findViewById(R.id.image);
        latitude = (TextView)rootView.findViewById(R.id.latitude);
        longitude = (TextView)rootView.findViewById(R.id.longitude);
        save = (Button)rootView.findViewById(R.id.saveButton);
        camera = (Button)rootView.findViewById(R.id.cameraButton);

        latitude.setText(String.valueOf(mListener.getLat()));
        longitude.setText(String.valueOf(mListener.getLong()));


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Form", "Save Button Clicked");
                newObject = new CustomObject();

                newObject.mTitle = title.getText().toString();
                newObject.mNote = notes.getText().toString();
                newObject.mLatitude = mListener.getLat();
                newObject.mLongitude = mListener.getLong();

                mListener.addObject(newObject);

                FileUtility.saveFile(getActivity(), newObject);

            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Form", "Camera Button Clicked");
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, getOutputUri());
                startActivityForResult(cameraIntent, REQUEST_TAKE_PICTURE);

            }
        });



        return rootView;
    }


//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        title = (EditText)getView().findViewById(R.id.title);
//        notes = (EditText)getView().findViewById(R.id.notes);
//        imageView = (ImageView)getView().findViewById(R.id.image);
//        latitude = (TextView)getView().findViewById(R.id.latitude);
//        longitude = (TextView)getView().findViewById(R.id.longitude);
//        save = (Button)getView().findViewById(R.id.saveButton);
//        camera = (Button)getView().findViewById(R.id.cameraButton);
//
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("Form", "Save Button Clicked");
//            }
//        });
//
//        camera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("Form", "Camera Button Clicked");
//                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mListener.getOutputUri());
//                startActivityForResult(cameraIntent, REQUEST_TAKE_PICTURE);
//
//            }
//        });
//
//
//        latitude.setText("Latitude: " + String.valueOf(mListener.getLat()));
//        longitude.setText("Longitude: " + String.valueOf(mListener.getLong()));
//
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_TAKE_PICTURE && requestCode != Activity.RESULT_CANCELED){
            if (data != null){
                imageView.setImageBitmap(BitmapFactory.decodeFile(imageUri.getPath()));

                addToGallary(imageUri);
            }else{
                imageView.setImageBitmap((Bitmap)data.getParcelableExtra("data"));
            }
        }
    }

    private Uri getOutputUri(){
        String imageName = new SimpleDateFormat("MMddyyyy_HHmmss").format(new Date(System.currentTimeMillis()));

        File imagedir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        //creates folder for images in default directory
        File appDir = new File(imagedir, "MyMapApp");
        appDir.mkdirs();
        File image = new File(appDir, imageName + ".jpg");

        try{
            image.createNewFile();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return Uri.fromFile(image);
    }

    private void addToGallary(Uri imageUri){
        Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        scanIntent.setData(imageUri);
        getActivity().sendBroadcast(scanIntent);

    }

}
