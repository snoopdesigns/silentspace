package org.snoopdesigns.silentspace.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.snoopdesigns.silentspace.core.levels.objects.LevelObject;
import org.snoopdesigns.silentspace.core.levels.objects.ObjectProcessor;
import org.snoopdesigns.silentspace.core.weapons.MissilesProcessor;
import org.snoopdesigns.silentspace.core.weapons.missiles.Missile;

import java.util.List;

public class CollisionProcessor {

    private ObjectProcessor objectProcessor;
    private MissilesProcessor missilesProcessor;

    public CollisionProcessor(ObjectProcessor objProcessor, MissilesProcessor misProcessor) {
        this.objectProcessor = objProcessor;
        this.missilesProcessor = misProcessor;
    }

    public void process(SpriteBatch batch) {
        List<Missile> missiles = missilesProcessor.getActiveMissiles();
        List<LevelObject> objects = objectProcessor.getObjects();

        for(int i=0;i<missiles.size();i++) {
            for(int j=0;j<objects.size();j++) {
                for(int k=0;k<missiles.get(i).getMissilesInfo().size;k++) {
                    if(checkCollision(missiles.get(i).getMissilesInfo().get(k).getX(),
                            missiles.get(i).getMissilesInfo().get(k).getY(),
                            objects.get(j).getX(),objects.get(j).getY())) {
                        objectProcessor.destroyObject(j);
                    }
                }
            }
        }
    }

    private boolean checkCollision(float x1, float y1, float x2, float y2) {
        float dx = (x2-x1)*(x2-x1);
        float dy = (y2-y1)*(y2-y1);
        double result = Math.sqrt(dx + dy);
        if(result < 40) return true;
        return false;
    }
}
