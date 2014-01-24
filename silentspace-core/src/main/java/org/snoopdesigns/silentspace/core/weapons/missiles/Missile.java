package org.snoopdesigns.silentspace.core.weapons.missiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import org.snoopdesigns.silentspace.core.config.SilentSpaceConfig;

public abstract class Missile {

    public void processMissile(SpriteBatch batch) {
        for(int i=0;i<this.getMissilesInfo().size;i++) {
            System.out.println("Moving missile: " + this.getMissilesInfo().get(i).y + " " + this.getMissilesInfo().get(i).speed);
            this.getMissilesInfo().get(i).y += (this.getMissilesInfo().get(i).speed * Gdx.graphics.getDeltaTime());
            batch.draw(this.getMissileTexture(), this.getMissilesInfo().get(i).x,
                    this.getMissilesInfo().get(i).y);
            System.out.println("Moving missile: " + this.getMissilesInfo().get(i).y + " " + (this.getMissilesInfo().get(i).speed * Gdx.graphics.getDeltaTime()));
        }
        for(int i=0;i<this.getMissilesInfo().size;i++) {
            if(!checkBounds(this.getMissilesInfo().get(i).x, this.getMissilesInfo().get(i).y)) {
                this.getMissilesInfo().removeIndex(i);
            }
        }
    }
    public boolean isMissileInactive() {
        return (getMissilesInfo().size == 0);
    }
    public abstract Missile newInstance(int x, int y);
    public abstract Array<MissileInfo> getMissilesInfo();
    public abstract Texture getMissileTexture();

    private boolean checkBounds(float x, float y) {
        if(x > 0 && x < SilentSpaceConfig.GAME_WINDOW_WIDTH &&
                y > 0 && y < SilentSpaceConfig.GAME_WINDOW_HEIGHT) {
            return true;
        } else {
            return false;
        }
    }

    public class MissileInfo {

        public MissileInfo(int startPointX, int startPointY, int speed, int angle) {
            this.x = startPointX;
            this.y = startPointY;
            this.speed = speed;
            this.angle = angle;
        }
        protected int speed;
        protected int angle;
        protected float x;
        protected float y;
    }
}
