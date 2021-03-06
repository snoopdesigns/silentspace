package org.snoopdesigns.silentspace.core.levels.objects.dropdowns;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.snoopdesigns.silentspace.core.player.PlayerShip;

public class HealthItem extends DropDownLevelObject {

    public HealthItem() {
        super();
    }

    @Override
    public void destroy() {
    }

    @Override
    public void updatePlayer(PlayerShip ship) {
        ship.setHealth(ship.getHealth() + 25);
    }

    @Override
    public Texture gerDropDownItemTexture() {
        return new Texture(Gdx.files.internal("objects/dropdowns/hp_health_sprite.png"));
    }

    @Override
    public String getCatchMessage() {
        return "Health!";
    }
}
