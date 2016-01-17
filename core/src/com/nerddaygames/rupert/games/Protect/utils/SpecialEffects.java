package com.nerddaygames.rupert.games.Protect.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.nerddaygames.rupert.games.Protect.managers.AssetsManager;
import com.nerddaygames.rupert.games.Protect.managers.GameManager;
import com.nerddaygames.rupert.games.SpaceLifeGame;

import java.util.Random;

/**
 * Created by robertwaite on 1/12/16.
 */
public class SpecialEffects {

    GameManager gameManager;
    SpriteBatch batch;
    Random random;

    Vector3 orgPosition;
    Color orgColor;

    float width, height;

    boolean flashYellow = false;
    float yellowFade = 0;
    private float yellowFadeElapsed = 0;


    boolean flashRed = false;
    float redFade = 0;
    private float redFadeElapsed = 0;


    private static final float FADE_IN_TIME = 0.005f;

    boolean shake;
    float currentTime;
    float power;
    float time;

    public SpecialEffects(GameManager gameManager, SpriteBatch batch){
        this.gameManager = gameManager;
        this.batch = batch;
        random = new Random();
        orgPosition = gameManager.viewport.getCamera().position;
        width = ProtectConstants.VIEWPORT_WIDTH;
        height = ProtectConstants.VIEWPORT_HEIGHT;
        orgColor = new Color(batch.getColor());

        shake = false;
        currentTime = 0;
        power = 0;
        time = 0;
    }

    public void update(float delta){
        if(flashYellow) {
            yellowFadeElapsed += delta;
            if(yellowFade == 1){
                flashYellow = false;
            }
        }

        if(flashRed) {
            redFadeElapsed += delta;
            if(redFade == 1){
                flashRed = false;
            }
        }

        if(shake){
            shakeScreen(delta, power, time);
        }
    }

    public void draw(){
        if(flashYellow){
           drawFlashYellow();
        }

        if(flashRed){
            drawFlashRed();
        }
    }

    private void drawFlashRed(){
        redFade = Interpolation.fade.apply(redFadeElapsed / FADE_IN_TIME);
        // Gdx.app.log("SpecialEffects", "redFade: " + redFade);
        batch.setColor(Color.RED.r, Color.RED.g, Color.RED.b, redFade / 2);
        batch.draw(AssetsManager.rect, 0, 0, width, height);
        batch.setColor(orgColor);
    }

    private void drawFlashYellow() {
        yellowFade = Interpolation.fade.apply(yellowFadeElapsed / FADE_IN_TIME);
        //Gdx.app.log("SpecialEffects", "yellowFade: " + yellowFade);
        batch.setColor(Color.YELLOW.r, Color.YELLOW.g, Color.YELLOW.b, yellowFade);
        batch.draw(AssetsManager.rect, 0, height - ProtectConstants.GAME_OBJECT_SIZE, ProtectConstants.MENU_WIDTH, height);
        batch.setColor(orgColor);

    }

    public void setFlashYellow(){

            flashYellow = true;
            if (flashYellow) {
                yellowFadeElapsed = 0;
                yellowFade = 0;

            }

    }

    public void setFlashRed(){
            flashRed = true;
            if (flashRed) {
                redFadeElapsed = 0;
                redFade = 0;
            }

    }

    // TODO fix with better values
    public void shakeScreen(float delta, float power, float time){
        // Gdx.app.log("SpecialEffects", "shakeScreen");
        if(currentTime <= time){
            float currentPower = power * ((time - currentTime) / time);
            float x = (random.nextFloat() - 0.5f) * 2 * currentPower;
            float y = (random.nextFloat() - 0.5f) * 2 * currentPower;
            // Gdx.app.log("SpecialEffects", "shakeScreen x: " + x + ", y: " + y);


            gameManager.viewport.update((int)(Gdx.graphics.getWidth() - x), (int)(Gdx.graphics.getHeight() - y), true);

            // gameManager.viewport.getCamera().translate(-x, -y, orgPosition.z);
            currentTime += delta;
        }else{
            /*
            gameManager.viewport.getCamera().position.x = orgPosition.x;
            gameManager.viewport.getCamera().position.y = orgPosition.y;
            */

            gameManager.viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
            shake = false;
        }
    }

    public void setShake(float power, float time) {
        shake = true;
        currentTime = 0;
        this.power = power;
        this.time = time;
    }
}
