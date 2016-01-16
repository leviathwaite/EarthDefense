package com.nerddaygames.rupert.games.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;
import com.nerddaygames.rupert.games.SpaceLifeGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int)ProtectConstants.VIEWPORT_WIDTH;
		config.height = (int)ProtectConstants.VIEWPORT_HEIGHT;
		config.samples = 4;
		new LwjglApplication(new SpaceLifeGame(), config);
	}
}
