package com.hackthone.vortexboing;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public static String id;
    private int interval = 1000;
    TextView materialStatus, sensorStatus, materialTemperature, freezerTemperature, status;
    Button track;
    JSONArray location;
    String SERVER_URL = "http://192.168.75.200:5000/api/getData";

    public void getData() {
        try {
            new AsyncFetch(new AsyncFetch.AsyncResponse() {
                @Override
                public void processFinish(JSONObject json) {
                    if(json == null)
                        return;
                    try {
                        materialStatus.setText(json.getString("materialSensorStatus"));
                        sensorStatus.setText(json.getString("sensorStatus"));
                        materialTemperature.setText(json.getString("materialTemp"));
                        freezerTemperature.setText(json.getString("freezerTemp"));
                        status.setText((json.getJSONArray("materialAnalysis")).getString(1));
                        location = json.getJSONArray("location");
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).execute(SERVER_URL, id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = getIntent();
        id = intent.getStringExtra("uuid");

        materialStatus = findViewById(R.id.material_stat);
        sensorStatus = findViewById(R.id.sensor_stat);
        materialTemperature = findViewById(R.id.material_temp);
        freezerTemperature = findViewById(R.id.freezer_temp);
        status = findViewById(R.id.status);

        track = findViewById(R.id.track);

        new Timer().scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                getData();
                Log.i("interval", "This function is called every 5 seconds.");
            }
        },0, interval);

        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, MapsActivity.class);
                myIntent.putExtra("location", location.toString());
                myIntent.putExtra("id", id.toString());
                MainActivity.this.startActivity(myIntent);
            }
        });
    }
}
