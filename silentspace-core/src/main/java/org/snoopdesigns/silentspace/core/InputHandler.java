package org.snoopdesigns.silentspace.core;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputHandler implements InputProcessor{

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    public static final int ENTER = 4;

    public static final int MOUSE_LEFT = 0;
    public static final int MOUSE_RIGHT = 1;

    public boolean[] buttons = new boolean[64];
    public boolean[] oldButtons = new boolean[64];

    private MouseInfo mouseInfo = new MouseInfo();

    public class MouseInfo {
        public int mousex = 0;
        public int mousey = 0;
        public boolean[] oldMouseState = new boolean[2];
        public boolean[] mouseState = new boolean[2];
        public int mousexdrag = 0;
        public int mouseydrag = 0;
        public int[] leftBtnDownInfo = new int[2];
        public int[] leftBtnUpInfo = new int[2];
    }

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

    public MouseInfo getMouseInfo() {
        return this.mouseInfo;
    }

    public void setMousePosition(int x, int y) {
        this.mouseInfo.mousex = x;
        this.mouseInfo.mousey = y;
    }

    public void setMouseDragPosition(int x, int y) {
        this.mouseInfo.mousexdrag = x;
        this.mouseInfo.mouseydrag = y;
    }

    public void tick () {
        for (int i = 0; i < buttons.length; i++) {
            oldButtons[i] = buttons[i];
        }
        for (int i = 0; i < mouseInfo.mouseState.length; i++) {
            mouseInfo.oldMouseState[i] = mouseInfo.mouseState[i];
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

    public boolean isMousePressed(int key) {
        if(mouseInfo.mouseState[key] == true &&
                mouseInfo.oldMouseState[key] == false) {
            return true;
        }
        return false;
    }

    public boolean isMouseReleased(int key) {
        if(mouseInfo.mouseState[key] == false &&
                mouseInfo.oldMouseState[key] == true) {
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
        if(i4 == Input.Buttons.LEFT) {
            mouseInfo.mouseState[MOUSE_LEFT] = true;
            mouseInfo.leftBtnDownInfo[0] = i;
            mouseInfo.leftBtnDownInfo[1] = i2;
        } else {
            mouseInfo.mouseState[MOUSE_RIGHT] = true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int i, int i2, int i3, int i4) {
        if(i4 == Input.Buttons.LEFT) {
            mouseInfo.mouseState[MOUSE_LEFT] = false;
            mouseInfo.leftBtnUpInfo[0] = i;
            mouseInfo.leftBtnUpInfo[1] = i2;
        } else {
            mouseInfo.mouseState[MOUSE_RIGHT] = false;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i2, int i3) {
        setMouseDragPosition(i, i2);
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i2) {
        setMousePosition(i,i2);
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}
