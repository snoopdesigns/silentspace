package org.snoopdesigns.silentspace.core.levels.objects;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class LevelObject {

    public int objectLine = 0;

    public void setLine(int line) {
        objectLine = line;
    }
    public abstract void process(SpriteBatch batch);
    public abstract float getX();
    public abstract float getY();
    public abstract boolean isActive();
    public abstract void destroy();
    public abstract FileHandle getAnimationFile();
    public abstract int getAnimationRows();
    public abstract int getAnimationCols();
    public abstract boolean isExplodable();
    public abstract boolean isDestroyble();
    public Animation getAnimation(int frame_cols, int frame_rows) {
        Animation animation;
        Texture sheet;
        TextureRegion[] frames;
        sheet = new Texture(this.getAnimationFile());
        TextureRegion[][] tmp = TextureRegion.split(sheet,
                sheet.getWidth()/frame_cols, sheet.getHeight()/frame_rows);
        frames = new TextureRegion[frame_cols * frame_rows];
        int index = 0;
        for (int i = 0; i < frame_rows; i++) {
            for (int j = 0; j < frame_cols; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        animation = new Animation(0.025f, frames);
        return animation;
    }
}
