package org.snoopdesigns.silentspace.core.levels.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface LevelObject {

    public void setLine(int line);
    public void process(SpriteBatch batch);
    public float getX();
    public float getY();
    public boolean isActive();
}
