package org.snoopdesigns.silentspace.core.bg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.snoopdesigns.silentspace.core.config.SilentSpaceConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BackgroundRenderer {

    private Texture bgTexture;
    private float curYup;
    private float curYdown;
    private float bgX;
    private int BG_HEIGHT;
    private Random rand;
    private List<Star> stars;
    private ShapeRenderer starsRenderer;
    public BackgroundRenderer() {
        rand = new Random();
        bgTexture = new Texture(Gdx.files.internal("bg.jpg"));
        bgX = rand.nextInt(bgTexture.getWidth()- SilentSpaceConfig.GAME_WINDOW_WIDTH);
        System.out.println("Bg X coordinate: " + bgX);
        BG_HEIGHT = bgTexture.getHeight();
        curYdown = 0;
        curYup = BG_HEIGHT;
        starsRenderer = new ShapeRenderer();
        stars = new ArrayList<Star>();
    }

    public void processBackground(SpriteBatch batch) {
        curYup -= SilentSpaceConfig.BG_SCROLL_SPEED * Gdx.graphics.getDeltaTime();
        curYdown -= SilentSpaceConfig.BG_SCROLL_SPEED * Gdx.graphics.getDeltaTime();
        if(curYup < 0) {
            curYdown = curYup;
            curYup = curYdown + BG_HEIGHT;
            System.out.println("BG Change!");
        }
        batch.draw(bgTexture, -bgX, curYdown);
        batch.draw(bgTexture, -bgX, curYup);
    }

    public void processStars() {
        this.generateStars();
        List<Integer> toRemove = new ArrayList<Integer>();
        for(int i=0;i<stars.size();i++) {
            if(stars.get(i).y < 0) toRemove.add(i);
            starsRenderer.begin(ShapeRenderer.ShapeType.Filled);
            starsRenderer.setColor(1, 1, 1, 1);
            stars.get(i).y -= (stars.get(i).speed * Gdx.graphics.getDeltaTime());
            starsRenderer.circle(stars.get(i).x, stars.get(i).y, stars.get(i).size);
            starsRenderer.end();
        }
        for(int i=0;i<toRemove.size();i++) {
            stars.remove(toRemove.get(i));
        }
    }

    private void generateStars() {
        if(rand.nextInt(100) > 95) {
            Star star = new Star();
            star.x = rand.nextInt(SilentSpaceConfig.GAME_WINDOW_WIDTH);
            star.y = SilentSpaceConfig.GAME_WINDOW_HEIGHT;
            star.speed = SilentSpaceConfig.BG_SCROLL_SPEED + rand.nextInt(100);
            if(star.speed >= SilentSpaceConfig.BG_SCROLL_SPEED
                    && star.speed < SilentSpaceConfig.BG_SCROLL_SPEED + 50) {
                star.size = 1;
            } else if(star.speed >= SilentSpaceConfig.BG_SCROLL_SPEED + 50
                    && star.speed <= SilentSpaceConfig.BG_SCROLL_SPEED + 100) {
                star.size = 2;
            }
            stars.add(star);
        }
    }

    private class Star {
        public int x;
        public int y;
        public int speed;
        public int size;
    }
}
