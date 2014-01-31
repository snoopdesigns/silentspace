package org.snoopdesigns.silentspace.core.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import org.snoopdesigns.silentspace.core.weapons.missiles.Missile;
import org.snoopdesigns.silentspace.core.weapons.missiles.SimpleBlasterMissile;

public class SimpleBlasterGun extends Weapon{

    protected SimpleBlasterGun(String weaponName, int missileCount) {
        super(weaponName, missileCount);
    }

    protected SimpleBlasterGun(String weaponName) {
        super(weaponName);
    }

    @Override
    public Missile getMissileType() {
        return new SimpleBlasterMissile();
    }

    @Override
    public Sound getShotSound() {
        return Gdx.audio.newSound(Gdx.files.internal("audio/effects/laser.mp3"));
    }

    @Override
    public float getWeaponShotsDelay() {
        return 0.6f;
    }
}
