package com.richardpingree.mymapapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.richardpingree.mymapapp.fragments.MyMapFragment;

import java.util.ArrayList;

/**
 * Created by Richard Pingree MDF3 1504 Week 4 on 4/27/15.
 */
public class MainActivity extends Activity implements LocationListener, MyMapFragment.MapListener{

    private static final int REQUEST_ENABLE_GPS = 0;
    private static final int ADD_BTN_REQUEST = 1;
    public static final int ADD_REQUEST = 2;

    public static final String ADD_OBJECT_EXTRA_TITLE = "Title";
    public static final String ADD_OBJECT_EXTRA_NOTE = "Note";
    public static final String ADD_OBJECT_EXTRA_LAT = "Lat";
    public static final String ADD_OBJECT_EXTRA_LONG = "Long";


    Double latitude, longitude;
    public CustomObject newObject;
    public ArrayList<CustomObject> mObjectDataList;

    LocationManager mManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mObjectDataList = new ArrayList<CustomObject>();
//            mObjectDataList.add(new CustomObject("IHop", "Food", 37.423752, -122.084170));
//            mObjectDataList.add(new CustomObject("Friday's", "Food", 37.421690, -122.080673));
        mManager = (LocationManager)getSystemService(LOCATION_SERVICE);

        enableGPS();

        if(FileUtility.loadFile(this) != null){
            mObjectDataList = FileUtility.loadFile(this);
        }

        MyMapFragment frag = new MyMapFragment();
        getFragmentManager().beginTransaction().replace(R.id.container, frag).commit();
    }

    private void enableGPS(){
        if(mManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            mManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);

            Location location = mManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                Log.i("main", String.valueOf(latitude));
                Log.i("main", String.valueOf(longitude));
            }

        } else{
                new AlertDialog.Builder(this)
                        .setTitle("GPS DISABLED")
                        .setMessage("Please enable GPS in the system steeings")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(settingsIntent, REQUEST_ENABLE_GPS);
                    }
                })
                .show();
            }
        }


    @Override
    protected void onResume() {
        super.onResume();

        enableGPS();
    }



    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == Activity.RESULT_OK && requestCode == ADD_BTN_REQUEST){
            newObject = new CustomObject();

            newObject.mTitle = data.getStringExtra(ADD_OBJECT_EXTRA_TITLE);
            newObject.mNote = data.getStringExtra(ADD_OBJECT_EXTRA_NOTE);
            newObject.mLatitude = data.getDoubleExtra(ADD_OBJECT_EXTRA_LAT, 0);
            newObject.mLongitude = data.getDoubleExtra(ADD_OBJECT_EXTRA_LONG, 0);


            mObjectDataList.add(newObject);




        }else if(resultCode == Activity.RESULT_OK && requestCode == ADD_REQUEST){
            newObject = new CustomObject();

            newObject.mTitle = data.getStringExtra(ADD_OBJECT_EXTRA_TITLE);
            newObject.mNote = data.getStringExtra(ADD_OBJECT_EXTRA_NOTE);
            newObject.mLatitude = data.getDoubleExtra(ADD_OBJECT_EXTRA_LAT, 0);
            newObject.mLongitude = data.getDoubleExtra(ADD_OBJECT_EXTRA_LONG, 0);


            mObjectDataList.add(newObject);


        }

    }

    public void addBtnMethod(){
        enableGPS();
        Intent addBtnIntent = new Intent(this, FormActivity.class);
        addBtnIntent.putExtra(FormActivity.GETLAT, latitude);
        addBtnIntent.putExtra(FormActivity.GETLONG, longitude);
        startActivityForResult(addBtnIntent, ADD_BTN_REQUEST);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            addBtnMethod();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public Double getLat() {
        return latitude;
    }

    @Override
    public Double getLong() {
        return longitude;
    }

    @Override
    public ArrayList<CustomObject> getObjects() {
        return mObjectDataList;
    }
}
