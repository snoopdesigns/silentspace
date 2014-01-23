package org.snoopdesigns.silentspace.core.bg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.snoopdesigns.silentspace.core.config.SilentSpaceConfig;

public class BackgroundRenderer {

    private Texture bgTexture;
    public BackgroundRenderer() {
        bgTexture = new Texture(Gdx.files.internal("bg.jpg"));
    }

    public void processBackground(SpriteBatch batch) {
        float eps = Gdx.graphics.getDeltaTime();
        batch.draw(bgTexture, 0, -(SilentSpaceConfig.BG_SCROLL_SPEED*eps));
    }
}
