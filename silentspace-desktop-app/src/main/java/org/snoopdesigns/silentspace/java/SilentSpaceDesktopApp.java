package org.snoopdesigns.silentspace.java;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import org.snoopdesigns.silentspace.core.SilentSpace;

public class SilentSpaceDesktopApp {
	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.useGL20 = true;
		new LwjglApplication(new SilentSpace(), config);
	}
}
