package org.snoopdesigns.silentspace.core.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class SimpleRocksLevel extends Level{
    @Override
    public FileHandle getLevelDescriptionFile() {
        return Gdx.files.internal("levels/level.xml");
    }
}
