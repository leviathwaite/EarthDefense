package com.nerddaygames.rupert.games.Protect.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.nerddaygames.rupert.games.SpaceLifeGame;

/**
 * Created by robertwaite on 12/28/15.
 */
public class WorldRenderer {

    SpaceLifeGame game;
    SpriteBatch batch;
    TextureRegion textureRegion;
    float width, height;

    public WorldRenderer(SpaceLifeGame game, SpriteBatch batch){
        this.game = game;
        this.batch = batch;
        init();
    }

    private void init() {
        width = height = ProtectConstants.GAME_OBJECT_SIZE;
        textureRegion = new TextureRegion();
    }

    // TODO make one draw method for each type, enemy and player etc,

    public void drawEarth(Vector2 position, float rotation){
        batch.draw(com.nerddaygames.rupert.games.Protect.managers.AssetsManager.earth, position.x - width / 2, position.y - height / 2,
                width / 2, height / 2, // originX, originY
                width, height,
                1, 1, rotation); // scaleX, scaleY, rotation
    }

        public void drawShooter (Vector2 position, float rotation){
            // Gdx.app.log("PlayerGameObjects", "drawShooter");
            batch.draw(com.nerddaygames.rupert.games.Protect.managers.AssetsManager.shooter, position.x - width / 2, position.y - height / 2,
                    0, 0, // originX, originY
                    width, height,
                    1, 1, rotation); // scaleX, scaleY, rotation
        }


    public void drawBooster(Vector2 position, float rotation) {
        // Gdx.app.log("PlayerGameObjects", "drawBooster");

        batch.draw(com.nerddaygames.rupert.games.Protect.managers.AssetsManager.booster, position.x - width / 2, position.y - height / 2,
                width / 2, height / 2, // originX, originY
                width, height,
                1, 1, rotation); // scaleX, scaleY, rotation
    }

    public void drawMine(Vector2 position, float rotation) {
        // Gdx.app.log("PlayerGameObjects", "drawMine");

        batch.draw(com.nerddaygames.rupert.games.Protect.managers.AssetsManager.mine, position.x - width / 2, position.y - height / 2,
                width / 2, height / 2, // originX, originY
                width, height,
                1, 1, rotation); // scaleX, scaleY, rotation
    }

    public void drawBlocker(Vector2 position, float rotation) {
        // Gdx.app.log("PlayerGameObjects", "drawBlocker");

        batch.draw(com.nerddaygames.rupert.games.Protect.managers.AssetsManager.blocker, position.x - width / 2, position.y - height / 2,
                width / 2, height / 2, // originX, originY
                width, height,
                1, 1, rotation); // scaleX, scaleY, rotation
    }

    public void drawEnergy(Vector2 position, float rotation) {
        // Gdx.app.log("PlayerGameObjects", "drawEnergy");

        batch.draw(com.nerddaygames.rupert.games.Protect.managers.AssetsManager.energyTower, position.x - width / 2, position.y - height / 2,
                width / 2, height / 2, // originX, originY
                width, height,
                1, 1, rotation); // scaleX, scaleY, rotation
    }

    public void drawMissile(Vector2 position, float rotation) {
        // Gdx.app.log("PlayerGameObjects", "drawBlocker");

        batch.draw(com.nerddaygames.rupert.games.Protect.managers.AssetsManager.missile, position.x - width / 2, position.y - height / 2,
                width / 2, height / 2, // originX, originY
                width, height,
                1, 1, rotation); // scaleX, scaleY, rotation
    }


    public void drawEnemy(Vector2 position, int type, float rotation) {

        switch (type){
            case ProtectConstants.EnemyTypes.BASE:
                textureRegion = com.nerddaygames.rupert.games.Protect.managers.AssetsManager.enemy1;
                break;
            case ProtectConstants.EnemyTypes.TOUGH:
                textureRegion = com.nerddaygames.rupert.games.Protect.managers.AssetsManager.enemy2;
                break;
            case ProtectConstants.EnemyTypes.TOUGHER:
                textureRegion = com.nerddaygames.rupert.games.Protect.managers.AssetsManager.enemy3;
                break;
            case ProtectConstants.EnemyTypes.JUMPER:
                textureRegion = com.nerddaygames.rupert.games.Protect.managers.AssetsManager.enemy4;
                break;
            case ProtectConstants.EnemyTypes.BOSS:
                textureRegion = com.nerddaygames.rupert.games.Protect.managers.AssetsManager.enemyboss;
                break;
        }
        if(type == ProtectConstants.EnemyTypes.BOSS){
            batch.draw(textureRegion, position.x - width, position.y - height,
                    width, height, // originX, originY
                    width * 2, height * 2,
                    1, 1, rotation); // scaleX, scaleY, rotation
        }else {

            batch.draw(textureRegion, position.x - width / 2, position.y - height / 2,
                    width / 2, height / 2, // originX, originY
                    width, height,
                    1, 1, rotation); // scaleX, scaleY, rotation
        }
    }

    public void drawEnemyBullet(Vector2 position){
            batch.draw(com.nerddaygames.rupert.games.Protect.managers.AssetsManager.mine, position.x - ProtectConstants.GAME_OBJECT_SIZE / 2,
                    position.y - ProtectConstants.GAME_OBJECT_SIZE / 2,
                    width / 2, height / 2,
                    width, height,
                    1, 1, 0);


    }

    public void drawPlayerBullet(Vector2 position){
            batch.draw(com.nerddaygames.rupert.games.Protect.managers.AssetsManager.mine, position.x - ProtectConstants.GAME_OBJECT_SIZE / 2,
                    position.y - ProtectConstants.GAME_OBJECT_SIZE / 2,
                    width / 2, height / 2,
                    width, height,
                    1, 1, 0);


    }




    public void drawEnergyPickup(Vector2 position, float rotation, float scale) {
        batch.draw(com.nerddaygames.rupert.games.Protect.managers.AssetsManager.energy, position.x - width / 2, position.y - height / 2,
                width / 2, height / 2, // originX, originY
                width, height,
                scale, scale, rotation); // scaleX, scaleY, rotation
    }

    public void drawMenuBox(Vector2 position, float menuWidth, float menuHeight){
        com.nerddaygames.rupert.games.Protect.managers.AssetsManager.menuBox.draw(batch, position.x, position.y, menuWidth, menuHeight);
    }

    public void drawMenuBoxSelected(Vector2 position, float menuWidth, float menuHeight){
        // TODO probably a better way to do this
        Color orgColor = new Color(com.nerddaygames.rupert.games.Protect.managers.AssetsManager.menuBox.getColor());
        Color color = new Color(com.nerddaygames.rupert.games.Protect.managers.AssetsManager.menuBox.getColor());
        color.a = color.a / 2;
        batch.setColor(color);
        com.nerddaygames.rupert.games.Protect.managers.AssetsManager.menuBox.draw(batch, position.x, position.y, menuWidth, menuHeight);
        batch.setColor(orgColor);
    }


    public void dispose(){
        // TODO
        batch.dispose();
        textureRegion.getTexture().dispose();
    }

}
