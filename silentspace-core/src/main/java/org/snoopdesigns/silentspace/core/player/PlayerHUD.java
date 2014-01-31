package org.snoopdesigns.silentspace.core.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.snoopdesigns.silentspace.core.config.SilentSpaceConfig;

public class PlayerHUD {

    private Texture bgTexture;
    private ShapeRenderer hudRenderer;
    private BitmapFont font;
    private BitmapFont messageFont;
    private float playerHealth;
    private String playerWeaponName;
    private String playerAmmoCount;
    private Texture ammoTexture;
    private Texture weaponTexture;
    private Texture healthTexture;
    private Texture healthPixel;
    private float playerTextDuration = 0f;
    private String playerTextString = "";

    public void setPlayerHealth(float playerHealth) {
        this.playerHealth = playerHealth;
    }

    public void setPlayerAmmoCount(String playerAmmoCount) {
        this.playerAmmoCount = playerAmmoCount;
    }

    public void setPlayerWeaponName(String playerWeaponName) {
        this.playerWeaponName = playerWeaponName;
    }

    public PlayerHUD() {
        bgTexture = new Texture(Gdx.files.internal("player/hud/hud_bg.png"));
        ammoTexture = new Texture(Gdx.files.internal("player/hud/hud1.png"));
        weaponTexture = new Texture(Gdx.files.internal("player/hud/hud2.png"));
        healthTexture = new Texture(Gdx.files.internal("player/hud/hud3.png"));
        healthPixel = new Texture(Gdx.files.internal("player/hud/health_item.png"));
        hudRenderer = new ShapeRenderer();
        font = new BitmapFont(Gdx.files.internal("fonts/calibri.fnt"),
                Gdx.files.internal("fonts/calibri.png"),false);
        messageFont = new BitmapFont(Gdx.files.internal("fonts/hud.fnt"),
                Gdx.files.internal("fonts/hud.png"),false);
        font.setScale(0.6f, 0.6f);
    }

    public void render(SpriteBatch batch) {
        font.setScale(0.6f);
        batch.draw(bgTexture, 0, SilentSpaceConfig.GAME_WINDOW_HEIGHT - bgTexture.getHeight());
        batch.draw(ammoTexture, 10, SilentSpaceConfig.GAME_WINDOW_HEIGHT - 47);
        font.draw(batch, playerAmmoCount, 58, SilentSpaceConfig.GAME_WINDOW_HEIGHT - 47 + 27);
        batch.draw(weaponTexture, 111, SilentSpaceConfig.GAME_WINDOW_HEIGHT - 47);
        font.draw(batch, playerWeaponName, 158, SilentSpaceConfig.GAME_WINDOW_HEIGHT - 47 + 27);
        batch.draw(healthTexture, 260, SilentSpaceConfig.GAME_WINDOW_HEIGHT - 47);
        drawHealthPixels(batch);
        if(playerTextDuration > 0f) {
            if(playerTextDuration < 0.2f) {
                messageFont.setScale(playerTextDuration * 5f);
                System.out.println("Text width = " + font.getBounds(playerTextString).width);
            } else {
                messageFont.setScale(1);
                System.out.println("Text width = " + font.getBounds(playerTextString).width);
            }
            messageFont.draw(batch, playerTextString, SilentSpaceConfig.GAME_WINDOW_WIDTH/2 - messageFont.getBounds(playerTextString).width/2,
                    SilentSpaceConfig.GAME_WINDOW_HEIGHT/4*3);
            if(playerTextDuration < 2) {
                playerTextDuration += Gdx.graphics.getDeltaTime();
            } else {
                playerTextDuration = 0f;
            }
        }
    }

    public void drawHealthPixels(SpriteBatch batch) {
        for(int i=0;i<17;i++) {
            if(this.playerHealth > i*10) {
                batch.draw(healthPixel, 268 + i*9, SilentSpaceConfig.GAME_WINDOW_HEIGHT - 47 + 6);
            }
        }
    }

    public void writePlayerText(String text) {
        playerTextDuration = 0.1f;
        playerTextString = text;
    }
}
