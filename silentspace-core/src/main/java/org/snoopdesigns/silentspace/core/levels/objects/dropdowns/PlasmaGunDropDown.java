package org.snoopdesigns.silentspace.core.levels.objects.dropdowns;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.snoopdesigns.silentspace.core.weapons.PlasmaGun;
import org.snoopdesigns.silentspace.core.weapons.Weapon;

public class PlasmaGunDropDown extends WeaponDropDown{
    @Override
    public Weapon getDropDownWeapon() {
        return new PlasmaGun("Plasma", 100);
    }

    @Override
    public Texture getWeaponTexture() {
        return new Texture(Gdx.files.internal("objects/dropdowns/plasma_gun.png"));
    }

    @Override
    public String getCatchMessage() {
        return "Plasma Gun!";
    }
}
