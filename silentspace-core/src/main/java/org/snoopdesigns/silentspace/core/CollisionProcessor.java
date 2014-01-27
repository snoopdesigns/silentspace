package org.snoopdesigns.silentspace.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import org.snoopdesigns.silentspace.core.levels.objects.LevelObject;
import org.snoopdesigns.silentspace.core.levels.objects.ObjectProcessor;
import org.snoopdesigns.silentspace.core.weapons.MissilesProcessor;
import org.snoopdesigns.silentspace.core.weapons.missiles.Missile;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CollisionProcessor {

    private ObjectProcessor objectProcessor;
    private MissilesProcessor missilesProcessor;
    private Array<Integer> objectsToDestroy;
    private Map<Integer, Array<Integer>> missilesToDestroy;
    public static final float COLLISION_EPS = 10.0f;

    public CollisionProcessor(ObjectProcessor objProcessor, MissilesProcessor misProcessor) {
        this.objectProcessor = objProcessor;
        this.missilesProcessor = misProcessor;
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
                                objects.get(j).getX(),objects.get(j).getY())) {
                            if(!objectsToDestroy.contains(j,true)) { objectsToDestroy.add(j);}
                            this.addMissileToDestroy(i,k);
                        }
                    }
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

    private boolean checkCollision(float x1, float y1, float x2, float y2) {
        float dx = (x2-x1)*(x2-x1);
        float dy = (y2-y1)*(y2-y1);
        double result = Math.sqrt(dx + dy);
        if(result < COLLISION_EPS) return true;
        return false;
    }
}
