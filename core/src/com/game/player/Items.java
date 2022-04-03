package com.game.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Items {
    public static final int ITEMS_WIDTH = 52;
    public static final int FLUCTUATION = 130;//диапазон откланений появления препятствий
    public static final int ITEMS_GAP = 100;//высота просвета между препятствиями
    public static final int LOWEST_OPENING = 120;//нижняя граница просвета

    private Texture top;
    private Texture botton;
    private Vector2 posTop;
    private Vector2 posBotton;
    private Random random;
    private Rectangle boundsTop, boundsBotton;

    public Texture getTop() {
        return top;
    }

    public Texture getBotton() {
        return botton;
    }

    public Vector2 getPosTop() {
        return posTop;
    }

    public Vector2 getPosBotton() {
        return posBotton;
    }

    public Items(float x){
        top = new Texture("top3.jpg");
        botton = new Texture("rukatwo.jpg");
        random = new Random();
        posTop = new Vector2(x, random.nextInt(FLUCTUATION) + ITEMS_GAP + LOWEST_OPENING);
        posBotton = new Vector2(x, posTop.y - ITEMS_GAP - botton.getHeight());

        boundsTop = new Rectangle(posTop.x, posTop.y, 50, 90);
        boundsBotton = new Rectangle(posBotton.x, posBotton.y, 50, 90);

    }

    public void reposition(float x){
        posTop.set(x, random.nextInt(FLUCTUATION) + ITEMS_GAP + LOWEST_OPENING);
        posBotton.set(x, posTop.y - ITEMS_GAP - botton.getHeight());

        boundsTop.setPosition(posTop.x, posTop.y);
        boundsBotton.setPosition(posBotton.x, posBotton.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBotton);
    }

    public void dispose(){
        top.dispose();
        botton.dispose();
    }
}
