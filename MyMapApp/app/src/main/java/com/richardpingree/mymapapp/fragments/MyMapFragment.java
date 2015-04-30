package com.richardpingree.mymapapp.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.richardpingree.mymapapp.CustomObject;
import com.richardpingree.mymapapp.DetailActivity;
import com.richardpingree.mymapapp.FormActivity;
import com.richardpingree.mymapapp.MainActivity;

import java.util.ArrayList;

/**
 * Created by Richard Pingree MDF3 1504 Week 4 on 4/27/15.
 */
public class MyMapFragment extends MapFragment implements OnInfoWindowClickListener{

    GoogleMap mMap;
    Double mLat = 0.00;
    Double mLong = 0.00;

    private MapListener mListener;
    private ArrayList<CustomObject> mObjectList;


    public interface MapListener{
        public Double getLat();
        public Double getLong();
        public ArrayList<CustomObject> getObjects();
   }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof MapListener){
            mListener = (MapListener) activity;
        }else{
            throw new IllegalArgumentException("Containing activity must implement MapListener interface");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mObjectList = mListener.getObjects();

        mLat = mListener.getLat();
        mLong = mListener.getLong();


        mMap = getMap();
        for(int i = 0; i < mObjectList.size(); i++){
            CustomObject currentObject = mObjectList.get(i);
            Marker cMarker;
            cMarker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(currentObject.mLatitude, currentObject.mLongitude))
                    .title(currentObject.mTitle)
                    .snippet(currentObject.mNote));
                    cMarker.showInfoWindow();
        }
        //mMap.addMarker(new MarkerOptions().position(new LatLng(28.590647, -81.304510)).title("MDVBS Faculty Offices"));

        mMap.setInfoWindowAdapter(new MarkerAdapter());
        mMap.setOnInfoWindowClickListener(this);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLat, mLong), 16));
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Intent addIntent = new Intent(getActivity(), FormActivity.class);
                addIntent.putExtra(FormActivity.GETLAT, latLng.latitude);
                addIntent.putExtra(FormActivity.GETLONG, latLng.longitude);
                startActivityForResult(addIntent, MainActivity.ADD_REQUEST);

            }
        });
    }

    @Override
    public void onInfoWindowClick(final Marker marker) {

        new AlertDialog.Builder(getActivity())
                .setTitle("Marker Clicked")
                .setMessage("You Clicked on: " + marker.getTitle())
                .setPositiveButton("Close", null)
                .setNegativeButton("Details", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent detailIntent = new Intent(getActivity(), DetailActivity.class);
                        detailIntent.putExtra(DetailActivity.TITLE_EXTRA, marker.getTitle());
                        detailIntent.putExtra(DetailActivity.NOTE_EXTRA, marker.getSnippet());
                        detailIntent.putExtra(DetailActivity.LATLONG_EXTRA, marker.getPosition());
                        startActivity(detailIntent);
                    }
                })
                .show();
    }




    private class MarkerAdapter implements GoogleMap.InfoWindowAdapter{
        TextView mText;

        public MarkerAdapter(){
            mText = new TextView(getActivity());
        }

        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker) {
            mText.setText(marker.getTitle() + " " + marker.getSnippet());

            return mText;
        }
    }
}
