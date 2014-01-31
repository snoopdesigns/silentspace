package org.snoopdesigns.silentspace.core.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;
import org.snoopdesigns.silentspace.core.levels.objects.dropdowns.DropDownLevelObject;
import org.snoopdesigns.silentspace.core.levels.objects.LevelObject;
import org.snoopdesigns.silentspace.core.levels.objects.ObjectProcessor;
import org.snoopdesigns.silentspace.core.player.PlayerShip;

import java.lang.reflect.Method;

public abstract class Level {

    private float delta;
    private XmlReader.Element descriptionFile;
    private int descriptionFileSize;
    private int currentLine;
    private XmlReader reader;
    private float lineDelay;

    public Level() {
        reader = new XmlReader();
        delta = 0;
        descriptionFile = this.readDescriptionFile(this.getLevelDescriptionFile());
        descriptionFileSize = descriptionFile.getChildCount();
        lineDelay = 0.3f * Integer.parseInt(descriptionFile.getAttribute("delay"));
        currentLine = 0;
    }

    public void process(ObjectProcessor objProcessor, PlayerShip ship) {
        delta += Gdx.graphics.getDeltaTime();
        if(delta >  lineDelay&& currentLine < descriptionFileSize) {
            delta = 0;
            XmlReader.Element elem = descriptionFile.getChild(currentLine);
            if(elem.getChildCount() != 0) {
                for(int i=0;i<elem.getChildCount();i++) {
                    if(!elem.getChild(i).getAttribute("action").equals("null")) {
                        try {
                            Class c = Class.forName("org.snoopdesigns.silentspace.core.levels.objects." + elem.getChild(i).getAttribute("action"));
                            Object obj = c.newInstance();
                            Class[] paramTypes = new Class[] { int.class };
                            Method method = c.getMethod("setLine", paramTypes);
                            Object[] args = new Object[] { new Integer(i) };
                            method.invoke(obj, args);

                            paramTypes = new Class[] { PlayerShip.class };
                            method = c.getMethod("setPlayerShipObject", paramTypes);
                            args = new Object[] { ship };
                            method.invoke(obj, args);

                            if(elem.getChild(i).getAttributes().containsKey("dropdown")) {
                                System.out.println("Adding dropdown item: " + elem.getChild(i).getAttribute("dropdown"));
                                this.addDropDownObject(obj, elem.getChild(i).getAttribute("dropdown"));
                            }
                            objProcessor.addLevelObject((LevelObject)obj);
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            currentLine ++;
        }
    }

    public abstract FileHandle getLevelDescriptionFile();

    private XmlReader.Element readDescriptionFile(FileHandle file) {
        XmlReader.Element main = null;
        try {
        main = reader.parse(file);
            System.out.println(main.getChildCount());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return main;
    }

    private void addDropDownObject(Object object, String name) {
        try {
            Class[] paramTypes = new Class[] { DropDownLevelObject.class };
            Method method = object.getClass().getMethod("setDropdownObject", paramTypes);
            Object[] args = new Object[] { this.getDropDownObject(name) };
            method.invoke(object, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object getDropDownObject(String name) {
        Object obj = null;
        Class c;
        try {
            c = Class.forName("org.snoopdesigns.silentspace.core.levels.objects." + name);
            obj = c.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
