package org.snoopdesigns.silentspace.core.levels.objects;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.snoopdesigns.silentspace.core.player.PlayerShip;

public abstract class DropDownLevelObject extends LevelObject{

    public float x;
    public float y;

    public void setStartX(float x) {
        this.x = x;
    }

    public void setStartY(float y) {
        this.y = y;
    }

    public abstract void updatePlayer(PlayerShip ship);

    @Override
    public boolean isActive() {
        return y > -50;
    }

    @Override
    public FileHandle getAnimationFile() {
        return null;
    }

    @Override
    public int getAnimationRows() {
        return 0;
    }

    @Override
    public int getAnimationCols() {
        return 0;
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
}
