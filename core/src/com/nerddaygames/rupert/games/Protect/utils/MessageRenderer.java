package com.nerddaygames.rupert.games.Protect.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.nerddaygames.rupert.games.Protect.managers.AssetsManager;

/**
 * Created by robertwaite on 1/10/16.
 */

// draw ui messages
public class MessageRenderer {

    Color orgColor;
    String string;

    boolean show = false; // default: false
    float showTime = 100;
    float showTimer = showTime;
    float fadeOffset = 20;
    float fadeAmount = 0;

    boolean showGameOver = false;
    String gameOverMessage;
    Vector2 gameOverPosition;

    Vector2 energyBalancePosition;

     public MessageRenderer(){
         // bitmapFont = new BitmapFont();
         orgColor = new Color(com.nerddaygames.rupert.games.Protect.managers.AssetsManager.messageBitmapFont.getColor());
         string = new String("Testing");

         gameOverMessage = new String("Earth Destroyed, Click To Restart");
         gameOverPosition = new Vector2(ProtectConstants.VIEWPORT_WIDTH_CENTER, ProtectConstants.VIEWPORT_HEIGHT_CENTER);

         energyBalancePosition = new Vector2(ProtectConstants.VIEWPORT_WIDTH_CENTER, ProtectConstants.GAME_OBJECT_SIZE);
     }


    public void setString(String newString){
        string = newString;
        showTimer = showTime;
        fadeAmount = 0;
        Gdx.app.log("MessageRenderer", "trying to show msg");
        com.nerddaygames.rupert.games.Protect.managers.AssetsManager.messageBitmapFont.setColor(orgColor);
        show = true;
    }


    public void draw(SpriteBatch batch){
        if(showGameOver){
            drawGameOver(batch);
        }

        // TODO move out of draw method
        if(show) {

            // fade in
            if(showTimer + fadeOffset > showTime && fadeAmount < 1){
                com.nerddaygames.rupert.games.Protect.managers.AssetsManager.messageBitmapFont.setColor(orgColor.r, orgColor.g, orgColor.b, fadeAmount);
                fadeAmount += 0.1f;
            }

            // fade out
            if(showTimer <= fadeOffset){
                com.nerddaygames.rupert.games.Protect.managers.AssetsManager.messageBitmapFont.setColor(orgColor.r, orgColor.g, orgColor.b, fadeAmount);
                fadeAmount -= 0.1f;
            }

            // Draw energy balance
            GlyphLayout easyLayout = new GlyphLayout(AssetsManager.bitmapFont, string);
            AssetsManager.bitmapFont.draw(batch,
                    string,
                    energyBalancePosition.x - (easyLayout.width / 2),
                    energyBalancePosition.y,
                    easyLayout.width,
                    Align.center,
                    false
            );

        }
    }



    public void update(float delta) {
        // add timer to show message for brief amount of time
        if(show) {
            if (showTimer > 0) {
                showTimer--;
            }else{
                show = false;
                showTimer = showTime;
                fadeAmount = 1;
            }
        }
    }

    public void setGameOver(){
        showGameOver = true;
    }

    private void drawGameOver(SpriteBatch batch) {
        // Draw energy balance
        GlyphLayout easyLayout = new GlyphLayout(AssetsManager.messageBitmapFont, gameOverMessage);
        AssetsManager.messageBitmapFont.draw(batch,
                gameOverMessage,
                gameOverPosition.x - (easyLayout.width / 2),
                gameOverPosition.y - (easyLayout.height / 2),
                easyLayout.width,
                Align.center,
                false
        );
    }

    public void dispose() {
    }

}
