package com.game.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Player {
    private static final int MOVEMENT = 100;
    private static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 speed;
    private Rectangle bounds;

    private Texture player;

    public Player(int x, int y){
        position = new Vector3(x, y, 0);
        speed = new Vector3(0, 0, 0);
        player =new Texture("cat2.png");
        bounds = new Rectangle(x, y, 50, 50);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getPlayer() {
        return player;
    }

    public void update(float dt){
        if (position.y > 0)
            speed.add(0, GRAVITY, 0);
        speed.scl(dt);
        position.add(MOVEMENT * dt, speed.y, 0);
        if (position.y < 0){
            position.y = 0;
        }
        speed.scl(1 / dt);
        bounds.setPosition(position.x, position.y);

    }
    public void jump(){
        speed.y = 200;
    }
    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        player.dispose();
    }

}
