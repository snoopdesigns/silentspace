package org.snoopdesigns.silentspace.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.snoopdesigns.silentspace.core.config.SilentSpaceConfig;

public class PlayerShip {

    public float x;
    public float y;
    public float health;
    private Texture shipTexture;
    private boolean movingRight;
    private boolean movingLeft;
    private ParticleEffect engineLeft;
    private ParticleEffect engineRight;

    public PlayerShip() {
        shipTexture = new Texture(Gdx.files.internal("ship.png"));
        this.x = SilentSpaceConfig.GAME_WINDOW_WIDTH/2;
        this.y = 50;
        this.movingLeft = false;
        this.movingRight = false;
        engineLeft = new ParticleEffect();
        engineLeft.load(Gdx.files.internal("effects/rocket.p"), Gdx.files.internal("effects"));
        engineRight = new ParticleEffect();
        engineRight.load(Gdx.files.internal("effects/rocket.p"), Gdx.files.internal("effects"));
    }

    public void processShip(SpriteBatch batch) {
        batch.draw(shipTexture, this.x, this.y);
        if(this.movingLeft) this.x -= (SilentSpaceConfig.SHIP_MOVE_SPEED * Gdx.graphics.getDeltaTime());
        if(this.movingRight) this.x += (SilentSpaceConfig.SHIP_MOVE_SPEED * Gdx.graphics.getDeltaTime());
        engineLeft.setPosition(x+37,y+3);
        engineLeft.update(Gdx.graphics.getDeltaTime());
        engineLeft.draw(batch);
        engineRight.setPosition(x+48,y+3);
        engineRight.update(Gdx.graphics.getDeltaTime());
        engineRight.draw(batch);
    }

    public void moveRight() {
        if(movingRight) movingRight = false;
        else movingRight = true;
    }

    public void moveLeft() {
        if(movingLeft) movingLeft = false;
        else movingLeft = true;
    }
}
