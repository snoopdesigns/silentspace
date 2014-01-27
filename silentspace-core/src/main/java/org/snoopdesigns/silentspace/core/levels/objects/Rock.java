package org.snoopdesigns.silentspace.core.levels.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.snoopdesigns.silentspace.core.config.SilentSpaceConfig;
import java.util.Random;

public class Rock extends LevelObject{

    private float x;
    private float y;
    private Sprite rockTexture;

    public Rock() {
        this.y = SilentSpaceConfig.GAME_WINDOW_HEIGHT;
        this.x = 30;
        rockTexture = new Sprite(new Texture(Gdx.files.internal("rock.png")));
        rockTexture.setRotation(new Random().nextInt(360));
    }

    @Override
    public void process(SpriteBatch batch) {
        this.x = 18 + 70*objectLine;
        this.y -= 1.0f;
        rockTexture.setPosition(x ,y);
        rockTexture.draw(batch);
    }

    @Override
    public float getX() {
        return x + rockTexture.getWidth()/2;
    }

    @Override
    public float getY() {
        return y + rockTexture.getHeight()/2;
    }

    @Override
    public boolean isActive() {
        return this.y > -100;
    }

    @Override
    public void destroy() {
        System.out.println("Object destroyed!");
    }

    @Override
    public FileHandle getAnimationFile() {
        return Gdx.files.internal("effects/animations/explosion.png");
    }

    @Override
    public int getAnimationRows() {
        return 4;
    }

    @Override
    public int getAnimationCols() {
        return 5;
    }

    @Override
    public boolean isExplodable() {
        return true;
    }

    @Override
    public boolean isDestroyble() {
        return true;
    }
}