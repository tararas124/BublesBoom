package com.example.taras.bublesboom.mainPhysics;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.Toast;

import com.example.taras.bublesboom.MainGame;

import java.util.ArrayList;
import java.util.List;


public class GameManager {

    public static final int MAX_CIRCLES = 10;
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
        SimpleCircle mainCircleArea = mainCircle.getCircleArea();
        circles = new ArrayList<>();
        for(int i = 0; i < MAX_CIRCLES; i++) {
            EnemyCircle circle;
            do {
                circle = EnemyCircle.getRandomCircle(mainCircle);
            } while (circle.isIntersect(mainCircleArea));
            circles.add(circle);
        }
        calculateAndSetColor();

    }

    private void calculateAndSetColor() {
        for (EnemyCircle circle : circles) {
            circle.setEnemyColor(mainCircle);
        }
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
        for (EnemyCircle circle : circles) {
            canvasView.drawCircle(circle);
        }
    }

    public void onTouchEvent(int x, int y) {
        mainCircle.moveMyCircleWhenTouchAt(x, y);
        checkCollision();
        moveCircles();
    }

    private void checkCollision() {
        for (EnemyCircle circle : circles) {
            if(mainCircle.isIntersect(circle)) {
                if(circle.isSmaller(mainCircle)) {
                    mainCircle.growRadius(circle);
                    circles.remove(circle);
                    calculateAndSetColor();
                    break;
                } else {
                    MainGame.lose++;
                    gameEnd("You Lose!!!");
                    return;
                }
            }
        }
        if(circles.isEmpty()) {
            MainGame.win++;
            gameEnd("You Win!!!");
        }
    }

    private void gameEnd(String text) {
        canvasView.showMessage(text);
        mainCircle.initRadius();
        initEnemyCircles();
        canvasView.redraw();
    }

    private void moveCircles() {
        for (EnemyCircle circle : circles) {
            circle.moveOnStep();
        }
    }
}
