package org.snoopdesigns.silentspace.core.levels.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import org.snoopdesigns.silentspace.core.audio.AudioProcessor;

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
            objects.get(ids.get(i).intValue()).destroy();
            if(objects.get(ids.get(i).intValue()).isExplodable()) {
                int rows = objects.get(ids.get(i).intValue()).getAnimationRows();
                int cols = objects.get(ids.get(i).intValue()).getAnimationCols();
                Animation anim = objects.get(ids.get(i).intValue()).getAnimation(cols, rows);
                int x = (int)objects.get(ids.get(i).intValue()).getX();
                int y = (int)objects.get(ids.get(i).intValue()).getY();
                this.addLevelObject(new Explosion(x,y,anim, rows * cols));
                AudioProcessor.playEffect(Gdx.audio.newSound(Gdx.files.internal("audio/effects/explode.wav")));
            }
            if(objects.get(ids.get(i).intValue()).getDropdownObject() != null) {
                DropDownLevelObject object = objects.get(ids.get(i).intValue()).getDropdownObject();
                object.setStartX(objects.get(ids.get(i).intValue()).getX());
                object.setStartY(objects.get(ids.get(i).intValue()).getY());
                objects.add(object);
            }
            objects.remove(ids.get(i).intValue());

        }
    }
}
