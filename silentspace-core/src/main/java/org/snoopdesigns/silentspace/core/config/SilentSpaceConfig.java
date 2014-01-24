package org.snoopdesigns.silentspace.core.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class SilentSpaceConfig {

    public static int BG_SCROLL_SPEED = 70;
    public static int GAME_WINDOW_HEIGHT = 480;
    public static int GAME_WINDOW_WIDTH = 640;
    public static int SHIP_MOVE_SPEED = 200;

    public SilentSpaceConfig()
    {
        Properties props = null;
        try {
            props = new Properties();
            props.load(new FileInputStream(new File("config/config.ini")));
        } catch(Exception e) {
            e.printStackTrace();
        }

        BG_SCROLL_SPEED = Integer.valueOf(props.getProperty("BG_SCROLL_SPEED", "70"));
        GAME_WINDOW_HEIGHT = Integer.valueOf(props.getProperty("GAME_WINDOW_HEIGHT", "480"));
        GAME_WINDOW_WIDTH = Integer.valueOf(props.getProperty("GAME_WINDOW_WIDTH", "640"));
        SHIP_MOVE_SPEED = Integer.valueOf(props.getProperty("SHIP_MOVE_SPEED", "200"));
    }
}
