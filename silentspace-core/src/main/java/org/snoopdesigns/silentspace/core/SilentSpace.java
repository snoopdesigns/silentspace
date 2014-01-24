package org.snoopdesigns.silentspace.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.snoopdesigns.silentspace.core.bg.BackgroundRenderer;

public class SilentSpace implements ApplicationListener {
	SpriteBatch batch;
	float elapsed;
    private BackgroundRenderer bgRenderer;
    private PlayerShip playerShip;
    private final InputHandler input = new InputHandler(this);
    private ParticleEffect p;

	@Override
	public void create () {
		batch = new SpriteBatch();
        bgRenderer = new BackgroundRenderer();
        playerShip = new PlayerShip();
        Gdx.input.setInputProcessor(input);
        p = new ParticleEffect();
        p.load(Gdx.files.internal("effects/rocket.p"), Gdx.files.internal("effects"));
	}

	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void render () {
		elapsed += Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();
        bgRenderer.processBackground(batch);
		playerShip.processShip(batch);
        p.setPosition(playerShip.x+43,playerShip.y);
        p.update(Gdx.graphics.getDeltaTime());
        p.draw(batch);
		batch.end();
        bgRenderer.processStars();
	}

	@Override
	public void pause () {
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
	}

    public PlayerShip getPlayerShip() {
        return this.playerShip;
    }
}
