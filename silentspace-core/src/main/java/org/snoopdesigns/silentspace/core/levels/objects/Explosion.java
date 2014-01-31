package org.snoopdesigns.silentspace.core.levels.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Explosion extends LevelObject{

    private Animation animation;
    private int frames;
    private float statetime;
    TextureRegion currentFrame;
    private int x;
    private int y;

    public Explosion(int x, int y, Animation anim, int frames) {
        animation = anim;
        this.frames = frames;
        statetime = 0f;
        this.x = x - anim.getKeyFrame(0f).getRegionHeight()/2;
        this.y = y - anim.getKeyFrame(0f).getRegionWidth()/2;
    }

    @Override
    public int getInitialHealth() {
        return 0;
    }

    @Override
    public void process(SpriteBatch batch) {
        statetime += Gdx.graphics.getDeltaTime();
        if(statetime <= animation.animationDuration) {
            currentFrame = animation.getKeyFrame(statetime, true);
            batch.draw(currentFrame, x, y);
        }
    }

    @Override
    public float getX() {
        return 0;
    }

    @Override
    public float getY() {
        return 0;
    }

    @Override
    public boolean isActive() {
        return !animation.isAnimationFinished(statetime);
    }

    @Override
    public void destroy() {
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
        return false;
    }

    @Override
    public boolean isPlayerShip() {
        return false;
    }

    @Override
    public float getCollisionEspilon() {
        return 0;
    }
}
