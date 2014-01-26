package org.snoopdesigns.silentspace.core.player;

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
    private Texture ammoTexture;
    private Texture weaponTexture;
    private Texture healthTexture;
    private Texture healthPixel;

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
        ammoTexture = new Texture(Gdx.files.internal("hud1.png"));
        weaponTexture = new Texture(Gdx.files.internal("hud2.png"));
        healthTexture = new Texture(Gdx.files.internal("hud3.png"));
        healthPixel = new Texture(Gdx.files.internal("health_item.png"));
        hudRenderer = new ShapeRenderer();
        font = new BitmapFont(Gdx.files.internal("fonts/calibri.fnt"),
                Gdx.files.internal("fonts/calibri.png"),false);
        font.setScale(0.6f, 0.6f);
    }

    public void render(SpriteBatch batch) {
        batch.draw(bgTexture, 0, SilentSpaceConfig.GAME_WINDOW_HEIGHT - bgTexture.getHeight());
        batch.draw(ammoTexture, 10, SilentSpaceConfig.GAME_WINDOW_HEIGHT - 47);
        font.draw(batch, playerAmmoCount, 58, SilentSpaceConfig.GAME_WINDOW_HEIGHT - 47 + 27);
        batch.draw(weaponTexture, 111, SilentSpaceConfig.GAME_WINDOW_HEIGHT - 47);
        font.draw(batch, playerWeaponName, 158, SilentSpaceConfig.GAME_WINDOW_HEIGHT - 47 + 27);
        batch.draw(healthTexture, 260, SilentSpaceConfig.GAME_WINDOW_HEIGHT - 47);
        drawHealthPixels(batch);
    }

    public void drawHealthPixels(SpriteBatch batch) {
        for(int i=0;i<10;i++) {
            batch.draw(healthPixel, 268 + i*9, SilentSpaceConfig.GAME_WINDOW_HEIGHT - 47 + 6);
        }
    }
}
