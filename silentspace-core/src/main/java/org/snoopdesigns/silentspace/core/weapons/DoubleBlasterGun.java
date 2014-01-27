package org.snoopdesigns.silentspace.core.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import org.snoopdesigns.silentspace.core.weapons.missiles.DoubleBlasterMissile;
import org.snoopdesigns.silentspace.core.weapons.missiles.Missile;

public class DoubleBlasterGun extends Weapon{

    public DoubleBlasterGun(String weaponName, int missileCount) {
        super(weaponName, missileCount);
    }

    public DoubleBlasterGun(String weaponName) {
        super(weaponName);
    }

    @Override
    public Missile getMissileType() {
        return new DoubleBlasterMissile();
    }

    @Override
    public Sound getShotSound() {
        return Gdx.audio.newSound(Gdx.files.internal("audio/effects/blaster.wav"));
    }
}
