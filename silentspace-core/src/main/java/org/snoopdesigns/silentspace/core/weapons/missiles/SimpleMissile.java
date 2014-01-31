package org.snoopdesigns.silentspace.core.weapons.missiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

public class SimpleMissile extends Missile{

    public SimpleMissile() {
        super();
    }

    public SimpleMissile(int x, int y, int angle, float distanceMultiplier) {
        super();
        MissileInfo mis = new MissileInfo(x,y,0,0,200,angle,0,distanceMultiplier);
        mis.centered = true;
        mis.centerOffset = getMissileTexture().getHeight()/2;
        info.add(mis);
    }

    @Override
    public Missile newInstance(int x, int y, int angle, float distanceMultiplier) {
        return new SimpleMissile(x,y, angle, distanceMultiplier);
    }

    @Override
    public Texture getMissileTexture() {
        return new Texture(Gdx.files.internal("objects/rocket.png"));
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

    @Override
    public int getMissileStrength() {
        return 5;
    }
}
