package org.snoopdesigns.silentspace.core.bg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.snoopdesigns.silentspace.core.config.SilentSpaceConfig;

import java.util.Random;

public class BackgroundRenderer {

    private Texture bgTexture;
    private float curYup;
    private float curYdown;
    private float bgX;
    private int BG_HEIGHT;
    private Random rand;
    public BackgroundRenderer() {
        rand = new Random();
        bgTexture = new Texture(Gdx.files.internal("bg.jpg"));
        bgX = rand.nextInt(bgTexture.getWidth());
        BG_HEIGHT = bgTexture.getHeight();
        curYdown = 0;
        curYup = BG_HEIGHT;
    }

    public void processBackground(SpriteBatch batch) {
        curYup -= SilentSpaceConfig.BG_SCROLL_SPEED * Gdx.graphics.getDeltaTime();
        curYdown -= SilentSpaceConfig.BG_SCROLL_SPEED * Gdx.graphics.getDeltaTime();
        if(curYup < 0) {
            curYdown = curYup;
            curYup = curYdown + BG_HEIGHT;
            System.out.println("BG Change!");
        }
        batch.draw(bgTexture, 0, curYdown);
        batch.draw(bgTexture, 0, curYup);
    }
}
