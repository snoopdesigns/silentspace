package org.snoopdesigns.silentspace.core.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import org.snoopdesigns.silentspace.core.audio.AudioProcessor;
import org.snoopdesigns.silentspace.core.player.PlayerHUD;
import org.snoopdesigns.silentspace.core.weapons.missiles.Missile;

public class PlayerWeaponsProcessor {

    private Array<Weapon> playerWeapons;
    private int playerActiveWeapon = -1;
    private PlayerHUD hudInstance;

    public PlayerWeaponsProcessor(PlayerHUD hud) {
        this.hudInstance = hud;
        playerWeapons = new Array<Weapon>();
        playerWeapons.add(new DoubleBlasterGun("Double", 100));
        this.setPlayerActiveWeapon(0);

    }

    public void addPlayerWeapon(Weapon weapon) {
        playerWeapons.add(weapon);
        playerActiveWeapon = playerWeapons.size - 1;
        hudInstance.setPlayerWeaponName(playerWeapons.get(playerActiveWeapon).getWeaponName());
        hudInstance.setPlayerAmmoCount(String.valueOf(playerWeapons.get(playerActiveWeapon).getMissileCount()));
    }

    public void setPlayerActiveWeapon(int i) {
        playerActiveWeapon = i;
        hudInstance.setPlayerWeaponName(playerWeapons.get(playerActiveWeapon).getWeaponName());
        hudInstance.setPlayerAmmoCount(String.valueOf(playerWeapons.get(playerActiveWeapon).getMissileCount()));
    }

    public int getPlayerActiveWeapon() {
        return playerActiveWeapon;
    }

    public Weapon getPlayerActiveWeaponById(int id) {
        return playerWeapons.get(id);
    }

    public Missile firePlayerActiveWeapon(int x, int y) {
        if(playerActiveWeapon != -1) {
            if(playerWeapons.get(playerActiveWeapon).getMissileCount() == 0) {
                System.out.println("Out of ammo!");
                if(this.getPlayerActiveWeapon() != 0) setPlayerActiveWeapon(getPlayerActiveWeapon()-1);
                AudioProcessor.playEffect(Gdx.audio.newSound(Gdx.files.internal("audio/effects/reload.wav")));
            }
            Missile mis = playerWeapons.get(playerActiveWeapon).fire(x,y,0,1f);
            hudInstance.setPlayerAmmoCount(String.valueOf(playerWeapons.get(playerActiveWeapon).getMissileCount()));
            return mis;
        } else {
            return null;
        }
    }
}
