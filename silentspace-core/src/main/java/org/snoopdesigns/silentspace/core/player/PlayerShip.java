package org.snoopdesigns.silentspace.core.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.snoopdesigns.silentspace.core.config.SilentSpaceConfig;
import org.snoopdesigns.silentspace.core.levels.objects.LevelObject;
import org.snoopdesigns.silentspace.core.weapons.MissilesProcessor;
import org.snoopdesigns.silentspace.core.weapons.Weapon;
import org.snoopdesigns.silentspace.core.weapons.missiles.Missile;

public class PlayerShip extends LevelObject{

    public float x;
    public float y;


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        this.playerHUD.setPlayerHealth(health);
    }

    private Texture shipTexture;
    private boolean movingRight;
    private boolean movingLeft;
    private boolean movingUp;
    private boolean movingDown;
    private ParticleEffect engineLeft;
    private ParticleEffect engineRight;

    public PlayerHUD getPlayerHUD() {
        return playerHUD;
    }

    private PlayerHUD playerHUD;

    public MissilesProcessor getMissilesProcessor() {
        return missilesProcessor;
    }

    private MissilesProcessor missilesProcessor;

    public PlayerWeaponsProcessor getWepProcessor() {
        return wepProcessor;
    }

    private PlayerWeaponsProcessor wepProcessor;

    public PlayerShip(MissilesProcessor mis) {
        this.missilesProcessor = mis;
        playerHUD = new PlayerHUD();
        this.health = 170;
        playerHUD.setPlayerHealth(health);
        wepProcessor = new PlayerWeaponsProcessor(playerHUD);
        shipTexture = new Texture(Gdx.files.internal("player/ship.png"));
        this.x = SilentSpaceConfig.GAME_WINDOW_WIDTH/2;
        this.y = 50;
        this.movingLeft = false;
        this.movingRight = false;
        this.movingUp = false;
        this.movingDown = false;
        engineLeft = new ParticleEffect();
        engineLeft.load(Gdx.files.internal("effects/rocket.p"), Gdx.files.internal("effects"));
        engineRight = new ParticleEffect();
        engineRight.load(Gdx.files.internal("effects/rocket.p"), Gdx.files.internal("effects"));
    }

    public void process(SpriteBatch batch) {
        batch.draw(shipTexture, this.x, this.y);
        if(this.movingLeft && checkBounds(x-SilentSpaceConfig.SHIP_MOVE_SPEED * Gdx.graphics.getDeltaTime(), y)) {
            this.x -= (SilentSpaceConfig.SHIP_MOVE_SPEED * Gdx.graphics.getDeltaTime());
        }
        if(this.movingRight && checkBounds(x+SilentSpaceConfig.SHIP_MOVE_SPEED * Gdx.graphics.getDeltaTime(),y)) {
            this.x += (SilentSpaceConfig.SHIP_MOVE_SPEED * Gdx.graphics.getDeltaTime());
        }
        if(this.movingUp && checkBounds(x,y+SilentSpaceConfig.SHIP_MOVE_SPEED * 0.5f * Gdx.graphics.getDeltaTime())) {
            this.y += (SilentSpaceConfig.SHIP_MOVE_SPEED * 0.5 * Gdx.graphics.getDeltaTime());
        }
        if(this.movingDown && checkBounds(x,y-SilentSpaceConfig.SHIP_MOVE_SPEED * 1.5f * Gdx.graphics.getDeltaTime())) {
            this.y -= (SilentSpaceConfig.SHIP_MOVE_SPEED * 1.5 * Gdx.graphics.getDeltaTime());
        }
        engineLeft.setPosition(x+37,y+3);
        engineLeft.update(Gdx.graphics.getDeltaTime());
        engineLeft.draw(batch);
        engineRight.setPosition(x+48,y+3);
        engineRight.update(Gdx.graphics.getDeltaTime());
        engineRight.draw(batch);
        this.setHealth(this.health);
    }

    public boolean checkBounds(float x, float y) {
        if(x > 0 && x < SilentSpaceConfig.GAME_WINDOW_WIDTH - shipTexture.getWidth() &&
                y > 0 && y < SilentSpaceConfig.GAME_WINDOW_HEIGHT - shipTexture.getHeight()) {
            return true;
        } else {
            return false;
        }
    }

    public Missile fireActiveWeapon() {
        return wepProcessor.firePlayerActiveWeapon((int)this.getX(), (int)this.getY());
    }

    public void addPlayerWeapon(Weapon weapon) {
        this.wepProcessor.addPlayerWeapon(weapon);
    }

    public void moveRight() {
        if(movingRight) movingRight = false;
        else movingRight = true;
    }

    public void moveLeft() {
        if(movingLeft) movingLeft = false;
        else movingLeft = true;
    }

    public void moveUp() {
        if(movingUp) movingUp = false;
        else movingUp = true;
    }

    public void moveDown() {
        if(movingDown) movingDown = false;
        else movingDown = true;
    }

    @Override
    public int getInitialHealth() {
        return 1700;
    }

    @Override
    public float getX() {
        return this.x + shipTexture.getWidth()/2;
    }

    @Override
    public float getY() {
        return this.y + shipTexture.getHeight()/2;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void destroy() {

    }

    @Override
    public FileHandle getAnimationFile() {
        return Gdx.files.internal("effects/animations/explosion.png");
    }

    @Override
    public int getAnimationRows() {
        return 5;
    }

    @Override
    public int getAnimationCols() {
        return 4;
    }

    @Override
    public boolean isExplodable() {
        return true;
    }

    @Override
    public boolean isDestroyble() {
        return true;
    }

    @Override
    public boolean isCatchable() {
        return false;
    }


    @Override
    public boolean isPlayerShip() {
        return true;
    }

    @Override
    public float getCollisionEspilon() {
        return shipTexture.getHeight()/2-5f;
    }
}
