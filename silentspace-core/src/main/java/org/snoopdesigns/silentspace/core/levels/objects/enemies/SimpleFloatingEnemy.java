package org.snoopdesigns.silentspace.core.levels.objects.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.snoopdesigns.silentspace.core.config.SilentSpaceConfig;
import org.snoopdesigns.silentspace.core.weapons.DoubleBlasterGun;
import org.snoopdesigns.silentspace.core.weapons.Weapon;

public class SimpleFloatingEnemy extends EnemyShipLevelObject{

    private float dy = 0f;

    public SimpleFloatingEnemy() {
        super();
        this.y = SilentSpaceConfig.GAME_WINDOW_HEIGHT - 90;
        this.x = SilentSpaceConfig.GAME_WINDOW_WIDTH;
    }

    @Override
    public Weapon getShipWeapon() {
        return new DoubleBlasterGun("double blaster", 500);
    }

    @Override
    public void processMoving(SpriteBatch batch) {
        this.x -= 0.5f;
        dy +=  Gdx.graphics.getDeltaTime()/30;
        this.y -= dy;
        batch.draw(this.enemyTexture, x, y);
    }

    @Override
    public Texture getEnemyTexture() {
        return new Texture(Gdx.files.internal("objects/enemy1.png"));
    }

    @Override
    public boolean isShootingAtPlayer() {
        return true;
    }

    @Override
    public float getWeaponDelay() {
        return 2.5f;
    }

    @Override
    public int getInitialHealth() {
        return 20;
    }

    @Override
    public boolean isActive() {
        return y > -100 && x > -100;
    }

    @Override
    public void destroy() {
    }
}
