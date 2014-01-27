package org.snoopdesigns.silentspace.core.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import org.snoopdesigns.silentspace.core.weapons.missiles.Missile;
import org.snoopdesigns.silentspace.core.weapons.missiles.SimpleMissile;

public class MissileGun extends Weapon{

    protected MissileGun(String weaponName, int missileCount) {
        super(weaponName, missileCount);
    }

    protected MissileGun(String weaponName) {
        super(weaponName);
    }

    @Override
    public Missile getMissileType() {
        return new SimpleMissile();
    }

    @Override
    public Sound getShotSound() {
        return Gdx.audio.newSound(Gdx.files.internal("audio/effects/blaster.wav"));
    }
}
