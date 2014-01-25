package org.snoopdesigns.silentspace.core.weapons;

import org.snoopdesigns.silentspace.core.weapons.missiles.BlasterMissile;
import org.snoopdesigns.silentspace.core.weapons.missiles.Missile;
import org.snoopdesigns.silentspace.core.weapons.missiles.SimpleMissile;

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
}
