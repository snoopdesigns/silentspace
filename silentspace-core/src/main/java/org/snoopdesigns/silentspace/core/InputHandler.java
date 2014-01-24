package org.snoopdesigns.silentspace.core;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputHandler implements InputProcessor{

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    public static final int ENTER = 4;

    public boolean[] buttons = new boolean[64];
    public boolean[] oldButtons = new boolean[64];

    public void set(int key, boolean down) {
        int button = -1;

        if (key == Input.Keys.DPAD_UP) button = UP;
        if (key == Input.Keys.DPAD_LEFT) button = LEFT;
        if (key == Input.Keys.DPAD_DOWN) button = DOWN;
        if (key == Input.Keys.DPAD_RIGHT) button = RIGHT;
        if (key == Input.Keys.ENTER) button = ENTER;

        if (button >= 0) {
            buttons[button] = down;
        }
    }

    public void tick () {
        for (int i = 0; i < buttons.length; i++) {
            oldButtons[i] = buttons[i];
        }
    }

    public boolean isKeyPressed(int key) {
        if(buttons[key] == true &&
                oldButtons[key] == false) {
            return true;
        }
        return false;
    }

    public boolean isKeyReleased(int key) {
        if(buttons[key] == false &&
                oldButtons[key] == true) {
            return true;
        }
        return false;
    }

    @Override
    public boolean keyDown(int key) {
        set(key,true);
        return false;
    }

    @Override
    public boolean keyUp(int key) {
        set(key,false);
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
