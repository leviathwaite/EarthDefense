package com.nerddaygames.rupert.games.Protect.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.nerddaygames.rupert.games.Protect.managers.AssetsManager;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;
import com.nerddaygames.rupert.games.SpaceLifeGame;

/**
 * Created by robertwaite on 1/12/16.
 */
public class WinScreen implements Screen, InputProcessor {

    private SpaceLifeGame game;
    private SpriteBatch batch;

    private StretchViewport viewport;

    public WinScreen(SpaceLifeGame game){
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        viewport = new StretchViewport(ProtectConstants.VIEWPORT_WIDTH, ProtectConstants.VIEWPORT_HEIGHT);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        viewport.apply();

        Gdx.gl.glClearColor(0, 0.45f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(AssetsManager.thanks, ProtectConstants.VIEWPORT_WIDTH_CENTER - (ProtectConstants.VIEWPORT_HEIGHT / 4),
                ProtectConstants.VIEWPORT_HEIGHT / 4,
                ProtectConstants.VIEWPORT_HEIGHT / 4, ProtectConstants.VIEWPORT_HEIGHT / 4,
                ProtectConstants.VIEWPORT_HEIGHT / 2, ProtectConstants.VIEWPORT_HEIGHT / 2,
                1, 1, 0);
        batch.end();

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
