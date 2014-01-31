package org.snoopdesigns.silentspace.core.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import org.snoopdesigns.silentspace.core.weapons.missiles.BlasterMissile;
import org.snoopdesigns.silentspace.core.weapons.missiles.Missile;

public class BlasterGun extends Weapon{

    public BlasterGun(String weaponName, int missileCount) {
        super(weaponName, missileCount);
    }

    public BlasterGun(String weaponName) {
        super(weaponName);
    }

    @Override
    public Missile getMissileType() {
        return new BlasterMissile();
    }

    @Override
    public Sound getShotSound() {
        return Gdx.audio.newSound(Gdx.files.internal("audio/effects/blaster.wav"));
    }

    @Override
    public float getWeaponShotsDelay() {
        return 0.2f;
    }
}
