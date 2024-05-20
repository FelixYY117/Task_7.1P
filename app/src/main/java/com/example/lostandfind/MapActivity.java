package com.example.lostandfind;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private DatabaseHelper databaseHelper;
    private static final String TAG = "MapActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        } else {
            Log.e(TAG, "SupportMapFragment is null");
        }

        databaseHelper = new DatabaseHelper(this);

    }

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        map = googleMap;
//
//        List<Item> itemList = databaseHelper.getAllItems();
//        for (Item item : itemList) {
//            LatLng latLng = new LatLng(item.getLatitude(), item.getLongitude());
//            map.addMarker(new MarkerOptions().position(latLng).title(item.getItemName()));
//        }
//    }
@Override
public void onMapReady(GoogleMap googleMap) {
    map = googleMap;
    if (map == null) {
        Log.e(TAG, "GoogleMap is null");
        return;
    }

    List<Item> itemList = databaseHelper.getAllItems();
    if (itemList == null) {
        Log.e(TAG, "Item list is null");
        return;
    }

    for (Item item : itemList) {
        LatLng latLng = new LatLng(item.getLatitude(), item.getLongitude());
        map.addMarker(new MarkerOptions().position(latLng).title(item.getItemName()));
    }
}
}