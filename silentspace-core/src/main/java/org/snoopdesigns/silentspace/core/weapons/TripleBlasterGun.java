package org.snoopdesigns.silentspace.core.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import org.snoopdesigns.silentspace.core.weapons.missiles.TripleBlasterMissile;
import org.snoopdesigns.silentspace.core.weapons.missiles.Missile;

public class TripleBlasterGun extends Weapon{

    public TripleBlasterGun(String weaponName, int missileCount) {
        super(weaponName, missileCount);
    }

    public TripleBlasterGun(String weaponName) {
        super(weaponName);
    }

    @Override
    public Missile getMissileType() {
        return new TripleBlasterMissile();
    }

    @Override
    public Sound getShotSound() {
        return Gdx.audio.newSound(Gdx.files.internal("audio/effects/blaster.wav"));
    }

    @Override
    public float getWeaponShotsDelay() {
        return 0.6f;
    }
}
