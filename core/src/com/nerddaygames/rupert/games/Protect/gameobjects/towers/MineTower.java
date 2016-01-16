package com.nerddaygames.rupert.games.Protect.gameobjects.towers;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;

/**
 * Created by robertwaite on 12/27/15.
 */
public class MineTower extends Tower{

    // tower destorys self and enemy when collided with, does not take damage and does not shoot

    boolean readyShoot = false; // TODO use if mine has setup time


    public MineTower(Vector2 position){
        super(position);
        init();
    }

    private void init(){
        type = ProtectConstants.TowerTypes.MINE;
        range = new Circle(position, ProtectConstants.GAME_OBJECT_SIZE / 2);
        collisionCircle = new Circle(position, ProtectConstants.GAME_OBJECT_SIZE / 2);

        maxHealth = ProtectConstants.MINE_MAX_HEALTH;
        health = 1;


    }

    public void update(float delta) {
        super.update(delta);

    }
    @Override
    public void hitByEnemyObject(float hitStrength) {
    }
}
