package org.snoopdesigns.silentspace.core.weapons.missiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

public class PlasmaMissile extends Missile{

    public PlasmaMissile() {
        super();
    }

    public PlasmaMissile(int x, int y, int angle, float distanceMultiplier) {
        super();
        int speed = 300;
        MissileInfo mis = new MissileInfo(x,y,-5,37,speed,angle,0,distanceMultiplier);
        info.add(mis);
        mis = new MissileInfo(x,y,5,37,speed,angle,0,distanceMultiplier);
        info.add(mis);
        mis = new MissileInfo(x,y,35,3,speed,angle,15,distanceMultiplier);
        info.add(mis);
        mis = new MissileInfo(x,y,-35,3,speed,angle,-15,distanceMultiplier);
        info.add(mis);
    }

    @Override
    public Missile newInstance(int x, int y, int angle, float distanceMultiplier) {
        return new PlasmaMissile(x,y,angle,distanceMultiplier);
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
        effect.load(Gdx.files.internal("weapons/plasma.p"), Gdx.files.internal("weapons"));
        return effect;
    }

    @Override
    public int getMissilesPerShot() {
        return 4;
    }

    @Override
    public int getMissileStrength() {
        return 20;
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
