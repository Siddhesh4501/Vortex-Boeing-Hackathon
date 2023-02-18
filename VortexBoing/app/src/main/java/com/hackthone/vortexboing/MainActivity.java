package com.hackthone.vortexboing;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView materialStatus, sensorStatus, materialTemperature, freezerTemperature;
    Button track;
    JSONArray location;
    String SERVER_URL = "http://192.168.75.200:5000/api/getData";

    public void getData() {
        try {
            new AsyncFetch(new AsyncFetch.AsyncResponse() {
                @Override
                public void processFinish(JSONObject json) {
                    try {
                        materialStatus.setText(json.getString("materialSensorStatus"));
                        sensorStatus.setText(json.getString("sensorStatus"));
                        materialTemperature.setText(json.getString("materialTemp"));
                        freezerTemperature.setText(json.getString("freezerTemp"));
                        location = json.getJSONArray("location");
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).execute(SERVER_URL);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        materialStatus = findViewById(R.id.material_stat);
        sensorStatus = findViewById(R.id.sensor_stat);
        materialTemperature = findViewById(R.id.material_temp);
        freezerTemperature = findViewById(R.id.freezer_temp);

        track = findViewById(R.id.track);

        new Timer().scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                getData();
                Log.i("interval", "This function is called every 5 seconds.");
            }
        },0,5000);


        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, MapsActivity.class);
                myIntent.putExtra("location", location.toString());
                MainActivity.this.startActivity(myIntent);
            }
        });

    }
}
