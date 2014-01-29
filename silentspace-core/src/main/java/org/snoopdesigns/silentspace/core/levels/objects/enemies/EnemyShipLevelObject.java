package org.snoopdesigns.silentspace.core.levels.objects.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.snoopdesigns.silentspace.core.levels.objects.LevelObject;
import org.snoopdesigns.silentspace.core.weapons.Weapon;

public abstract class EnemyShipLevelObject extends LevelObject{

    private Weapon weapon;
    private float eps;
    protected float x;
    protected float y;

    public EnemyShipLevelObject() {
        super();
        eps = 0f;
        weapon = this.getShipWeapon();
    }

    public abstract Weapon getShipWeapon();
    public abstract void processMoving(SpriteBatch batch);
    public abstract boolean isShootingAtPlayer();
    @Override
    public FileHandle getAnimationFile() {
        return Gdx.files.internal("effects/animations/explosion.png");
    }

    @Override
    public void process(SpriteBatch batch) {
        eps += Gdx.graphics.getDeltaTime();
        if(eps > this.getWeaponDelay()) {
            if(isShootingAtPlayer()) {
                this.getPlayerShip().getMissilesProcessor().addActiveMissile(
                        this.getShipWeapon().fire((int)getX(), (int)getY(), 180));
            } else {
                this.getPlayerShip().getMissilesProcessor().addActiveMissile(
                        this.getShipWeapon().fire((int)x, (int)y, 180));
                System.out.println("Shooting at player");
            }
            eps = 0f;
        }
        this.processMoving(batch);
    }

    public abstract float getWeaponDelay();

    @Override
    public int getAnimationRows() {
        return 4;
    }

    @Override
    public int getAnimationCols() {
        return 5;
    }

    @Override
    public boolean isExplodable() {
        return true;
    }

    @Override
    public boolean isDestroyble() {
        return true;
    }

    @Override
    public boolean isCatchable() {
        return false;
    }
}
