package com.example.taras.bublesboom.mainPhysics;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class GameManager {

    private MainCircle mainCircle;
    private CanvasView canvasView;
    List<EnemyCircle> circles;
    private static int width;
    private static int height;

    public GameManager(CanvasView canvasView, int w, int h) {
        this.canvasView = canvasView;
        width = w;
        height = h;
        initMainCircle();
        initEnemyCircles();
    }

    private void initEnemyCircles() {
        circles = new ArrayList<>();

    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    private void initMainCircle() {
        mainCircle = new MainCircle(width/2, height/2);
    }

    public void onDraw() {
        canvasView.drawCircle(mainCircle);
    }

    public void onTochEvent(int x, int y) {
        mainCircle.moveMyCircleWhenTouchAt(x, y);
    }
}
