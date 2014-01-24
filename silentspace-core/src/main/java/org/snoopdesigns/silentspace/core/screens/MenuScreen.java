package org.snoopdesigns.silentspace.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.snoopdesigns.silentspace.core.InputHandler;

public class MenuScreen extends Screen{

    private SpriteBatch batch;
    private Texture bgTexture;

    public MenuScreen() {
        batch = new SpriteBatch();
        bgTexture = new Texture(Gdx.files.internal("bg.jpg"));
    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(bgTexture, 0, 0);
        batch.end();
    }

    @Override
    public void remove() {
    }

    @Override
    public void tick(InputHandler input) {
        if(input.isKeyPressed(InputHandler.ENTER)) {
            setScreen(new GameScreen());
        }
    }
}
