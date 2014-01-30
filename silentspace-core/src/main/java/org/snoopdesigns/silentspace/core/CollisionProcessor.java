package org.snoopdesigns.silentspace.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import org.snoopdesigns.silentspace.core.audio.AudioProcessor;
import org.snoopdesigns.silentspace.core.levels.objects.DropDownLevelObject;
import org.snoopdesigns.silentspace.core.levels.objects.Explosion;
import org.snoopdesigns.silentspace.core.levels.objects.LevelObject;
import org.snoopdesigns.silentspace.core.levels.objects.ObjectProcessor;
import org.snoopdesigns.silentspace.core.player.PlayerShip;
import org.snoopdesigns.silentspace.core.weapons.MissilesProcessor;
import org.snoopdesigns.silentspace.core.weapons.missiles.Missile;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CollisionProcessor {

    private ObjectProcessor objectProcessor;
    private MissilesProcessor missilesProcessor;
    private PlayerShip playerShip;
    private Array<Integer> objectsToDestroy;
    private Map<Integer, Array<Integer>> missilesToDestroy;
    public static final float COLLISION_EPS = 15.0f;
    public static final float PLAYER_COLLISION_EPS = 35.0f;

    public CollisionProcessor(ObjectProcessor objProcessor, MissilesProcessor misProcessor, PlayerShip playerShip) {
        this.objectProcessor = objProcessor;
        this.missilesProcessor = misProcessor;
        this.playerShip = playerShip;
        objectsToDestroy = new Array<Integer>();
        missilesToDestroy = new HashMap<Integer, Array<Integer>>();
    }

    public void process(SpriteBatch batch) {
        List<Missile> missiles = missilesProcessor.getActiveMissiles();
        List<LevelObject> objects = objectProcessor.getObjects();

        for(int i=0;i<missiles.size();i++) {
            for(int j=0;j<objects.size();j++) {
                for(int k=0;k<missiles.get(i).getMissilesInfo().size;k++) {
                    if(objects.get(j).isDestroyble()) {
                        if(checkCollision(missiles.get(i).getMissilesInfo().get(k).getX(),
                                missiles.get(i).getMissilesInfo().get(k).getY(),
                                objects.get(j).getX(),objects.get(j).getY(), COLLISION_EPS)) {
                                objects.get(j).health -= missiles.get(i).getMissileStrength();
                                if(objects.get(j).health < 0) {
                                    if(!objectsToDestroy.contains(j,true)) { objectsToDestroy.add(j);}
                                } else {
                                    objects.add(new Explosion((int)objects.get(j).getX()-2, (int)objects.get(j).getY()-6,
                                            objects.get(j).getHitAnimation(), 20));
                                }
                                    this.addMissileToDestroy(i,k);
                        }
                    }
                }
            }
        }

        for(int i=0;i<objects.size();i++) {
            if(!objects.get(i).isPlayerShip()) {
                if(checkCollision(playerShip.x + 42, playerShip.y + 42, objects.get(i).getX(),
                        objects.get(i).getY(), PLAYER_COLLISION_EPS)) {
                    if(objects.get(i).isCatchable() && objects.get(i) instanceof DropDownLevelObject) {
                        ((DropDownLevelObject) objects.get(i)).updatePlayer(playerShip);
                        AudioProcessor.playEffect(Gdx.audio.newSound(Gdx.files.internal("audio/effects/item_collect.wav")));
                        objects.add(new Explosion((int)playerShip.x + 42, (int)playerShip.y + 42,
                                objects.get(i).getAnimation(objects.get(i).getAnimationCols(), objects.get(i).getAnimationRows()),
                                objects.get(i).getAnimationCols() * objects.get(i).getAnimationRows()));
                    } else {
                        playerShip.setHealth(playerShip.getHealth() - 10);
                    }
                    if(!objectsToDestroy.contains(i,true)) { objectsToDestroy.add(i);}
                }
            }
        }

        if(objectsToDestroy.size > 0) objectProcessor.destroyObject(objectsToDestroy);
        objectsToDestroy.clear();

        Iterator it = missilesToDestroy.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            missiles.get((Integer)pairs.getKey()).destroyMissile((Array<Integer>)pairs.getValue());
        }
        missilesToDestroy.clear();
    }

    public void addMissileToDestroy(int missileid, int shotid) {
        if(missilesToDestroy.containsKey(missileid)) {
            Array<Integer> shots = missilesToDestroy.get(missileid);
            shots.add(shotid);
            missilesToDestroy.remove(missileid);
            missilesToDestroy.put(missileid, shots);
        } else {
            Array<Integer> shots = new Array<Integer>();
            shots.add(shotid);
            missilesToDestroy.put(missileid, shots);
        }
    }

    private boolean checkCollision(float x1, float y1, float x2, float y2, float eps) {
        float dx = (x2-x1)*(x2-x1);
        float dy = (y2-y1)*(y2-y1);
        double result = Math.sqrt(dx + dy);
        if(result < eps) return true;
        return false;
    }
}
