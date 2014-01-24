package org.snoopdesigns.silentspace.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.snoopdesigns.silentspace.core.InputHandler;
import org.snoopdesigns.silentspace.core.MissilesProcessor;
import org.snoopdesigns.silentspace.core.PlayerShip;
import org.snoopdesigns.silentspace.core.bg.BackgroundRenderer;

public class GameScreen extends Screen{

    SpriteBatch batch;
    float elapsed;
    private BackgroundRenderer bgRenderer;
    private PlayerShip playerShip;
    private MissilesProcessor missilesProcessor;

    public GameScreen() {
        batch = new SpriteBatch();
        bgRenderer = new BackgroundRenderer();
        playerShip = new PlayerShip();
        missilesProcessor = new MissilesProcessor();
    }

    @Override
    public void render() {
        elapsed += Gdx.graphics.getDeltaTime();
        batch.begin();
        bgRenderer.processBackground(batch);
        playerShip.processShip(batch);
        missilesProcessor.process(batch);
        batch.end();
        bgRenderer.processStars();
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
            System.out.println("Shooting..");
            missilesProcessor.addActiveMissile(playerShip.fireActiveWeapon());
        }
    }
}
