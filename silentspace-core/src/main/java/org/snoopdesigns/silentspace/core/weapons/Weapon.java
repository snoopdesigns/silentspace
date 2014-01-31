package org.snoopdesigns.silentspace.core.weapons;

import com.badlogic.gdx.audio.Sound;
import org.snoopdesigns.silentspace.core.audio.AudioProcessor;
import org.snoopdesigns.silentspace.core.weapons.missiles.Missile;

public abstract class Weapon {

    public String getWeaponName() {
        return weaponName;
    }

    private String weaponName;

    public int getMissileCount() {
        return missileCount;
    }

    private int missileCount;

    public Weapon(String weaponName, int missileCount) {
        this.weaponName = weaponName;
        this.missileCount = missileCount;
    }

    public Weapon(String weaponName) {
        this.weaponName = weaponName;
        this.missileCount = 10;
    }

    public Missile fire(int x, int y, int angle, float distanceMultiplier) {
        if(this.missileCount < 1) {
            return null;
        } else {
            missileCount --;
            AudioProcessor.playEffect(getShotSound());
            return getMissileType().newInstance(x,y, angle, distanceMultiplier);
        }
    }

    public abstract Missile getMissileType();
    public abstract Sound getShotSound();
    public abstract float getWeaponShotsDelay();
}
