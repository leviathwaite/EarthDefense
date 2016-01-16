package com.nerddaygames.rupert.games.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;
import com.nerddaygames.rupert.games.SpaceLifeGame;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration((int)ProtectConstants.VIEWPORT_WIDTH, (int)ProtectConstants.VIEWPORT_HEIGHT);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new SpaceLifeGame();
        }
}