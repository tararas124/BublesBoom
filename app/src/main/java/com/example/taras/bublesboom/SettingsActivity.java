package com.example.taras.bublesboom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void musicStart (View view) {
        startService(new Intent(this, MusicService.class));
    }

    public void musicStop (View view) {
        stopService(new Intent(this, MusicService.class));
    }
}
