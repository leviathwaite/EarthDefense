package com.nerddaygames.rupert.games;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.nerddaygames.rupert.games.Protect.screens.HelpScreen;
import com.nerddaygames.rupert.games.Protect.screens.MenuScreen;
import com.nerddaygames.rupert.games.Protect.screens.ProtectGameScreen;
import com.nerddaygames.rupert.games.Protect.screens.SplashScreen;
import com.nerddaygames.rupert.games.Protect.screens.WinScreen;
import com.nerddaygames.rupert.games.Protect.managers.AssetsManager;
import com.nerddaygames.rupert.games.Protect.managers.GameManager;

public class SpaceLifeGame extends Game{

    public AssetsManager assetsManager;
    public GameManager gameManager;
    private Screen gameScreen;
    private Screen menuScreen;
    private Screen winScreen;


    @Override
    public void create() {

        // TODO add assetManager
        assetsManager = new AssetsManager();

        setSplashScreen();
    }

    public void setMenuScreen(){
        setScreen(new MenuScreen(this));
    }

    public void setSplashScreen(){
        setScreen(new SplashScreen(this));
    }

    public void setHelpScreen(){
        setScreen(new HelpScreen(this));
    }

    public void setSetGameScreen(){
        setScreen(new ProtectGameScreen(this));
    }

    public void setWinScreen(){
        setScreen(new WinScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetsManager.dispose();
    }
}