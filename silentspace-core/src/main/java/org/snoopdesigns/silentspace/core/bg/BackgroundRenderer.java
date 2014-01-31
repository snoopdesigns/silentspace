package org.snoopdesigns.silentspace.core.bg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.snoopdesigns.silentspace.core.config.SilentSpaceConfig;

public class BackgroundRenderer {

    private Texture bgTexture;
    private ParticleEffect starsEffect;
    public BackgroundRenderer() {
        bgTexture = new Texture(Gdx.files.internal("bg1.png"));
        starsEffect = new ParticleEffect();
        starsEffect.load(Gdx.files.internal("effects/stars.p"), Gdx.files.internal("effects"));
    }

    public void processBackground(SpriteBatch batch) {
        batch.draw(bgTexture, 0, 0);
    }

    public void processStars(SpriteBatch batch) {
        starsEffect.setPosition(SilentSpaceConfig.GAME_WINDOW_WIDTH/2, SilentSpaceConfig.GAME_WINDOW_HEIGHT/2);
        starsEffect.update(Gdx.graphics.getDeltaTime());
        starsEffect.draw(batch);
    }
}
