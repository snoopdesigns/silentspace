package org.snoopdesigns.silentspace.core.levels.objects.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import org.snoopdesigns.silentspace.core.config.SilentSpaceConfig;
import org.snoopdesigns.silentspace.core.weapons.DoubleBlasterGun;
import org.snoopdesigns.silentspace.core.weapons.Weapon;

public class SimpleEnemy extends EnemyShipLevelObject{

    public SimpleEnemy() {
        super();
        this.y = SilentSpaceConfig.GAME_WINDOW_HEIGHT;
    }

    @Override
    public Weapon getShipWeapon() {
        return new DoubleBlasterGun("double blaster", 500);
    }

    @Override
    public void processMoving() {
        this.x = 18 + 70*objectLine;
        this.y -= 1.0f;
    }

    @Override
    public boolean isShootingAtPlayer() {
        return false;
    }

    @Override
    public Texture getEnemyTexture() {
        return new Texture(Gdx.files.internal("objects/enemy2.png"));
    }

    @Override
    public boolean useParticlesForEngines() {
        return true;
    }

    @Override
    public ParticleEffect getEngineParticleEffect() {
        ParticleEffect effect = new ParticleEffect();
        effect.load(Gdx.files.internal("effects/engine.p"), Gdx.files.internal("effects"));
        return effect;
    }

    @Override
    public int getEngineOffsetX() {
        return this.enemyTexture.getWidth()/2;
    }

    @Override
    public int getEngineOffsetY() {
        return this.enemyTexture.getHeight()-5;
    }

    @Override
    public int getInitialHealth() {
        return 20;
    }

    @Override
    public float getWeaponDelay() {
        return 3.5f;
    }

    @Override
    public boolean isActive() {
        return y > -70;
    }

    @Override
    public void destroy() {

    }
}
