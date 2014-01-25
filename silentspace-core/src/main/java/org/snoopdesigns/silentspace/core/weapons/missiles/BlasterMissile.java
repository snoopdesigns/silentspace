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

    public BlasterMissile(int x, int y) {
        super();
        MissileInfo mis = new MissileInfo(x+43,y+80,300,0);
        info.add(mis);
        mis = new MissileInfo(x+6,y+48,300,0);
        info.add(mis);
        mis = new MissileInfo(x+79,y+48,300,0);
        info.add(mis);
    }

    @Override
    public Missile newInstance(int x, int y) {
        return new BlasterMissile(x,y);
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
}
