package com.example.a2olage06.myapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button download = (Button) findViewById(R.id.download);
        download.setOnClickListener(this);

        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId()==R.id.download)
        {
            startActivity(new Intent(this, DownloadActivity.class));
        }

        else if (view.getId()==R.id.add)
        {
            startActivity(new Intent(this, AddSongActivity.class));
        }
    }
}
