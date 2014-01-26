package org.snoopdesigns.silentspace.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.snoopdesigns.silentspace.core.InputHandler;
import org.snoopdesigns.silentspace.core.weapons.MissilesProcessor;
import org.snoopdesigns.silentspace.core.levels.objects.ObjectProcessor;
import org.snoopdesigns.silentspace.core.player.PlayerShip;
import org.snoopdesigns.silentspace.core.bg.BackgroundRenderer;
import org.snoopdesigns.silentspace.core.levels.Level;
import org.snoopdesigns.silentspace.core.levels.SimpleRocksLevel;
import org.snoopdesigns.silentspace.core.weapons.missiles.Missile;

public class GameScreen extends Screen{

    SpriteBatch batch;
    float elapsed;
    private BackgroundRenderer bgRenderer;
    private PlayerShip playerShip;
    private MissilesProcessor missilesProcessor;
    private Level level;
    private ObjectProcessor objProcessor;

    public GameScreen() {
        batch = new SpriteBatch();
        bgRenderer = new BackgroundRenderer();
        playerShip = new PlayerShip();
        missilesProcessor = new MissilesProcessor();
        level = new SimpleRocksLevel();
        objProcessor = new ObjectProcessor();
    }

    @Override
    public void render() {
        elapsed += Gdx.graphics.getDeltaTime();
        batch.begin();
        bgRenderer.processBackground(batch);
        missilesProcessor.process(batch);
        level.process(objProcessor);
        objProcessor.process(batch);
        batch.end();
        bgRenderer.processStars();
        batch.begin();
        playerShip.processShip(batch);
        batch.end();
    }

    @Override
    public void remove() {
        batch.dispose();
    }

    @Override
    public void tick(InputHandler input) {
        if(input.isKeyPressed(InputHandler.LEFT) || input.isKeyReleased(InputHandler.LEFT)) {
            playerShip.moveLeft();
        }
        if(input.isKeyPressed(InputHandler.RIGHT) || input.isKeyReleased(InputHandler.RIGHT)) {
            playerShip.moveRight();
        }
        if(input.isKeyPressed(InputHandler.UP) || input.isKeyReleased(InputHandler.UP)) {
            playerShip.moveUp();
        }
        if(input.isKeyPressed(InputHandler.DOWN) || input.isKeyReleased(InputHandler.DOWN)) {
            playerShip.moveDown();
        }
        if(input.isKeyPressed(InputHandler.SPACE)) {
            Missile mis = playerShip.fireActiveWeapon();
            if(mis != null) {
                missilesProcessor.addActiveMissile(mis);
            }
        }
    }
}
