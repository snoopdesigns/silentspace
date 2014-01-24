package org.snoopdesigns.silentspace.core.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import org.snoopdesigns.silentspace.core.config.SilentSpaceConfig;
import org.snoopdesigns.silentspace.core.screens.Screen;

public class MainMenu {

    private BitmapFont font;
    private Array<MenuItem> menuItems = new Array<MenuItem>();
    private ShapeRenderer menuRenderer;
    private Texture logo;
    private int selected = -1;
    private Screen menuScreen;
    public static String[] menuText = {
            "Start",
            "Settings",
            "About",
            "Exit"
    };
    private int[] menuOffsets = {
            31,
            51,
            41,
            25
    };

    public MainMenu(Screen screen) {
        menuScreen = screen;
        menuRenderer = new ShapeRenderer();
        logo = new Texture(Gdx.files.internal("logo.png"));
        font = new BitmapFont(Gdx.files.internal("fonts/calibri.fnt"),
                Gdx.files.internal("fonts/calibri.png"),false);
        for(int i=0;i<menuText.length;i++) {
            MenuItem item = MainMenuMapper.mapMenuItem(menuText[i],screen);
            item.x = SilentSpaceConfig.GAME_WINDOW_WIDTH/2;
            item.y = SilentSpaceConfig.GAME_WINDOW_HEIGHT/2 - i*70;
            item.text = menuText[i];
            item.offset = menuOffsets[i];
            menuItems.add(item);
        }

    }

    public void processMenu(SpriteBatch batch) {
        batch.end();
        for(int i=0;i<menuItems.size;i++) {
            if(i == selected) {
                this.drawMenuItemBG(menuItems.get(i), true);
            } else {
                this.drawMenuItemBG(menuItems.get(i), false);
            }
        }
        batch.begin();
        batch.draw(logo, SilentSpaceConfig.GAME_WINDOW_WIDTH/2 - logo.getWidth()/2,
                SilentSpaceConfig.GAME_WINDOW_HEIGHT/2 + 150);
        for(int i=0;i<menuItems.size;i++) {
            this.drawMenuItemText(menuItems.get(i), batch);
        }

    }

    private void drawMenuItemBG(MenuItem item, boolean selected) {
        Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl20.glEnable(GL20.GL_BLEND);
        menuRenderer.begin(ShapeRenderer.ShapeType.Filled);
        menuRenderer.setColor(0, 0, 0.8f, 0.3f);
        menuRenderer.rect(item.x - 90, item.y - 25, 180, 50);
        menuRenderer.end();
        menuRenderer.begin(ShapeRenderer.ShapeType.Line);
        if(selected) {
            menuRenderer.setColor(1, 1, 1, 0.7f);
        } else {
            menuRenderer.setColor(0, 0, 0, 0.7f);
        }
        menuRenderer.rect(item.x - 90, item.y - 25, 180, 50);
        menuRenderer.end();

    }

    private void drawMenuItemText(MenuItem item, SpriteBatch batch) {
        font.draw(batch, item.text, item.x-item.offset, item.y+12);
    }

    public int isPointerOnMenuItem(int x, int y) {
        int fixedY = SilentSpaceConfig.GAME_WINDOW_HEIGHT - y;
        for(int i=0;i<menuItems.size;i++) {
            if(x > menuItems.get(i).x-90 && x < menuItems.get(i).x+90 &&
                    fixedY > menuItems.get(i).y-25 && fixedY < menuItems.get(i).y+25) {
                return i;
            }
        }
        return -1;
    }

    public void selectMenu(int num) {
        this.selected = num;
    }

    public void clickMenu(int num) {
        this.menuItems.get(num).click();
    }


}
