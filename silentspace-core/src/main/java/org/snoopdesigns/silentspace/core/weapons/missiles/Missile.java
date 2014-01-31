package org.snoopdesigns.silentspace.core.weapons.missiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import org.snoopdesigns.silentspace.core.config.SilentSpaceConfig;

public abstract class Missile {

    private Array<ParticleEffect> particleEffects;
    public Array<MissileInfo> info = new Array<MissileInfo>();
    private ParticleEffect textureParticleEffect;

    public Missile() {
        if(!this.useTexture()) {
            particleEffects = new Array<ParticleEffect>();
            for(int i=0;i<this.getMissilesPerShot();i++) {
                particleEffects.add(this.getParticleEffect());
            }
        }
        if(this.useParticlesForTexture()) {
            this.textureParticleEffect = this.getParticleEffectForTexture();
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
                if(this.useParticlesForTexture()) {
                    textureParticleEffect.setPosition(this.getMissilesInfo().get(i).x + this.getParticleTextureOffsetX(),
                            this.getMissilesInfo().get(i).y + this.getParticleTextureOffsetY());
                    textureParticleEffect.update(Gdx.graphics.getDeltaTime());
                    textureParticleEffect.draw(batch);
                }
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

    public Array<MissileInfo> getMissilesInfo() {
        return info;
    }

    public boolean isMissileInactive() {
        return (getMissilesInfo().size == 0);
    }
    public abstract Missile newInstance(int x, int y, int angle, float distanceMultiplier);
    public abstract Texture getMissileTexture();
    public abstract boolean useTexture();
    public abstract ParticleEffect getParticleEffect();
    public abstract int getMissilesPerShot();
    public abstract int getMissileStrength();
    public abstract boolean useParticlesForTexture();
    public abstract ParticleEffect getParticleEffectForTexture();
    public abstract int getParticleTextureOffsetX();
    public abstract int getParticleTextureOffsetY();

    private boolean checkBounds(float x, float y) {
        if(x > 0 && x < SilentSpaceConfig.GAME_WINDOW_WIDTH &&
                y > 0 && y < SilentSpaceConfig.GAME_WINDOW_HEIGHT) {
            return true;
        } else {
            return false;
        }
    }

    public void destroyMissile(Array<Integer> toRemove) {
        toRemove.reverse();
        for(int i=0;i<toRemove.size;i++) {
            if(toRemove.get(i) < this.getMissilesInfo().size) {
                this.getMissilesInfo().removeIndex(toRemove.get(i));
            }
        }
    }

    public class MissileInfo {

        public MissileInfo(int startPointX, int startPointY,int xoffset, int yoffset, int speed, int angle, int missileAngle, float distanceMultiplier) {
            if(distanceMultiplier > 1f) {
                startPointX -= (int)(10*distanceMultiplier * Math.sin(Math.toRadians(-angle)));
                startPointY += (int)(10*distanceMultiplier * Math.cos(Math.toRadians(-angle)));
            }
            this.x = startPointX + xoffset * (float)Math.cos(Math.toRadians(-angle)) -
                yoffset * (float)Math.sin(Math.toRadians(-angle));
            this.y = startPointY + xoffset * (float)Math.sin(Math.toRadians(-angle)) +
                    yoffset * (float)Math.cos(Math.toRadians(-angle));
            this.speed = speed;
            this.angle = angle + missileAngle;
        }
        protected int speed;
        protected int angle;
        protected float x;
        protected float y;
        protected boolean centered;
        protected float centerOffset;

        public float getX() {
            if(centered) return x + centerOffset;
            return x;
        }
        public float getY() {
            if(centered) return y+centerOffset;
            return y;
        }

        private float getDistance(float x1, float y1, float x2, float y2) {
            float dx = (x2-x1)*(x2-x1);
            float dy = (y2-y1)*(y2-y1);
            double result = Math.sqrt(dx + dy);
            return (float)result;
        }
    }
}
