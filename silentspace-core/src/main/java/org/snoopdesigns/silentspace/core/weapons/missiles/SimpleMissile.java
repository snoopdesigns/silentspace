package org.snoopdesigns.silentspace.core.weapons.missiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.utils.Array;

public class SimpleMissile extends Missile{

    Array<MissileInfo> info = new Array<MissileInfo>();

    public SimpleMissile() {
        super();
        MissileInfo mis = new MissileInfo(0, 0, 300 ,0);
        info.add(mis);
    }

    public SimpleMissile(int x, int y) {
        MissileInfo mis = new MissileInfo(x+43-getMissileTexture().getWidth()/2,
                y+80-getMissileTexture().getHeight()/2,300,0);
        mis.centered = true;
        mis.centerOffset = getMissileTexture().getHeight()/2;
        info.add(mis);
    }

    @Override
    public Missile newInstance(int x, int y) {
        return new SimpleMissile(x,y);
    }

    @Override
    public Array<MissileInfo> getMissilesInfo() {
        return info;
    }

    @Override
    public Texture getMissileTexture() {
        return new Texture(Gdx.files.internal("rocket.png"));
    }

    @Override
    public boolean useTexture() {
        return true;
    }

    @Override
    public ParticleEffect getParticleEffect() {
        return null;
    }

    @Override
    public int getMissilesPerShot() {
        return 1;
    }
}
