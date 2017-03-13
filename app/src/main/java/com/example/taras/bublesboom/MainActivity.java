package com.example.taras.bublesboom;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivityForResult(new Intent(this, MainGameActivity.class), 1);
    }

    public void startGame(View view) {
        Intent intent = new Intent(this, MainGameActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(new Intent(this, MusicService.class));
    }

    public void musicChange(View view) {
        if(isMyServiceRunning(MusicService.class)) {
            view.setBackgroundResource(R.drawable.music_off);
            stopService(new Intent(this, MusicService.class));
        } else {
            view.setBackgroundResource(R.drawable.music_on);
            startService(new Intent(this, MusicService.class));
        }
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TextView textView = (TextView) findViewById(R.id.textView);

        textView.setText("SCORE :" + data.getIntExtra("win", 0) + " - " + data.getIntExtra("lose", 0));
    }
}
