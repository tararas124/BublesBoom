package com.example.taras.bublesboom.mainPhysics;


import android.graphics.Color;

public class MainCircle extends SimpleCircle {
    public static final int INIT_RADIUS = (GameManager.getWidth() + GameManager.getHeight()) / 20;
    public static final int MAIN_SPEED = (GameManager.getWidth() + GameManager.getHeight()) / 40;
    public static final int MAIN_COLOR = Color.BLACK;

    public MainCircle(int x, int y) {
        super(x, y, (GameManager.getWidth() + GameManager.getHeight()) / 30);
        setColor(MAIN_COLOR);
    }

    public void moveMyCircleWhenTouchAt(int x1, int y1) {
        int dx = (x1 - x) *  MAIN_SPEED / GameManager.getWidth();
        int dy = (y1 - y) * MAIN_SPEED / GameManager.getHeight();
        x += dx;
        y += dy;
    }

    public void initRadius() {
        radius = INIT_RADIUS;
    }

    public void growRadius(EnemyCircle circle) {
        radius = (int) Math.sqrt(Math.pow(radius,2) + Math.pow(circle.radius, 2));
    }
}
