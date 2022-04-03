package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.states.GameStateManager;
import com.game.states.MenuState;

public class Horror extends ApplicationAdapter {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 600;
    public static final String TITLE = "Don't fall";

    private GameStateManager gms;
    private SpriteBatch batch;

    private Music music;

    @Override
    public void create() {
        batch = new SpriteBatch();
        gms = new GameStateManager();
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);
        music.play();
        music.setVolume(0.1f);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        gms.push(new MenuState(gms));
    }

    @Override
    public void render(){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gms.update(Gdx.graphics.getDeltaTime());
        gms.render(batch);
        //music.dispose();
    }
}

