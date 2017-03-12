package com.example.taras.bublesboom;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {

    private static String Tag = null;
    MediaPlayer player;
    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.hello);
        player.setLooping(true);
        player.setVolume(100, 100);

        Intent intent = new Intent(this, SettingsActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Music")
                .setContentText("Do you wanna turn music off?")
                .build();

        startForeground(100, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        return startId;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
        player.release();
    }
}
