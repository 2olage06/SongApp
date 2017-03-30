package com.example.a2olage06.myapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.net.URL;

public class AddSongActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);

        Button AddButton = (Button) findViewById(R.id.AddButton);
        AddButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        EditText Songtitle = (EditText) findViewById(R.id.Songtitle);
        String songTitle = Songtitle.getText().toString();

        EditText AddArtist = (EditText) findViewById(R.id.AddArtist);
        String artist = AddArtist.getText().toString();

        EditText AddYear = (EditText) findViewById(R.id.AddYear);
        String year = AddYear.getText().toString();

        (new  AddSongAsyncTask()).execute(songTitle, artist, year);
    }

    class AddSongAsyncTask extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String...params)
        {
            String postData = "song="+params[0] + "artist="+params[1] + "year="+params[2];

            try
            {
                URL urlObject = new URL("http://www.free-map.org.uk/course/mad/ws/addhit.php");
            }
            catch(IOException e)
            {
                return "Error: " +e.getMessage();
            }

            return "Error: something went wrong";


        }
    }

}
