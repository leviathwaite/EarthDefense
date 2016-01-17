package com.nerddaygames.rupert.games.Protect.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.nerddaygames.rupert.games.Protect.managers.AssetsManager;
import com.nerddaygames.rupert.games.Protect.managers.GameManager;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;
import com.nerddaygames.rupert.games.Protect.utils.StarField;
import com.nerddaygames.rupert.games.SpaceLifeGame;

/**
 * Created by robertwaite on 12/20/15.
 */
public class ProtectGameScreen extends ScreenAdapter implements InputProcessor {

    private SpaceLifeGame game;
    private SpriteBatch batch;

    // debug
    private ShapeRenderer shapeRenderer;
    private boolean debug = true;

    // private ExtendViewport viewport;
    private StretchViewport viewport;

    private GameManager gameManager;

    public ProtectGameScreen(SpaceLifeGame game){
        this.game = game;
    }



    @Override
    public void show() {
        batch = new SpriteBatch();
        viewport = new StretchViewport(ProtectConstants.VIEWPORT_WIDTH, ProtectConstants.VIEWPORT_HEIGHT);

        gameManager = new GameManager(game, batch, viewport);

        // TODO move to debug
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);


        Gdx.input.setInputProcessor(this);
    }



    @Override
    public void render(float delta) {
        viewport.apply();

        if(!gameManager.isPaused()) {
            gameManager.update(delta);
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        gameManager.draw(batch);
        gameManager.drawUI();

        batch.end();

        if(debug){
            debugDraw();
        }

    }

    private void debugDraw() {
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        gameManager.debugDraw(shapeRenderer);
        shapeRenderer.end();
    }


    private void cirlePoint(){
        /*

        as3.x = centerX + radius * cos(angle)
        as3.y = centerY + radius * sin(angle)

        x = Math.cos(alpha*MathUtils.DegreesToRadians)* distance;
        y = Math.sin(alpha* MathUtils.DegreesToRadians )* distance;
        alpha++;

        X = r * cosine(angle)
        Y = r * sine(angle)

        X = xcircle + (r * sine(angle))
        Y = ycircle + (r * cosine(angle))
        */

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }


    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();

        batch.dispose();
        shapeRenderer.dispose();
        gameManager.dispose();
        // TODO move?
        AssetsManager.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {

        switch (keycode){
            // TODO remove
            case Input.Keys.I:
                gameManager.printInfo();
                gameManager.debugInfo();
                break;
            case Input.Keys.P:
                gameManager.togglePause();
                break;
            case Input.Keys.R:
                gameManager.reset();
                break;
            case Input.Keys.S:

                // gameManager.specialEffectTest();
                break;
            case Input.Keys.ESCAPE:
                gameManager.setPause();
                game.setMenuScreen();
                break;
            case Input.Keys.F1:
                gameManager.setPause();
                game.setHelpScreen();

                break;
            default:
                break;
        }
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
        Vector2 worldClick = viewport.unproject(new Vector2(screenX, screenY));
        gameManager.touched(worldClick);

        if(gameManager.getGameOver()){
            gameManager.setGameOver();
            gameManager.reset();
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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
