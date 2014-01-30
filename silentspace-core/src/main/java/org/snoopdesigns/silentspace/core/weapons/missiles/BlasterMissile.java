package org.snoopdesigns.silentspace.core.weapons.missiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.utils.Array;

public class BlasterMissile extends Missile{

    Array<MissileInfo> info = new Array<MissileInfo>();

    public BlasterMissile() {
        super();
    }

    public BlasterMissile(int x, int y, int angle, float distanceMultiplier) {
        super();
        MissileInfo mis = new MissileInfo(x,y,0,37,300,angle,0,distanceMultiplier);
        info.add(mis);
        mis = new MissileInfo(x,y,37,3,300,angle,30,distanceMultiplier);
        info.add(mis);
        mis = new MissileInfo(x,y,-37,3,300,angle,-30,distanceMultiplier);
        info.add(mis);
    }

    public BlasterMissile(int x, int y, Array<MissileInfo> missileInfo) {
        super();
        for(int i=0;i<missileInfo.size;i++) {
            info.add(missileInfo.get(i));
        }
    }

    @Override
    public Missile newInstance(int x, int y, int angle, float distanceMultiplier) {
        return new BlasterMissile(x,y, angle, distanceMultiplier);
    }

    @Override
    public Array<MissileInfo> getMissilesInfo() {
        return info;
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
        return 3;
    }

    @Override
    public int getMissileStrength() {
        return 10;
    }
}
