package org.snoopdesigns.silentspace.core.screens;

import org.snoopdesigns.silentspace.core.InputHandler;
import org.snoopdesigns.silentspace.core.SilentSpace;

public abstract class Screen {

    public SilentSpace main;

    public abstract void render();

    public abstract void remove();

    public abstract void tick(InputHandler input);

    protected void setScreen (Screen screen) {
        main.setScreen(screen);
    }

    public void init(SilentSpace main) {
        this.main = main;
    }
}
