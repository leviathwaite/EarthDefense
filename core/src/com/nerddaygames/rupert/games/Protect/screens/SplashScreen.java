package com.nerddaygames.rupert.games.Protect.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.nerddaygames.rupert.games.Protect.managers.AssetsManager;
import com.nerddaygames.rupert.games.Protect.screens.Buttons.HelpButton;
import com.nerddaygames.rupert.games.Protect.screens.Buttons.StartButton;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;
import com.nerddaygames.rupert.games.SpaceLifeGame;

/**
 * Created by robertwaite on 1/17/16.
 */
public class SplashScreen  implements Screen, InputProcessor {

    private SpaceLifeGame game;
    private SpriteBatch batch;

    private StretchViewport viewport;
    Color orgColor;

    float fade = 0;
    private float fadeElapsed = 0;

    private boolean fadein = true;
    private float waitTime = 100;

    private static final float FADE_IN_TIME = 30f;

    public SplashScreen(SpaceLifeGame game){
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        orgColor = new Color(batch.getColor());

        viewport = new StretchViewport(ProtectConstants.VIEWPORT_WIDTH, ProtectConstants.VIEWPORT_HEIGHT);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        viewport.apply();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        fade = Interpolation.fade.apply(fadeElapsed / FADE_IN_TIME);
        if(fadein) {
            fade = 1 - fade;
        }
        if(fade == 1){
            fadeElapsed = 0;
            tickWaitTime();
        }
        batch.setColor(orgColor.r, orgColor.g, orgColor.b, fade);


        batch.begin();
        batch.draw(AssetsManager.splash, ProtectConstants.VIEWPORT_WIDTH_CENTER - (ProtectConstants.VIEWPORT_HEIGHT / 4),
                ProtectConstants.VIEWPORT_HEIGHT / 4,
                ProtectConstants.VIEWPORT_HEIGHT / 4, ProtectConstants.VIEWPORT_HEIGHT / 4,
                ProtectConstants.VIEWPORT_HEIGHT / 2, ProtectConstants.VIEWPORT_HEIGHT / 2,
                1, 1, 0);
        batch.setColor(orgColor);
        batch.end();

        if(!fadein && fade == 0){
            game.setMenuScreen();
        }

    }

    private void tickWaitTime() {
        if(waitTime < 0){
            fadein = false;
        }else{
            waitTime--;
        }
    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        game.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        game.setMenuScreen();
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
