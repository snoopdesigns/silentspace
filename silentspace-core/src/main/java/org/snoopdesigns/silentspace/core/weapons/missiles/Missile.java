package org.snoopdesigns.silentspace.core.weapons.missiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import org.snoopdesigns.silentspace.core.config.SilentSpaceConfig;

public abstract class Missile {

    private Array<ParticleEffect> particleEffects;

    public Missile() {
        if(!this.useTexture()) {
            particleEffects = new Array<ParticleEffect>();
            for(int i=0;i<this.getMissilesPerShot();i++) {
                particleEffects.add(this.getParticleEffect());
            }

        }
    }

    public void processMissile(SpriteBatch batch) {
        for(int i=0;i<this.getMissilesInfo().size;i++) {
            float dist = (this.getMissilesInfo().get(i).speed * Gdx.graphics.getDeltaTime());
            this.getMissilesInfo().get(i).y += Math.cos(Math.toRadians(this.getMissilesInfo().get(i).angle)) * dist;
            this.getMissilesInfo().get(i).x += Math.sin(Math.toRadians(this.getMissilesInfo().get(i).angle)) * dist;
            if(this.useTexture()) {
                batch.draw(this.getMissileTexture(), this.getMissilesInfo().get(i).x,
                        this.getMissilesInfo().get(i).y);
            } else {
                particleEffects.get(i).setPosition(this.getMissilesInfo().get(i).x, this.getMissilesInfo().get(i).y);
                particleEffects.get(i).update(Gdx.graphics.getDeltaTime());
                particleEffects.get(i).draw(batch);
            }
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
    public abstract boolean useTexture();
    public abstract ParticleEffect getParticleEffect();
    public abstract int getMissilesPerShot();

    private boolean checkBounds(float x, float y) {
        if(x > 0 && x < SilentSpaceConfig.GAME_WINDOW_WIDTH &&
                y > 0 && y < SilentSpaceConfig.GAME_WINDOW_HEIGHT) {
            return true;
        } else {
            return false;
        }
    }

    public void destroyMissile(Array<Integer> toRemove) {
        System.out.println("Missile destroyed!");
        toRemove.reverse();
        for(int i=0;i<toRemove.size;i++) {
            this.getMissilesInfo().removeIndex(toRemove.get(i));
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

        public float getX() {return x;}
        public float getY() {return y;}
    }
}
