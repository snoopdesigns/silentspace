package org.snoopdesigns.silentspace.core.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.snoopdesigns.silentspace.core.InputHandler;
import org.snoopdesigns.silentspace.core.audio.AudioProcessor;
import org.snoopdesigns.silentspace.core.mainmenu.MainMenu;
import org.snoopdesigns.silentspace.core.bg.BackgroundRenderer;

public class MenuScreen extends Screen{

    private SpriteBatch batch;
    private BackgroundRenderer bg;
    private MainMenu menu;

    public MenuScreen() {
        batch = new SpriteBatch();
        bg = new BackgroundRenderer();
        menu = new MainMenu(this);
        AudioProcessor.playIntro();
    }

    @Override
    public void render() {
        batch.begin();
        bg.processBackground(batch);
        menu.processMenu(batch);
        batch.end();
    }

    @Override
    public void remove() {
        AudioProcessor.stopIntro();
    }

    @Override
    public void tick(InputHandler input) {
        menu.selectMenu(menu.isPointerOnMenuItem(input.getMouseInfo().mousex, input.getMouseInfo().mousey));
        if(input.isMousePressed(InputHandler.MOUSE_LEFT)) {
            int menuItemId = menu.isPointerOnMenuItem(input.getMouseInfo().leftBtnDownInfo[0],
                input.getMouseInfo().leftBtnDownInfo[1]);
            if(menuItemId != -1) menu.clickMenu(menuItemId);
        }
        input.tick();
    }
}
