package com.nerddaygames.rupert.games.Protect.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.nerddaygames.rupert.games.Protect.managers.AssetsManager;
import com.nerddaygames.rupert.games.Protect.screens.Buttons.MenuButton;
import com.nerddaygames.rupert.games.Protect.screens.Buttons.StartButton;
import com.nerddaygames.rupert.games.Protect.selectionwindow.MenuObject;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;
import com.nerddaygames.rupert.games.SpaceLifeGame;

/**
 * Created by robertwaite on 1/12/16.
 */
public class HelpScreen implements Screen, InputProcessor {

    private SpaceLifeGame game;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private boolean debug = true;

    private StretchViewport viewport;

    private Vector2 startButtonPosition;
    private StartButton startButton;
    private Vector2 menuButtonPosition;
    private MenuButton menuButton;

    public HelpScreen(SpaceLifeGame game){
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        // TODO move to debug
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        viewport = new StretchViewport(ProtectConstants.VIEWPORT_WIDTH, ProtectConstants.VIEWPORT_HEIGHT);

        startButtonPosition = new Vector2(ProtectConstants.VIEWPORT_WIDTH_CENTER + (ProtectConstants.BUTTON_WIDTH * 0.25f),
                ProtectConstants.BUTTON_HEIGHT * 0.5f);
        startButton = new StartButton(startButtonPosition);

        menuButtonPosition = new Vector2(ProtectConstants.VIEWPORT_WIDTH_CENTER - (ProtectConstants.BUTTON_WIDTH * 1.25f),
                ProtectConstants.BUTTON_HEIGHT * 0.5f);
        menuButton = new MenuButton(menuButtonPosition);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        viewport.apply();

        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(AssetsManager.helpbackground, 0, 0,
                ProtectConstants.VIEWPORT_WIDTH / 2, ProtectConstants.VIEWPORT_HEIGHT / 2,
                ProtectConstants.VIEWPORT_WIDTH, ProtectConstants.VIEWPORT_HEIGHT,
                1, 1, 0);
        batch.draw(AssetsManager.help, 0, ProtectConstants.BUTTON_HEIGHT * 1.5f,
                ProtectConstants.VIEWPORT_WIDTH / 2, (ProtectConstants.VIEWPORT_HEIGHT - (ProtectConstants.BUTTON_HEIGHT * 2.5f)) / 2,
                ProtectConstants.VIEWPORT_WIDTH, ProtectConstants.VIEWPORT_HEIGHT - ProtectConstants.BUTTON_HEIGHT * 1.5f,
                1, 1, 0);
        menuButton.draw(batch);
        startButton.draw(batch);
        batch.end();

        if(debug){
            debugDraw();
        }
    }

    private void debugDraw() {
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        menuButton.debugDraw(shapeRenderer);
        startButton.debugDraw(shapeRenderer);
        shapeRenderer.end();
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
        shapeRenderer.dispose();
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
        Vector2 worldClick = viewport.unproject(new Vector2(screenX, screenY));
        if(startButton.checkForTouch(worldClick)){
            game.setSetGameScreen();
        }

        if(menuButton.checkForTouch(worldClick)){
            game.setMenuScreen();
        }
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
