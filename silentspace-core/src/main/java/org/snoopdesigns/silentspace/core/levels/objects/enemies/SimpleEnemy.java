package org.snoopdesigns.silentspace.core.levels.objects.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.snoopdesigns.silentspace.core.config.SilentSpaceConfig;
import org.snoopdesigns.silentspace.core.weapons.DoubleBlasterGun;
import org.snoopdesigns.silentspace.core.weapons.Weapon;

public class SimpleEnemy extends EnemyShipLevelObject{

    private Texture texture;

    public SimpleEnemy() {
        texture = new Texture(Gdx.files.internal("objects/enemy2.png"));
        this.y = SilentSpaceConfig.GAME_WINDOW_HEIGHT;
    }

    @Override
    public Weapon getShipWeapon() {
        return new DoubleBlasterGun("double blaster", 500);
    }

    @Override
    public void processMoving(SpriteBatch batch) {
        this.x = 18 + 70*objectLine;
        this.y -= 1.0f;
        batch.draw(texture, x, y);
    }

    @Override
    public boolean isShootingAtPlayer() {
        return false;
    }

    @Override
    public int getInitialHealth() {
        return 20;
    }

    @Override
    public float getWeaponDelay() {
        return 1.5f;
    }

    @Override
    public float getX() {
        return x+texture.getWidth()/2;
    }

    @Override
    public float getY() {
        return y+texture.getHeight()/2;
    }

    @Override
    public boolean isActive() {
        return y > -70;
    }

    @Override
    public void destroy() {

    }
}
