package com.nerddaygames.rupert.games.Protect.gameobjects.towers;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.nerddaygames.rupert.games.Protect.gameobjects.projectiles.PlayerBullet;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;

/**
 * Created by robertwaite on 12/27/15.
 */
public class BoosterTower extends Tower{

    // tower used to boost player bullets that pass thru it. Takes damage and does not shoot

    public BoosterTower(Vector2 position){
        super(position);
        init();
    }

    private void init(){
        type = ProtectConstants.TowerTypes.BOOSTER;
        range = new Circle(position, ProtectConstants.GAME_OBJECT_SIZE / 2);
        collisionCircle = new Circle(position, ProtectConstants.GAME_OBJECT_SIZE / 2);

        maxHealth = ProtectConstants.BOOSTER_MAX_HEALTH;
        health = maxHealth;
    }

    public void update(float delta){
        super.update(delta);
    }

    public void hitBoosterTower(PlayerBullet playerBullet){
        playerBullet.setBoosted();
    }


}
