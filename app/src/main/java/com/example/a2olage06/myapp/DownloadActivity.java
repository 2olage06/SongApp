package com.example.a2olage06.myapp;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadActivity extends Activity implements View.OnClickListener {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Download Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.a2olage06.myapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Download Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.a2olage06.myapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    class MyTask extends AsyncTask<String, Void, String> {
        private static final int HTTP_OK = 200;

        @Override

        protected String doInBackground(String... arguments) {
            String artist = arguments[0];
            String urlText = arguments[1];


            HttpURLConnection conn = null;

            try {
                URL urlObject = new URL("http://www.free-map.org.uk/course/mad/ws/hits.php" + "?artist=" + artist);
                conn = (HttpURLConnection) urlObject.openConnection();

                if (conn.getResponseCode() == 200) {
                    InputStream in = conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String json = "";
                    String line = br.readLine();

                    while (line != null) {
                        json += line;
                        line = br.readLine();
                    }


                    JSONArray jsonArray = new JSONArray(json);

                    String result = "";

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject songObj = jsonArray.getJSONObject(i);

                        String songTitle = songObj.getString("song");
                        String artist1 = songObj.getString("artist");
                        String year = songObj.getString("year");

                        String pretty = "Song Title: " + songTitle;
                        pretty += "Artist: " + artist1;
                        pretty += "year: " + year + "\n";

                        result += pretty;

                    }
                    return result;


                }
                else{
                    return conn.getResponseCode() +"error!";
                }
            } catch (IOException e) {
                return "Error: " + e.getMessage();
            } catch (JSONException e) {
                return "Error: " + e.getMessage();
            }

        }


        @Override
        protected void onPostExecute(String result)

        {
            TextView txt1 = (TextView) findViewById(R.id.txt1);
            txt1.setText(result);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        Button go = (Button) findViewById(R.id.btn1);
        go.setOnClickListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public void onClick(View v) {
        EditText et1 = (EditText) findViewById(R.id.et1);
        String artist = et1.getText().toString();
        EditText url = (EditText) findViewById((R.id.urlText));
        String urlText = url.getText().toString();
        MyTask t = new MyTask();
        t.execute(artist, urlText);
    }
}