package com.richardpingree.mymapapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.richardpingree.mymapapp.fragments.FormFragment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Richard Pingree MDF3 1504 Week 4 on 4/29/15.
 */
public class FormActivity extends Activity implements FormFragment.FormListener{

    public static final String GETLAT = "latitude";
    public static final String GETLONG = "longitude";
    private static final int REQUEST_TAKE_PICTURE = 0;
    public EditText titleInput, notesInput;
    public ImageView imageView;
    public TextView latitude, longitude;
    public Button save, camera;
    public Uri imageUri;
    public CustomObject newObject;

    Double mLat, mLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

       // getFragmentManager().beginTransaction().replace(R.id.container, new FormFragment()).commit();
        titleInput = (EditText)findViewById(R.id.titleText);
        notesInput = (EditText)findViewById(R.id.notesText);
        imageView = (ImageView)findViewById(R.id.image);
        latitude = (TextView)findViewById(R.id.latitude);
        longitude = (TextView)findViewById(R.id.longitude);
        save = (Button)findViewById(R.id.saveButton);
        camera = (Button)findViewById(R.id.cameraButton);


        Intent locIntent = getIntent();
        if(locIntent != null){
            mLat = locIntent.getDoubleExtra(GETLAT, 0.00);
            mLong = locIntent.getDoubleExtra(GETLONG, 0.00);
            Log.i("FormActivity", String.valueOf(mLat) + String.valueOf(mLong));
        }

        latitude.setText("Latitude: " + String.valueOf(mLat));
        longitude.setText("Longitude: " + String.valueOf(mLong));

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Form", "Save Button Clicked");
                newObject = new CustomObject();

                newObject.mTitle = titleInput.getText().toString();
                newObject.mNote = notesInput.getText().toString();
                newObject.mLatitude = mLat;
                newObject.mLongitude = mLong;

                addObject(newObject);

                FileUtility.saveFile(getApplicationContext(), newObject);

            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Form", "Camera Button Clicked");
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                imageUri = getOutputUri();
                if(imageUri != null) {
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                }
                startActivityForResult(cameraIntent, REQUEST_TAKE_PICTURE);

            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_TAKE_PICTURE && requestCode != RESULT_CANCELED){
            if (imageUri != null){
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
        sendBroadcast(scanIntent);

    }


    @Override
    public Double getLat() {
        //mLat = getIntent().getExtras().getString("latitude");
        return mLat;
    }

    @Override
    public Double getLong() {
        //mLong = getIntent().getStringExtra("longitude");
        return mLong;

    }

    @Override
    public void addObject(CustomObject newObject) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(MainActivity.ADD_OBJECT_EXTRA_TITLE, newObject.mTitle);
        returnIntent.putExtra(MainActivity.ADD_OBJECT_EXTRA_NOTE, newObject.mNote);
        returnIntent.putExtra(MainActivity.ADD_OBJECT_EXTRA_LAT, newObject.mLatitude);
        returnIntent.putExtra(MainActivity.ADD_OBJECT_EXTRA_LONG, newObject.mLongitude);
        setResult(RESULT_OK, returnIntent);
        finish();

    }


}
