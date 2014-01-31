package org.snoopdesigns.silentspace.core.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import org.snoopdesigns.silentspace.core.weapons.missiles.Missile;
import org.snoopdesigns.silentspace.core.weapons.missiles.PlasmaMissile;

public class PlasmaGun extends Weapon{

    public PlasmaGun(String weaponName, int missileCount) {
        super(weaponName, missileCount);
    }

    public PlasmaGun(String weaponName) {
        super(weaponName);
    }

    @Override
    public Missile getMissileType() {
        return new PlasmaMissile();
    }

    @Override
    public Sound getShotSound() {
        return Gdx.audio.newSound(Gdx.files.internal("audio/effects/blaster.wav"));
    }

    @Override
    public float getWeaponShotsDelay() {
        return 0.3f;
    }
}
