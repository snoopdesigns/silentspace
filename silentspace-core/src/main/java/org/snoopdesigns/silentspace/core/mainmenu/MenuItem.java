package org.snoopdesigns.silentspace.core.mainmenu;

import org.snoopdesigns.silentspace.core.screens.Screen;

public abstract class MenuItem {
    public String text;
    public int x;
    public int y;
    public int offset;
    public abstract void click();
    private final Screen screen;

    protected MenuItem(final Screen screen) {
        this.screen = screen;
    }
}
