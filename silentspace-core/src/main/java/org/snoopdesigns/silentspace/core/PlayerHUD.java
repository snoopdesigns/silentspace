package org.snoopdesigns.silentspace.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.snoopdesigns.silentspace.core.config.SilentSpaceConfig;
import org.snoopdesigns.silentspace.core.mainmenu.MenuItem;

public class PlayerHUD {

    private Texture bgTexture;
    private ShapeRenderer hudRenderer;
    private BitmapFont font;
    private String playerHealth;
    private String playerWeaponName;
    private String playerAmmoCount;

    public void setPlayerHealth(String playerHealth) {
        this.playerHealth = playerHealth;
    }

    public void setPlayerAmmoCount(String playerAmmoCount) {
        this.playerAmmoCount = playerAmmoCount;
    }

    public void setPlayerWeaponName(String playerWeaponName) {
        this.playerWeaponName = playerWeaponName;
    }

    public PlayerHUD() {
        bgTexture = new Texture(Gdx.files.internal("hud_bg.png"));
        hudRenderer = new ShapeRenderer();
        font = new BitmapFont(Gdx.files.internal("fonts/calibri.fnt"),
                Gdx.files.internal("fonts/calibri.png"),false);
        font.setScale(0.35f, 0.35f);
    }

    public void render(SpriteBatch batch) {
        batch.draw(bgTexture, 0, SilentSpaceConfig.GAME_WINDOW_HEIGHT - bgTexture.getHeight());
        this.drawSimpleBox(batch, 20, 100, "Weapon: " + playerWeaponName, 13);
        this.drawSimpleBox(batch, SilentSpaceConfig.GAME_WINDOW_WIDTH-120, 100, "Ammo: " + playerAmmoCount, 27);
        this.drawSimpleBox(batch, 140, SilentSpaceConfig.GAME_WINDOW_WIDTH - 280, "", 0);
    }

    private void drawSimpleBox(SpriteBatch batch, int startx, int width, String text, int textoffset) {
        batch.end();
        Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl20.glEnable(GL20.GL_BLEND);
        hudRenderer.begin(ShapeRenderer.ShapeType.Filled);
        hudRenderer.setColor(0, 0, 0.8f, 0.3f);
        hudRenderer.rect(startx, SilentSpaceConfig.GAME_WINDOW_HEIGHT - bgTexture.getHeight() + 17,
                width, bgTexture.getHeight() - 20);
        hudRenderer.end();
        hudRenderer.begin(ShapeRenderer.ShapeType.Line);
        hudRenderer.setColor(0, 0, 0, 0.7f);
        hudRenderer.rect(startx, SilentSpaceConfig.GAME_WINDOW_HEIGHT - bgTexture.getHeight() + 17,
                width, bgTexture.getHeight() - 20);
        hudRenderer.end();
        batch.begin();
        font.draw(batch, text, startx + textoffset, SilentSpaceConfig.GAME_WINDOW_HEIGHT - bgTexture.getHeight() + 27);
    }
}
