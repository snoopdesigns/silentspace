package org.snoopdesigns.silentspace.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.snoopdesigns.silentspace.core.InputHandler;
import org.snoopdesigns.silentspace.core.PlayerShip;
import org.snoopdesigns.silentspace.core.bg.BackgroundRenderer;

public class GameScreen extends Screen{

    SpriteBatch batch;
    float elapsed;
    private BackgroundRenderer bgRenderer;
    private PlayerShip playerShip;

    public GameScreen() {
        batch = new SpriteBatch();
        bgRenderer = new BackgroundRenderer();
        playerShip = new PlayerShip();
    }

    @Override
    public void render() {
        elapsed += Gdx.graphics.getDeltaTime();
        batch.begin();
        bgRenderer.processBackground(batch);
        playerShip.processShip(batch);
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
    }
}
