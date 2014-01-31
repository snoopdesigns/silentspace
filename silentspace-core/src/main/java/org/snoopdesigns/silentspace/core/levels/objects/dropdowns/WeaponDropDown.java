package org.snoopdesigns.silentspace.core.levels.objects.dropdowns;

import com.badlogic.gdx.graphics.Texture;
import org.snoopdesigns.silentspace.core.player.PlayerShip;
import org.snoopdesigns.silentspace.core.weapons.Weapon;

public abstract class WeaponDropDown extends DropDownLevelObject{

    public abstract Weapon getDropDownWeapon();
    public abstract Texture getWeaponTexture();

    @Override
    public void updatePlayer(PlayerShip ship) {
        ship.addPlayerWeapon(this.getDropDownWeapon());
    }

    @Override
    public Texture gerDropDownItemTexture() {
        return this.getWeaponTexture();
    }

    @Override
    public void destroy() {
    }
}
