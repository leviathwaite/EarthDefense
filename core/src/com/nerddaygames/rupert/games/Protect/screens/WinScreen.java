package com.nerddaygames.rupert.games.Protect.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;
import com.nerddaygames.rupert.games.SpaceLifeGame;

/**
 * Created by robertwaite on 1/12/16.
 */
public class WinScreen implements Screen, InputProcessor {

    private SpaceLifeGame game;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private boolean debug = true;

    private ExtendViewport viewport;

    public WinScreen(SpaceLifeGame game){
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        // TODO move to debug
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        viewport = new ExtendViewport(ProtectConstants.VIEWPORT_WIDTH, ProtectConstants.VIEWPORT_HEIGHT);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        viewport.apply();

        Gdx.gl.glClearColor(0, 0.65f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        batch.end();

        if(debug){
            debugDraw();
        }
    }

    private void debugDraw() {

    }

    @Override
    public void resize(int width, int height) {

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
        game.setSetGameScreen();
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
