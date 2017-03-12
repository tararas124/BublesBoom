package com.example.taras.bublesboom;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taras.bublesboom.mainPhysics.CanvasView;

public class MainGame extends Activity {

    static TextView textView;
    public static int win = 0;
    public static int lose = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
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
}
