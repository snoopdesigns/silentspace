package org.snoopdesigns.silentspace.core.weapons;

import org.snoopdesigns.silentspace.core.weapons.missiles.Missile;

public abstract class Weapon {

    private String weaponName;
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
        return getMissileType().newInstance(x,y);
    }
    public abstract Missile getMissileType();
}
