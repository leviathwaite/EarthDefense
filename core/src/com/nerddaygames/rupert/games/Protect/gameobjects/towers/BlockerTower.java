package com.nerddaygames.rupert.games.Protect.gameobjects.towers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;

/**
 * Created by robertwaite on 12/27/15.
 */
public class BlockerTower extends Tower{


    Circle range;
    Circle collisionCircle;

    float damage = 10;

    boolean readyShoot = false;
    float reloadTime = 10;
    float reloadingTime = reloadTime;


    public BlockerTower(Vector2 position){
        super(position);
        init();
    }

    private void init(){
        maxHealth = ProtectConstants.BLOCKER_MAX_HEALTH;
        health = maxHealth; // TODO make 1/2 of max for upgrade to full maxHealth
        type = ProtectConstants.TowerTypes.BLOCKER;
        range = new Circle(position, ProtectConstants.GAME_OBJECT_SIZE * 10);
        collisionCircle = new Circle(position, ProtectConstants.GAME_OBJECT_SIZE);


        Gdx.app.log("BlockerTower", "maxHealth: " + maxHealth + ", Constant: " + ProtectConstants.BLOCKER_MAX_HEALTH);

    }

}
