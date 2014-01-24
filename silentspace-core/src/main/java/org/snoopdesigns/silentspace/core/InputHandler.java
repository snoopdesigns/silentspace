package org.snoopdesigns.silentspace.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputHandler implements InputProcessor{

    private SilentSpace renderer;

    public InputHandler() {}

    public InputHandler(ApplicationListener renderer) {
        this.renderer = (SilentSpace)renderer;
    }

    @Override
    public boolean keyDown(int key) {
        System.out.println("keyDown(): " + key);
        if (key == Input.Keys.DPAD_LEFT) renderer.getPlayerShip().moveLeft();
        if (key == Input.Keys.DPAD_RIGHT) renderer.getPlayerShip().moveRight();
        return false;
    }

    @Override
    public boolean keyUp(int key) {
        if (key == Input.Keys.DPAD_LEFT) renderer.getPlayerShip().moveLeft();
        if (key == Input.Keys.DPAD_RIGHT) renderer.getPlayerShip().moveRight();
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i2, int i3, int i4) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i2, int i3, int i4) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i2, int i3) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i2) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}
