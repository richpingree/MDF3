package com.richardpingree.mappingphotos.fragments;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by richardpingree on 3/24/15.
 */
public class MyMapFragment extends MapFragment {

    GoogleMap mMap;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mMap = getMap();

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(28.593770, -81303797), 0));
    }
}
