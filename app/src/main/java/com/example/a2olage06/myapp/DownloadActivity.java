package com.example.a2olage06.myapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadActivity extends Activity implements View.OnClickListener{

    class MyTask extends AsyncTask<String,Void,String>
    {
        private static final int HTTP_OK = 200;

        @Override

        protected String doInBackground(String... arguments) {
            String artist = arguments[0];
            String urlText = arguments[1];


            HttpURLConnection conn = null;

            try {
                URL urlObject = new URL(urlText + "?artist=" + artist);
                conn = (HttpURLConnection) urlObject.openConnection();

                if (conn.getResponseCode() == HTTP_OK) {
                    InputStream in = conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String lines = "";
                    String line = br.readLine();
                    while (line != null) {
                        lines += line;
                        line = br.readLine();
                    }
                    return lines;
                }


            }
            catch (IOException e) {
                return "Error: " +e.getMessage();
            }
            return "Error: something went wrong";

        }

        @Override
        protected  void onPostExecute(String result)

        {
            TextView txt1 = (TextView) findViewById(R.id.txt1);
            txt1.setText(result);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        Button go = (Button)findViewById(R.id.btn1);
        go.setOnClickListener(this);
    }


    public void onClick(View v)
    {
        EditText et1 = (EditText)findViewById(R.id.et1);
        String artist = et1.getText().toString();
        EditText url = (EditText)findViewById((R.id.urlText));
        String urlText = url.getText().toString();
        MyTask t = new MyTask();
        t.execute(artist, urlText);
    }
}