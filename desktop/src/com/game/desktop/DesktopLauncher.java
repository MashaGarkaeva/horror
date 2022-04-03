package com.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.game.Horror;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Horror.WIDTH;
		config.height = Horror.HEIGHT;
		config.title = Horror.TITLE;
		new LwjglApplication(new Horror(), config);
	}
}
