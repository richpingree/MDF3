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

/**
 * Created by Richard Pingree MDF3 1504 Week 4 on 4/27/15.
 */
public class MainActivity extends Activity implements LocationListener{

    private static final int REQUEST_ENABLE_GPS = 0;
    private static final int ADD_BTN_REQUEST = 1;
    private static final String ADD_LAT = "Latitude";
    private static final String ADD_LONG = "Longitude";


    double latitude;
    double longitude;

    LocationManager mManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mManager = (LocationManager)getSystemService(LOCATION_SERVICE);

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
        enableGPS();
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


}
