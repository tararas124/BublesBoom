package com.example.taras.bublesboom;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taras.bublesboom.mainPhysics.CanvasView;

public class MainGameActivity extends Activity {

    static TextView textView;
    private static final String WIN = "win";
    private static final String LOSE = "lose";
    public static int win = 0;
    public static int lose = 0;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        loadData();
        textView = (TextView) findViewById(R.id.score);
        textView.setText(win + " - " + lose);
    }

    public static void changeScore() {
        try {
            textView.setText(win + " - " + lose);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveData() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(WIN, win);
        editor.putInt(LOSE, lose);
        editor.apply();
    }

    public void loadData() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        win = sharedPreferences.getInt(WIN, 0);
        lose = sharedPreferences.getInt(LOSE, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveData();
    }
}
