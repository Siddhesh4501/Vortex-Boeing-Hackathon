package com.hackthone.vortexboing.ui.login;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class AsyncLogin extends AsyncTask<String, Integer, JSONObject> {
    public AsyncResponse delegate = null;
    public interface AsyncResponse {
        void processFinish(JSONObject output);
    }
    public AsyncLogin(AsyncResponse delegate){
        this.delegate = delegate;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        try {
            String username = strings[1];
            String password = strings[2];
            String object = "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";

            URL url = new URL(strings[0]);

            HttpURLConnection con = null;

            con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestMethod("POST");
            con.setConnectTimeout(5000);
            con.setDoOutput(true);
            con.setDoInput(true);

            OutputStream os = con.getOutputStream();
            os.write(object.getBytes(StandardCharsets.UTF_8));
            os.close();

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                con.disconnect();
                return new JSONObject(content.toString());
            } else {
                con.disconnect();
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(JSONObject s) {
        super.onPostExecute(s);
        delegate.processFinish(s);
    }
}
