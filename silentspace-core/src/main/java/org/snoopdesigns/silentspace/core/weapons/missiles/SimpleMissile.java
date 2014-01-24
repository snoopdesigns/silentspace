package org.snoopdesigns.silentspace.core.weapons.missiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class SimpleMissile extends Missile{

    Array<MissileInfo> info = new Array<MissileInfo>();

    public SimpleMissile() {
    }

    public SimpleMissile(int x, int y) {
        MissileInfo mis = new MissileInfo(x+43-getMissileTexture().getWidth()/2,
                y+80-getMissileTexture().getHeight()/2,300,0);
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
}
