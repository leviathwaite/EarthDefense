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
import com.nerddaygames.rupert.games.Protect.screens.Buttons.HelpButton;
import com.nerddaygames.rupert.games.Protect.screens.Buttons.StartButton;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;
import com.nerddaygames.rupert.games.SpaceLifeGame;

/**
 * Created by robertwaite on 1/12/16.
 */
public class MenuScreen implements Screen, InputProcessor {

    private SpaceLifeGame game;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private boolean debug = true;

    private StretchViewport viewport;

    private Vector2 startButtonPosition;
    private StartButton startButton;

    private Vector2 helpButtonPosition;
    private HelpButton helpButton;


    public MenuScreen(SpaceLifeGame game){
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);


        viewport = new StretchViewport(ProtectConstants.VIEWPORT_WIDTH, ProtectConstants.VIEWPORT_HEIGHT);

        startButtonPosition = new Vector2(ProtectConstants.VIEWPORT_WIDTH / 3, ProtectConstants.VIEWPORT_HEIGHT / 3);
        startButton = new StartButton(startButtonPosition);

        helpButtonPosition = new Vector2(ProtectConstants.VIEWPORT_WIDTH / 3,
                ProtectConstants.VIEWPORT_HEIGHT / 3 - (ProtectConstants.BUTTON_HEIGHT * 1.5f));
        helpButton = new HelpButton(helpButtonPosition);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        viewport.apply();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(AssetsManager.menubackground, 0, 0,
                ProtectConstants.VIEWPORT_WIDTH / 2, ProtectConstants.VIEWPORT_HEIGHT / 2,
                ProtectConstants.VIEWPORT_WIDTH, ProtectConstants.VIEWPORT_HEIGHT,
                1, 1, 0);
        startButton.draw(batch);
        helpButton.draw(batch);
        batch.end();

        if(debug){
            debugDraw();
        }
    }

    private void debugDraw() {
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        startButton.debugDraw(shapeRenderer);
        helpButton.debugDraw(shapeRenderer);
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

        if(helpButton.checkForTouch(worldClick)){
            game.setHelpScreen();
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
