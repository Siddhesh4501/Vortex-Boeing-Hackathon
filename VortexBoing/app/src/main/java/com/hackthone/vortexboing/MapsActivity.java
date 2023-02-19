package com.hackthone.vortexboing;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hackthone.vortexboing.databinding.ActivityMapsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static String id;
    private int interval = 1000;
    private GoogleMap mMap;
    private Marker marker;
    private ActivityMapsBinding binding;
    JSONArray json = null;
    String SERVER_URL = "http://192.168.75.200:5000/api/getData";

    public void getData() {
        try {
            new AsyncFetch(new AsyncFetch.AsyncResponse() {
                @Override
                public void processFinish(JSONObject data) {
                    try {
                        json = data.getJSONArray("location");
                        if(marker != null)
                            marker.setPosition(new LatLng(json.getDouble(0), json.getDouble(1)));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).execute(SERVER_URL, id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        try {
            id = intent.getStringExtra("id");
            json = new JSONArray(intent.getStringExtra("location"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        new Timer().scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                getData();
                Log.i("interval", "This function is called every 5 seconds.");
            }
        },0,interval);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng loc = null;
        try {
            loc = new LatLng(json.getDouble(0), json.getDouble(1));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        marker = mMap.addMarker(new MarkerOptions().position(loc).title("Vehicle Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
    }
}