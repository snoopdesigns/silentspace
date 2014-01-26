package org.snoopdesigns.silentspace.core.levels.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.snoopdesigns.silentspace.core.levels.objects.LevelObject;

import java.util.ArrayList;
import java.util.List;

public class ObjectProcessor {

    List<LevelObject> objects;

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
}
