package org.snoopdesigns.silentspace.core.levels.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

public class ObjectProcessor {

    private List<LevelObject> objects;

    public ObjectProcessor() {
        objects = new ArrayList<LevelObject>();
    }

    public void addLevelObject(LevelObject object) {
        objects.add(object);
    }

    public void process(SpriteBatch batch) {
        for(int i=0;i<objects.size();i++) {
            if(objects.get(i).isActive()) {
                objects.get(i).process(batch);
            } else {
                objects.remove(i);
            }
        }
    }

    public List<LevelObject> getObjects() {
        return objects;
    }

    public void destroyObject(Array<Integer> ids) {
        ids.reverse();
        for(int i=0;i<ids.size;i++) {
            objects.get(ids.get(i)).destroy();
            objects.remove(ids.get(i).intValue());
        }
    }
}
