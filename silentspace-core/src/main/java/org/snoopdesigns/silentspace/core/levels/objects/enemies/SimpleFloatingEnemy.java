package org.snoopdesigns.silentspace.core.levels.objects.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.snoopdesigns.silentspace.core.config.SilentSpaceConfig;
import org.snoopdesigns.silentspace.core.weapons.DoubleBlasterGun;
import org.snoopdesigns.silentspace.core.weapons.Weapon;

public class SimpleFloatingEnemy extends EnemyShipLevelObject{

    private Texture texture;

    public SimpleFloatingEnemy() {
        texture = new Texture(Gdx.files.internal("objects/enemy.png"));
        this.y = SilentSpaceConfig.GAME_WINDOW_HEIGHT - 90;
        this.x = SilentSpaceConfig.GAME_WINDOW_WIDTH;
    }

    @Override
    public Weapon getShipWeapon() {
        return new DoubleBlasterGun("Dbl", 100);
    }

    @Override
    public void processMoving(SpriteBatch batch) {
        this.x -= 0.5f;
        this.y -= 0.25;
        batch.draw(texture, x, y);
    }

    @Override
    public boolean isShootingAtPlayer() {
        return true;
    }

    @Override
    public float getWeaponDelay() {
        return 2f;
    }

    @Override
    public int getInitialHealth() {
        return 20;
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
        return y > -100 && x > -100;
    }

    @Override
    public void destroy() {
    }
}
