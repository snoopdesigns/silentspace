package org.snoopdesigns.silentspace.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import org.snoopdesigns.silentspace.core.screens.MenuScreen;
import org.snoopdesigns.silentspace.core.screens.Screen;

public class SilentSpace implements ApplicationListener {

    private Screen screen;
    private InputHandler input = new InputHandler();

	@Override
	public void create () {
        Gdx.input.setInputProcessor(input);
        setScreen(new MenuScreen());
	}

	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        screen.tick(input);
        input.tick();
        screen.render();
	}

    public void setScreen (Screen newScreen) {
        if (screen != null) screen.remove();
        screen = newScreen;
        if (screen != null) screen.init(this);
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
}
