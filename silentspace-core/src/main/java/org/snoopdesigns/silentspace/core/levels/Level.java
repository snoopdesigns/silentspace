package org.snoopdesigns.silentspace.core.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;
import org.snoopdesigns.silentspace.core.levels.objects.LevelObject;
import org.snoopdesigns.silentspace.core.levels.objects.ObjectProcessor;

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

    public void process(ObjectProcessor objProcessor) {
        delta += Gdx.graphics.getDeltaTime();
        if(delta >  lineDelay&& currentLine < descriptionFileSize) {
            delta = 0;
            XmlReader.Element elem = descriptionFile.getChild(currentLine);
            for(int i=0;i<elem.getChildCount();i++) {
                if(!elem.getChild(i).getAttribute("action").equals("null")) {
                    System.out.println("processing new object on line: " + i + ", " + elem.getChild(i).getAttribute("action"));
                    try {
                        Class c = Class.forName("org.snoopdesigns.silentspace.core.levels.objects." + elem.getChild(i).getAttribute("action"));
                        Object obj = c.newInstance();
                        Class[] paramTypes = new Class[] { int.class };
                        Method method = c.getMethod("setLine", paramTypes);
                        Object[] args = new Object[] { new Integer(i) };
                        method.invoke(obj, args);
                        objProcessor.addLevelObject((LevelObject)obj);
                    } catch(Exception e) {
                        e.printStackTrace();
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
}
