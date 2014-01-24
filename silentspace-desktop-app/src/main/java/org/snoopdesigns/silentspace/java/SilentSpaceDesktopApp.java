package org.snoopdesigns.silentspace.java;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.snoopdesigns.silentspace.core.SilentSpace;
import org.snoopdesigns.silentspace.core.config.SilentSpaceConfig;

public class SilentSpaceDesktopApp {
	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        SilentSpaceConfig configFile = new SilentSpaceConfig();
		config.useGL20 = true;
        config.height = SilentSpaceConfig.GAME_WINDOW_HEIGHT;
        config.width = SilentSpaceConfig.GAME_WINDOW_WIDTH;
		new LwjglApplication(new SilentSpace(), config);
	}
}
