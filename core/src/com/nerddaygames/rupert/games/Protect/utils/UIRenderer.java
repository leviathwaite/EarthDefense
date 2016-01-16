package com.nerddaygames.rupert.games.Protect.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.nerddaygames.rupert.games.SpaceLifeGame;

/**
 * Created by robertwaite on 12/29/15.
 */
public class UIRenderer {

    SpaceLifeGame game;
    SpriteBatch batch;

    float width, height;

    // TODO fix assets manaager and get rid of game parameter
    public UIRenderer(SpaceLifeGame game, SpriteBatch batch){
        this.game = game;
        this.batch = batch;
        init();
    }

    private void init() {
        width = height = ProtectConstants.MENU_OBJECT_MIN_SIZE;
    }

    public void drawShooter (Vector2 position,float rotation){
        // Gdx.app.log("PlayerGameObjects", "drawShooter");
        batch.draw(com.nerddaygames.rupert.games.Protect.managers.AssetsManager.shooter, position.x, position.y,
                width / 2, height / 2, // originX, originY
                width, height,
                1, 1, rotation); // scaleX, scaleY, rotation

        // draw price
        GlyphLayout easyLayout = new GlyphLayout(com.nerddaygames.rupert.games.Protect.managers.AssetsManager.bitmapFont, ProtectConstants.SHOOTER_COST_STRING);

        com.nerddaygames.rupert.games.Protect.managers.AssetsManager.bitmapFont.draw(batch,
                ProtectConstants.SHOOTER_COST_STRING,
                position.x + (ProtectConstants.MENU_TEXT_OFFSET_X - easyLayout.width / 2),
                position.y + ProtectConstants.MENU_TEXT_OFFSET_Y,
                easyLayout.width,
                Align.center,
                false
        );
    }


    public void drawBooster(Vector2 position, float rotation) {
        // Gdx.app.log("PlayerGameObjects", "drawBooster");

        batch.draw(game.assetsManager.booster, position.x, position.y,
                width / 2, height / 2, // originX, originY
                width, height,
                1, 1, rotation); // scaleX, scaleY, rotation

        // draw price
        GlyphLayout easyLayout = new GlyphLayout(com.nerddaygames.rupert.games.Protect.managers.AssetsManager.bitmapFont, ProtectConstants.BOOSTER_COST_STRING);

        com.nerddaygames.rupert.games.Protect.managers.AssetsManager.bitmapFont.draw(batch,
                ProtectConstants.BOOSTER_COST_STRING,
                position.x + (ProtectConstants.MENU_TEXT_OFFSET_X - easyLayout.width / 2),
                position.y + ProtectConstants.MENU_TEXT_OFFSET_Y,
                easyLayout.width,
                Align.center,
                false
        );
    }

    public void drawMine(Vector2 position, float rotation) {
        // Gdx.app.log("PlayerGameObjects", "drawMine");

        batch.draw(game.assetsManager.mine, position.x, position.y,
                width / 2, height / 2, // originX, originY
                width, height,
                1, 1, rotation); // scaleX, scaleY, rotation

        // draw price
        GlyphLayout easyLayout = new GlyphLayout(com.nerddaygames.rupert.games.Protect.managers.AssetsManager.bitmapFont, ProtectConstants.MINE_COST_STRING);

        com.nerddaygames.rupert.games.Protect.managers.AssetsManager.bitmapFont.draw(batch,
                ProtectConstants.MINE_COST_STRING,
                position.x + (ProtectConstants.MENU_TEXT_OFFSET_X - easyLayout.width / 2),
                position.y + ProtectConstants.MENU_TEXT_OFFSET_Y,
                easyLayout.width,
                Align.center,
                false
        );
    }

    public void drawBlocker(Vector2 position, float rotation) {
        // Gdx.app.log("PlayerGameObjects", "drawBlocker");

        batch.draw(game.assetsManager.blocker, position.x, position.y,
                width / 2, height / 2, // originX, originY
                width, height,
                1, 1, rotation); // scaleX, scaleY, rotation

        // draw price
        GlyphLayout easyLayout = new GlyphLayout(com.nerddaygames.rupert.games.Protect.managers.AssetsManager.bitmapFont, ProtectConstants.BLOCKER_COST_STRING);

        com.nerddaygames.rupert.games.Protect.managers.AssetsManager.bitmapFont.draw(batch,
                ProtectConstants.BLOCKER_COST_STRING,
                position.x + (ProtectConstants.MENU_TEXT_OFFSET_X - easyLayout.width / 2),
                position.y + ProtectConstants.MENU_TEXT_OFFSET_Y,
                easyLayout.width,
                Align.center,
                false
        );
    }


    public void drawEnergy(Vector2 position, float rotation) {
        // Gdx.app.log("PlayerGameObjects", "drawBlocker");

        batch.draw(game.assetsManager.energyTower, position.x, position.y,
                width / 2, height / 2, // originX, originY
                width, height,
                1, 1, rotation); // scaleX, scaleY, rotation

        // draw price
        GlyphLayout easyLayout = new GlyphLayout(com.nerddaygames.rupert.games.Protect.managers.AssetsManager.bitmapFont, ProtectConstants.ENERGY_COST_STRING);

        com.nerddaygames.rupert.games.Protect.managers.AssetsManager.bitmapFont.draw(batch,
                ProtectConstants.ENERGY_COST_STRING,
                position.x + (ProtectConstants.MENU_TEXT_OFFSET_X - easyLayout.width / 2),
                position.y + ProtectConstants.MENU_TEXT_OFFSET_Y,
                easyLayout.width,
                Align.center,
                false
        );
    }

    public void drawMissile(Vector2 position, float rotation) {
        // Gdx.app.log("PlayerGameObjects", "drawBlocker");

        batch.draw(game.assetsManager.missile, position.x, position.y,
                width / 2, height / 2, // originX, originY
                width, height,
                1, 1, rotation); // scaleX, scaleY, rotation

        // draw price
        GlyphLayout easyLayout = new GlyphLayout(com.nerddaygames.rupert.games.Protect.managers.AssetsManager.bitmapFont, ProtectConstants.MISSILE_COST_STRING);

        com.nerddaygames.rupert.games.Protect.managers.AssetsManager.bitmapFont.draw(batch,
                ProtectConstants.MISSILE_COST_STRING,
                position.x + (ProtectConstants.MENU_TEXT_OFFSET_X - easyLayout.width / 2),
                position.y + ProtectConstants.MENU_TEXT_OFFSET_Y,
                easyLayout.width,
                Align.center,
                false
        );
    }

    public void drawMenuBox(Vector2 position){
        com.nerddaygames.rupert.games.Protect.managers.AssetsManager.menuBox.draw(batch, position.x, position.y,
                ProtectConstants.MENU_OBJECT_MIN_SIZE, ProtectConstants.MENU_OBJECT_MIN_SIZE);

    }

    public void drawMenuBoxBorder(Vector2 position){
        com.nerddaygames.rupert.games.Protect.managers.AssetsManager.menuBox.draw(batch, position.x, position.y,
                ProtectConstants.MENU_WIDTH, ProtectConstants.MENU_HEIGHT);
    }

    public void drawMenuBoxSelected(Vector2 position){
        // TODO probably a better way to do this
        Color orgColor = new Color(com.nerddaygames.rupert.games.Protect.managers.AssetsManager.menuBoxFilled.getColor());
        Color color = new Color(com.nerddaygames.rupert.games.Protect.managers.AssetsManager.menuBoxFilled.getColor());
        color.a = color.a / 2;
        batch.setColor(color);
        com.nerddaygames.rupert.games.Protect.managers.AssetsManager.menuBoxFilled.draw(batch, position.x, position.y,
                ProtectConstants.MENU_OBJECT_MIN_SIZE, ProtectConstants.MENU_OBJECT_MIN_SIZE);
        batch.setColor(orgColor);
    }

    public void drawHealthBar(Vector2 position, float health, float maxHealth){
        float currentWidth = (health / maxHealth) * ProtectConstants.HEALTHBAR_WIDTH;
        // don't draw if less than 9patch width, moves left.
        if(currentWidth >= com.nerddaygames.rupert.games.Protect.managers.AssetsManager.circlePatch.getTotalWidth()) {
            com.nerddaygames.rupert.games.Protect.managers.AssetsManager.circlePatch.setColor(Color.LIME);

            if (health <= (maxHealth / 2)) {
                com.nerddaygames.rupert.games.Protect.managers.AssetsManager.circlePatch.setColor(Color.YELLOW);
            }
            if (health <= (maxHealth / 4)) {
                com.nerddaygames.rupert.games.Protect.managers.AssetsManager.circlePatch.setColor(Color.RED);
            }

            com.nerddaygames.rupert.games.Protect.managers.AssetsManager.circlePatch.draw(batch, position.x, position.y,
                    currentWidth,
                    ProtectConstants.HEALTHBAR_HEIGHT);
        }
        // Gdx.app.log("UIRenderer", "MaxHealth: " + maxHealth + ", health: " + health);

    }

    public void drawDisableButton(Vector2 position){
        batch.draw(game.assetsManager.disable, position.x, position.y,
                ProtectConstants.MENU_OBJECT_MIN_SIZE, ProtectConstants.MENU_OBJECT_MIN_SIZE);
    }

    // TODO find a good spot
    public void drawEnergyBalance(int amount){

        // draw price
        GlyphLayout easyLayout = new GlyphLayout(com.nerddaygames.rupert.games.Protect.managers.AssetsManager.bitmapFont, ProtectConstants.MISSILE_COST_STRING);

        com.nerddaygames.rupert.games.Protect.managers.AssetsManager.bitmapFont.draw(batch,
                String.valueOf(amount),
                ProtectConstants.BALANCE_POSITION.x - (easyLayout.width / 2),
                ProtectConstants.BALANCE_POSITION.y,
                easyLayout.width,
                Align.center,
                false
        );
    }

    public void dispose(){
        // TODO
        batch.dispose();
    }

}


