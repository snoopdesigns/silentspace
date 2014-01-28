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

    protected Weapon(String weaponName, int missileCount) {
        this.weaponName = weaponName;
        this.missileCount = missileCount;
    }

    protected Weapon(String weaponName) {
        this.weaponName = weaponName;
        this.missileCount = 10;
    }

    public Missile fire(int x, int y) {
        if(this.missileCount < 1) {
            return null;
        } else {
            missileCount --;
            AudioProcessor.playEffect(getShotSound());
            return getMissileType().newInstance(x,y);
        }
    }
    public abstract Missile getMissileType();
    public abstract Sound getShotSound();
    public abstract float getWeaponShotsDelay();
}
