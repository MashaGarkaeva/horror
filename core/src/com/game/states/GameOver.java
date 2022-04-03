package com.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.game.Horror;
import com.game.player.Face;

import java.util.Random;

public class GameOver extends State{

    private Texture background;
    private Sound sound;


    public GameOver(GameStateManager gsm) {
        super(gsm);
        sound = Gdx.audio.newSound(Gdx.files.internal("krik.mp3"));
        sound.play();
        background = new Texture("scrimer.jpg");
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.begin();
        sb.draw(background, (Horror.WIDTH - background.getWidth()) / 2, Horror.HEIGHT / 3.5f);
        sb.end();
    }


    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void dispose() {
        sound.dispose();
        background.dispose();
    }
}
