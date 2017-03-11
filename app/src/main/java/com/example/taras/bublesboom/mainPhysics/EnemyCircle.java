package com.example.taras.bublesboom.mainPhysics;

import android.graphics.Color;

import java.util.Random;

public class EnemyCircle extends SimpleCircle {
    public static final int TO_RADIUS = 110;
    public static final int FROM_RADIUS = 10;
    public static final int ENEMY_COLOR = Color.RED;
    public static final int FOOD_COLOR = Color.GREEN;
    public static final int ENEMY_SPEED = (GameManager.getWidth() + GameManager.getHeight()) / 200;
    private int dx;
    private int dy;

    public EnemyCircle(int x, int y, int radius, int dx, int dy) {
        super(x, y, radius);
        this.dx = dx;
        this.dy = dy;
    }

    public static EnemyCircle getRandomCircle(MainCircle mainCircle) {
        Random random = new Random();
        int x = random.nextInt(GameManager.getWidth());
        int y = random.nextInt(GameManager.getHeight());
        int dx = 1 + random.nextInt(ENEMY_SPEED);
        int dy = 1 + random.nextInt(ENEMY_SPEED);
        int radius = mainCircle.getRadius()/5 + random.nextInt(mainCircle.getRadius()*2 - mainCircle.getRadius()/5);
        EnemyCircle enemyCircle = new EnemyCircle(x, y, radius, dx, dy);
        return enemyCircle;
    }

    public void setEnemyColor(MainCircle mainCircle) {
        if(isSmaller(mainCircle)) {
            setColor(FOOD_COLOR);
        } else {
            setColor(ENEMY_COLOR);
        }
    }

    public boolean isSmaller(SimpleCircle circle) {
        if(radius < circle.radius) {
            return true;
        }
        return false;
    }

    public void moveOnStep() {
        x += dx;
        y += dy;
        checkBounds();
    }

    private void checkBounds() {
        if(x > GameManager.getWidth() || x < 0) {
            dx = -dx;
        }
        if(y > GameManager.getHeight() || y < 0) {
            dy = -dy;
        }
    }


}
