package org.snoopdesigns.silentspace.core.levels.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.snoopdesigns.silentspace.core.player.PlayerShip;
import org.snoopdesigns.silentspace.core.weapons.DoubleBlasterGun;

public class HealthItem extends DropDownLevelObject{

    private Texture texture;

    public HealthItem() {
        texture = new Texture(Gdx.files.internal("rocket.png"));
    }

    @Override
    public void process(SpriteBatch batch) {
        this.y -=1;
        batch.draw(texture, x, y);
    }

    @Override
    public float getX() {
        return this.x + texture.getWidth()/2;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public float getY() {
        return this.y + texture.getHeight()/2;
    }

    @Override
    public void destroy() {
    }

    @Override
    public void updatePlayer(PlayerShip ship) {
        ship.addPlayerWeapon(new DoubleBlasterGun("Double", 50));
        ship.setHealth(ship.getHealth() + 25);
    }
}
