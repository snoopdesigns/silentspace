package org.snoopdesigns.silentspace.core.levels.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.snoopdesigns.silentspace.core.player.PlayerShip;

public abstract class DropDownLevelObject extends LevelObject{

    public float x;
    public float y;
    public Texture texture;

    public DropDownLevelObject() {
        this.texture = this.gerDropDownItemTexture();
    }

    public void setStartX(float x) {
        this.x = x - texture.getWidth()/2;
    }

    public void setStartY(float y) {
        this.y = y - texture.getHeight()/2;
    }

    public abstract void updatePlayer(PlayerShip ship);
    public abstract Texture gerDropDownItemTexture();

    @Override
    public void process(SpriteBatch batch) {
        this.y -=1;
        batch.draw(texture, x, y);
    }

    @Override
    public float getX() {
        return this.x + texture.getWidth()/2;
    }

    @Override
    public float getY() {
        return this.y + texture.getHeight()/2;
    }

    @Override
    public boolean isActive() {
        return y > -50;
    }

    @Override
    public FileHandle getAnimationFile() {
        return Gdx.files.internal("effects/animations/air-blast.png");
    }

    @Override
    public int getAnimationRows() {
        return 2;
    }

    @Override
    public int getAnimationCols() {
        return 5;
    }

    @Override
    public boolean isExplodable() {
        return false;
    }

    @Override
    public boolean isDestroyble() {
        return false;
    }

    @Override
    public boolean isCatchable() {
        return true;
    }


    @Override
    public boolean isPlayerShip() {
        return false;
    }
}
