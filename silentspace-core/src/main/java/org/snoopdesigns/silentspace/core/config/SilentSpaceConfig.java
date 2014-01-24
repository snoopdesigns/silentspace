package org.snoopdesigns.silentspace.core.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class SilentSpaceConfig {

    public static int BG_SCROLL_SPEED;
    public static int GAME_WINDOW_HEIGHT;
    public static int GAME_WINDOW_WIDTH;
    public static int SHIP_MOVE_SPEED;

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
        System.out.println("BG_SCROLL_SPEED: " + BG_SCROLL_SPEED);
        System.out.println("GAME_WINDOW_HEIGHT: " + GAME_WINDOW_HEIGHT);
        System.out.println("GAME_WINDOW_WIDTH: " + GAME_WINDOW_WIDTH);
        System.out.println("SHIP_MOVE_SPEED: " + SHIP_MOVE_SPEED);
    }
}
