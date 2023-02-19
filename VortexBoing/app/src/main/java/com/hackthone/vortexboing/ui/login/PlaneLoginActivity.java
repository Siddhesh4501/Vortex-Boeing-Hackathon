package com.hackthone.vortexboing.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.hackthone.vortexboing.MainActivity;
import com.hackthone.vortexboing.databinding.ActivityPlaneLoginBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class PlaneLoginActivity extends AppCompatActivity {

    private ActivityPlaneLoginBinding binding;
    String SERVER_URL = "http://192.168.75.200:5000/api/login";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPlaneLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.login;
        final ProgressBar loadingProgressBar = binding.loading;

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                usernameEditText.setEnabled(false);
                passwordEditText.setEnabled(false);
                loginButton.setEnabled(false);

                new AsyncLogin(new AsyncLogin.AsyncResponse() {
                    @Override
                    public void processFinish(JSONObject output) {
                        if(output == null) {
                            usernameEditText.setEnabled(true);
                            passwordEditText.setEnabled(true);
                            loginButton.setEnabled(true);
                            loadingProgressBar.setVisibility(View.INVISIBLE);
                            return;
                        }
                        try {
                            loadingProgressBar.setVisibility(View.INVISIBLE);
                            Intent myIntent = new Intent(PlaneLoginActivity.this, MainActivity.class);
                            Log.d("ID", output.toString());
                            myIntent.putExtra("uuid", output.getString("uuid"));
                            PlaneLoginActivity.this.startActivity(myIntent);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).execute(SERVER_URL, usernameEditText.getText().toString(), passwordEditText.getText().toString());

            }
        });
    }
}