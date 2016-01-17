package com.nerddaygames.rupert.games.Protect.gameobjects.towers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.nerddaygames.rupert.games.Protect.managers.GameManager;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;

/**
 * Created by robertwaite on 12/27/15.
 */
public class ShooterTower extends Tower{

    GameManager gameManager;

    boolean readyShoot = false;
    float reloadTime = 100;
    float reloadingTime = reloadTime;


    public ShooterTower(GameManager gameManager, Vector2 position) {
        super(position);
        this.gameManager = gameManager;

        init();
    }

    private void init(){
        type = ProtectConstants.TowerTypes.SHOOTER;
        maxHealth = ProtectConstants.SHOOTER_MAX_HEALTH;
        health = maxHealth;
        bulletDamage = 35;
        range = new Circle(position, ProtectConstants.GAME_OBJECT_SIZE * 4f);

    }

    public void update(float delta) {
        super.update(delta);


        if(!readyShoot){
            tickReadyToShoot();
        }
        if(target != null) {
            if (!inRange(target.getCollisionCircle())) {
                target = null;
            }
        }

            if (target != null && !target.isDead()) {
                shoot(delta);

            }


    }

    public void debugDraw(ShapeRenderer shapeRenderer){
        super.debugDraw(shapeRenderer);

        // circle target
        if(target != null && !target.isDead()) {
            if (target.isEnabled()) {
                shapeRenderer.setColor(Color.RED);
                shapeRenderer.circle(target.getPosition().x, target.getPosition().y, (ProtectConstants.GAME_OBJECT_SIZE / 2) + 2);
            }
        }
    }

    private void tickReadyToShoot() {
        reloadingTime--;
        if(reloadingTime <= 0){
            readyShoot = true;
        }
    }



    public void shoot(float delta){

        if(readyShoot) {
            float distance = distanceBetweenTwoPoints(position, target.getPosition());
            targetTime = targetTime - delta;

            if(target.getType() == ProtectConstants.EnemyTypes.BOSS){
                // right side
                if(target.getPosition().x > ProtectConstants.VIEWPORT_WIDTH_CENTER){
                    // upper
                    if(target.getPosition().y > ProtectConstants.VIEWPORT_HEIGHT_CENTER){
                        leadTarget.set(target.getPosition().x - (targetTime * ProtectConstants.GAME_OBJECT_SPEED * distance),
                                target.getPosition().y + (targetTime * ProtectConstants.GAME_OBJECT_SPEED * distance));
                    }else{ // lower
                        leadTarget.set(target.getPosition().x + (targetTime * ProtectConstants.GAME_OBJECT_SPEED * distance),
                                target.getPosition().y + (targetTime * ProtectConstants.GAME_OBJECT_SPEED * distance));
                    }

                }else{// left
                    // upper
                    if(target.getPosition().y > ProtectConstants.VIEWPORT_HEIGHT_CENTER){
                        leadTarget.set(target.getPosition().x - (targetTime * ProtectConstants.GAME_OBJECT_SPEED * distance),
                                target.getPosition().y - (targetTime * ProtectConstants.GAME_OBJECT_SPEED * distance));
                    }else{ // lower
                        leadTarget.set(target.getPosition().x + (targetTime * ProtectConstants.GAME_OBJECT_SPEED * distance),
                                target.getPosition().y - (targetTime * ProtectConstants.GAME_OBJECT_SPEED * distance));
                    }
                }
                // aim ahead of bosss, good enough. calculation should change based on angle.

            }else {
                // aim ahead of target, good enough. calculation should change based on angle.
                leadTarget.set(target.getPosition().x + (targetTime * ProtectConstants.GAME_OBJECT_SPEED),
                        target.getPosition().y + (targetTime * ProtectConstants.GAME_OBJECT_SPEED));
            }
            gameManager.playerShoots(this);
            readyShoot = false;
            reloadingTime = reloadTime;
        }
    }


    private float distanceBetweenTwoPoints(Vector2 vectorA, Vector2 vectorB){
        return (float) Math.sqrt(((vectorA.x - vectorB.x) * (vectorA.x - vectorB.x)) +
                ((vectorA.y * vectorB.y) + (vectorA.y * vectorB.y)));
    }

}
