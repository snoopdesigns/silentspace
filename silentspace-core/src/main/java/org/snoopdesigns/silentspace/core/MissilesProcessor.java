package org.snoopdesigns.silentspace.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.snoopdesigns.silentspace.core.weapons.missiles.Missile;

import java.util.ArrayList;
import java.util.List;

public class MissilesProcessor {

    private List<Missile> activeMissiles = new ArrayList<Missile>();

    public void addActiveMissile(Missile missile) {
        activeMissiles.add(missile);
    }

    public void process(SpriteBatch batch) {
        for(int i=0;i<activeMissiles.size();i++) {
            if(activeMissiles.get(i).isMissileInactive()) {
                activeMissiles.remove(i);
                System.out.println("Removing missile");
            } else {
                System.out.println("Processing missile:");
                activeMissiles.get(i).processMissile(batch);
            }
        }
    }
}
