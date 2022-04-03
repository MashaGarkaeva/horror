package com.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.game.Horror;
import com.game.player.Face;
import com.game.player.Items;
import com.game.player.Player;

import java.util.Random;

public class PlayState extends State{
    public static final int ITEMS_SPACING = 95;//расстояние по ширине
    public static final int ITEMS_COUNT = 4;
    public static final int ZEMLYA_Y = -60;

    private Player player;
    private Texture background;
    private Texture zemlya;
    private Vector2 zemlyaPos1, zemlyaPos2;
    private Sound sound;
    private Array<Items> itemsArray;
    private Random random;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, Horror.WIDTH, Horror.HEIGHT);

        background = new Texture("fon1.jpg");
        zemlya = new Texture("zemlya.jpg");
        player = new Player(20, 250);
        zemlyaPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, ZEMLYA_Y);
        zemlyaPos2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + zemlya.getWidth(), ZEMLYA_Y);
        sound = Gdx.audio.newSound(Gdx.files.internal("cat.mp3"));
        itemsArray = new Array<>();

        for(int i = 1; i < ITEMS_COUNT; i ++){
            itemsArray.add(new Items( i * (ITEMS_SPACING + Items.ITEMS_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            player.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateZemlya();
        player.update(dt);
        camera.position.x = player.getPosition().x + 80;

        for (int i = 0; i < itemsArray.size; i ++){
            Items items = itemsArray.get(i);
            if (camera.position.x - (camera.viewportWidth / 2) > items.getPosTop().x + items.getTop().getWidth()){
                items.reposition(items.getPosTop().x + ((Items.ITEMS_WIDTH + ITEMS_SPACING) * ITEMS_COUNT));
            }


            if (items.collides(player.getBounds())) {
                sound.play();
                gsm.set(new GameOver(gsm));
            }

        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, camera.position.x - (camera.viewportWidth / 2), 0);
        sb.draw(player.getPlayer(), player.getPosition().x, player.getPosition().y);
        for (Items items : itemsArray){
            sb.draw(items.getTop(), items.getPosTop().x, items.getPosTop().y);
            sb.draw(items.getBotton(), items.getPosBotton().x, items.getPosBotton().y);
        }
        sb.draw(zemlya, zemlyaPos1.x, zemlyaPos1.y);
        sb.draw(zemlya, zemlyaPos2.x, zemlyaPos2.y);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        player.dispose();
        zemlya.dispose();
        sound.dispose();
        for (Items items : itemsArray){
            items.dispose();
        }
    }

    private void updateZemlya(){
        if (camera.position.x - (camera.viewportWidth / 2) > zemlyaPos1.x + zemlya.getWidth()){
            zemlyaPos1.add(zemlya.getWidth() * 2, 0);
        }
        if (camera.position.x - (camera.viewportWidth / 2) > zemlyaPos2.x + zemlya.getWidth()){
            zemlyaPos2.add(zemlya.getWidth() * 2, 0);
        }
    }
}
