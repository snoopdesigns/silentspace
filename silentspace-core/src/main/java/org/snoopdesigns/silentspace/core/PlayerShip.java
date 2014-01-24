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
    private boolean movingUp;
    private boolean movingDown;
    private ParticleEffect engineLeft;
    private ParticleEffect engineRight;

    public PlayerShip() {
        shipTexture = new Texture(Gdx.files.internal("ship.png"));
        this.x = SilentSpaceConfig.GAME_WINDOW_WIDTH/2;
        this.y = 50;
        this.movingLeft = false;
        this.movingRight = false;
        this.movingUp = false;
        this.movingDown = false;
        engineLeft = new ParticleEffect();
        engineLeft.load(Gdx.files.internal("effects/rocket.p"), Gdx.files.internal("effects"));
        engineRight = new ParticleEffect();
        engineRight.load(Gdx.files.internal("effects/rocket.p"), Gdx.files.internal("effects"));
    }

    public void processShip(SpriteBatch batch) {
        batch.draw(shipTexture, this.x, this.y);
        if(this.movingLeft && checkBounds(x-SilentSpaceConfig.SHIP_MOVE_SPEED * Gdx.graphics.getDeltaTime(), y)) {
            this.x -= (SilentSpaceConfig.SHIP_MOVE_SPEED * Gdx.graphics.getDeltaTime());
        }
        if(this.movingRight && checkBounds(x+SilentSpaceConfig.SHIP_MOVE_SPEED * Gdx.graphics.getDeltaTime(),y)) {
            this.x += (SilentSpaceConfig.SHIP_MOVE_SPEED * Gdx.graphics.getDeltaTime());
        }
        if(this.movingUp && checkBounds(x,y+SilentSpaceConfig.SHIP_MOVE_SPEED * 0.5f * Gdx.graphics.getDeltaTime())) {
            this.y += (SilentSpaceConfig.SHIP_MOVE_SPEED * 0.5 * Gdx.graphics.getDeltaTime());
        }
        if(this.movingDown && checkBounds(x,y-SilentSpaceConfig.SHIP_MOVE_SPEED * 1.5f * Gdx.graphics.getDeltaTime())) {
            this.y -= (SilentSpaceConfig.SHIP_MOVE_SPEED * 1.5 * Gdx.graphics.getDeltaTime());
        }
        engineLeft.setPosition(x+37,y+3);
        engineLeft.update(Gdx.graphics.getDeltaTime());
        engineLeft.draw(batch);
        engineRight.setPosition(x+48,y+3);
        engineRight.update(Gdx.graphics.getDeltaTime());
        engineRight.draw(batch);
    }

    public boolean checkBounds(float x, float y) {
        if(x > 0 && x < SilentSpaceConfig.GAME_WINDOW_WIDTH - shipTexture.getWidth() &&
                y > 0 && y < SilentSpaceConfig.GAME_WINDOW_HEIGHT - shipTexture.getHeight()) {
            return true;
        } else {
            return false;
        }
    }

    public void moveRight() {
        if(movingRight) movingRight = false;
        else movingRight = true;
    }

    public void moveLeft() {
        if(movingLeft) movingLeft = false;
        else movingLeft = true;
    }

    public void moveUp() {
        if(movingUp) movingUp = false;
        else movingUp = true;
    }

    public void moveDown() {
        if(movingDown) movingDown = false;
        else movingDown = true;
    }
}
