package org.snoopdesigns.silentspace.core.weapons;

import com.badlogic.gdx.utils.Array;
import org.snoopdesigns.silentspace.core.player.PlayerHUD;
import org.snoopdesigns.silentspace.core.weapons.missiles.Missile;

public class PlayerWeaponsProcessor {

    private Array<Weapon> playerWeapons;
    private int playerActiveWeapon = -1;
    private PlayerHUD hudInstance;

    public PlayerWeaponsProcessor(PlayerHUD hud) {
        this.hudInstance = hud;
        playerWeapons = new Array<Weapon>();
        playerWeapons.add(new DoubleBlasterGun("dblblaster", 100));
        this.setPlayerActiveWeapon(0);
    }

    public void addPlayerWeapon(Weapon weapon) {
        playerWeapons.add(weapon);
    }

    public void setPlayerActiveWeapon(int i) {
        playerActiveWeapon = i;
        hudInstance.setPlayerWeaponName(playerWeapons.get(playerActiveWeapon).getWeaponName());
        hudInstance.setPlayerAmmoCount(String.valueOf(playerWeapons.get(playerActiveWeapon).getMissileCount()));
    }

    public Missile firePlayerActiveWeapon(int x, int y) {
        if(playerActiveWeapon != -1) {
            if(playerWeapons.get(playerActiveWeapon).getMissileCount() == 0) {
                System.out.println("Out of ammo!");
            }
            Missile mis = playerWeapons.get(playerActiveWeapon).fire(x,y);
            hudInstance.setPlayerAmmoCount(String.valueOf(playerWeapons.get(playerActiveWeapon).getMissileCount()));
            return mis;
        } else {
            return null;
        }
    }
}
