package org.snoopdesigns.silentspace.core.weapons.missiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.utils.Array;

public class DoubleBlasterMissile extends Missile{

    Array<MissileInfo> info = new Array<MissileInfo>();

    public DoubleBlasterMissile() {
        super();
    }

    public DoubleBlasterMissile(int x, int y) {
        super();
        MissileInfo mis = new MissileInfo(x+35,y+75,300,0);
        info.add(mis);
        mis = new MissileInfo(x+51,y+75,300,0);
        info.add(mis);
    }

    public DoubleBlasterMissile(int x, int y, int angle) {
        super();
        MissileInfo mis = new MissileInfo(x+35,y+75,300,angle);
        info.add(mis);
        mis = new MissileInfo(x+51,y+75,300,angle);
        info.add(mis);
    }

    public DoubleBlasterMissile(int x, int y, Array<MissileInfo> missileInfo) {
        super();
        for(int i=0;i<missileInfo.size;i++) {
            info.add(missileInfo.get(i));
        }
    }

    @Override
    public Missile newInstance(int x, int y) {
        return new DoubleBlasterMissile(x,y);
    }

    @Override
    public Missile newInstance(int x, int y, int angle) {
        return new DoubleBlasterMissile(x,y, angle);
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
        return 2;
    }

    @Override
    public int getMissileStrength() {
        return 10;
    }
}
