package org.snoopdesigns.silentspace.core.weapons.missiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.utils.Array;

public class DoubleBlasterMissile extends Missile{

    public DoubleBlasterMissile() {
        super();
    }

    public DoubleBlasterMissile(int x, int y, int angle, float distanceMultiplier) {
        super();
        int speed = 300;
        MissileInfo mis = new MissileInfo(x,y,-15,37,speed,angle,0,distanceMultiplier);
        info.add(mis);
        mis = new MissileInfo(x,y,15,37,speed,angle,0,distanceMultiplier);
        info.add(mis);
    }

    public DoubleBlasterMissile(int x, int y, Array<MissileInfo> missileInfo) {
        super();
        for(int i=0;i<missileInfo.size;i++) {
            info.add(missileInfo.get(i));
        }
    }

    @Override
    public Missile newInstance(int x, int y, int angle, float distanceMultiplier) {
        return new DoubleBlasterMissile(x,y, angle, distanceMultiplier);
    }

    @Override
    public Texture getMissileTexture() {
        return null;
    }

    @Override
    public boolean useTexture() {
        return false;
    }

    @Override
    public ParticleEffect getParticleEffect() {
        ParticleEffect effect = new ParticleEffect();
        effect.load(Gdx.files.internal("weapons/blaster.p"), Gdx.files.internal("weapons"));
        return effect;
    }

    @Override
    public int getMissilesPerShot() {
        return 2;
    }

    @Override
    public int getMissileStrength() {
        return 10;
    }

    @Override
    public boolean useParticlesForTexture() {
        return false;
    }

    @Override
    public ParticleEffect getParticleEffectForTexture() {
        return null;
    }

    @Override
    public int getParticleTextureOffsetX() {
        return 0;
    }

    @Override
    public int getParticleTextureOffsetY() {
        return 0;
    }
}
