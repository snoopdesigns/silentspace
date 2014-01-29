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
    public abstract Missile newInstance(int x, int y, int angle, float distanceMultiplier);
    public abstract Array<MissileInfo> getMissilesInfo();
    public abstract Texture getMissileTexture();
    public abstract boolean useTexture();
    public abstract ParticleEffect getParticleEffect();
    public abstract int getMissilesPerShot();
    public abstract int getMissileStrength();

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
            float dist = this.getDistance(0,0,xoffset, yoffset);
            float sin = xoffset/dist;
            float cos = yoffset/dist;
            float angleNew = 0;
            angleNew = (float)Math.toDegrees(Math.acos(cos)) + angle;
            this.x = startPointX + (xoffset * (float)Math.cos(Math.toRadians(angle)) -
                yoffset * (float)Math.sin(Math.toRadians(angle)));//(int)(Math.sin(Math.toRadians(angleNew)) * (xoffset * distanceMultiplier));
            this.y = startPointY + xoffset * (float)Math.sin(Math.toRadians(angle)) +
                    yoffset * (float)Math.cos(Math.toRadians(angle));//(int)(Math.cos(Math.toRadians(angleNew)) * (yoffset * distanceMultiplier));
            this.speed = speed;
            System.out.println("Distance: " + dist + ", " + this.getDistance(0,0,x - startPointX,y - startPointY));
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
