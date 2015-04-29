package com.richardpingree.mymapapp.fragments;

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
import com.richardpingree.mymapapp.DetailActivity;

/**
 * Created by Richard Pingree MDF3 1504 Week 4 on 4/27/15.
 */
public class MyMapFragment extends MapFragment implements OnInfoWindowClickListener {

    GoogleMap mMap;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mMap = getMap();
        mMap.addMarker(new MarkerOptions().position(new LatLng(28.590647, -81.304510)).title("MDVBS Faculty Offices"));

        mMap.setInfoWindowAdapter(new MarkerAdapter());
        mMap.setOnInfoWindowClickListener(this);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(28.593770, -81.303797), 16));
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
            mText.setText(marker.getTitle());
            return mText;
        }
    }
}
