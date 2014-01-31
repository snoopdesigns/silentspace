package org.snoopdesigns.silentspace.core.levels.objects.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.snoopdesigns.silentspace.core.levels.objects.LevelObject;
import org.snoopdesigns.silentspace.core.weapons.Weapon;

public abstract class EnemyShipLevelObject extends LevelObject{

    private Weapon weapon;
    private float eps;
    protected float x;
    protected float y;
    public Texture enemyTexture;

    public EnemyShipLevelObject() {
        super();
        eps = 0f;
        weapon = this.getShipWeapon();
        enemyTexture = this.getEnemyTexture();
    }

    public abstract Weapon getShipWeapon();
    public abstract void processMoving(SpriteBatch batch);
    public abstract boolean isShootingAtPlayer();
    public abstract Texture getEnemyTexture();

    @Override
    public float getX() {
        return x+enemyTexture.getWidth()/2;
    }

    @Override
    public float getY() {
        return y+enemyTexture.getHeight()/2;
    }

    @Override
    public float getCollisionEspilon() {
        return enemyTexture.getHeight()/2 - 5f;
    }

    @Override
    public FileHandle getAnimationFile() {
        return Gdx.files.internal("effects/animations/explosion.png");
    }

    @Override
    public void process(SpriteBatch batch) {
        eps += Gdx.graphics.getDeltaTime();
        if(eps > this.getWeaponDelay()) {
            if(isShootingAtPlayer()) {
                float dx = Math.abs(this.getPlayerShip().getX() - getX());
                float dy = Math.abs(this.getPlayerShip().getY() - getY());
                float dist = (float)Math.sqrt(dx*dx+dy*dy);
                int angle = (int)Math.toDegrees(Math.acos(dy/dist));
                if(this.getPlayerShip().getX() < this.getX()) {
                this.getPlayerShip().getMissilesProcessor().addActiveMissile(
                        this.getShipWeapon().fire((int)getX(), (int)getY(),180 + angle, 3f));
                } else {
                    this.getPlayerShip().getMissilesProcessor().addActiveMissile(
                            this.getShipWeapon().fire((int)getX(), (int)getY(),180 - angle, 3f));
                }
            } else {
                this.getPlayerShip().getMissilesProcessor().addActiveMissile(
                        this.getShipWeapon().fire((int) getX(), (int) getY(), 180, 1f));
            }
            eps = 0f;
        }
        this.processMoving(batch);
    }

    private float getDistance(float x1, float y1, float x2, float y2) {
        float dx = (x2-x1)*(x2-x1);
        float dy = (y2-y1)*(y2-y1);
        double result = Math.sqrt(dx + dy);
        return (float)result;
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


    @Override
    public boolean isPlayerShip() {
        return false;
    }
}
