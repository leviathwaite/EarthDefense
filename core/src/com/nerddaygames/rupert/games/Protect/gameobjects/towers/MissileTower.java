package com.nerddaygames.rupert.games.Protect.gameobjects.towers;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.nerddaygames.rupert.games.Protect.managers.GameManager;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;

/**
 * Created by robertwaite on 12/27/15.
 */
public class MissileTower extends Tower{

    GameManager gameManager;

    boolean readyShoot = false;
    float reloadTime = 10;
    float reloadingTime = reloadTime;


    public MissileTower(GameManager gameManager, Vector2 position){
        super(position);
        this.gameManager = gameManager;
        init();
    }

    private void init(){
        type = ProtectConstants.TowerTypes.MISSILE;
        range = new Circle(position, ProtectConstants.GAME_OBJECT_SIZE * 10);
        collisionCircle = new Circle(position, ProtectConstants.GAME_OBJECT_SIZE);

        maxHealth = ProtectConstants.MISSILE_MAX_HEALTH;

    }

    public void update(float delta) {
        super.update(delta);


        if(!readyShoot){
            tickReadyToShoot();
        }

        // shoot();

    }

    private void tickReadyToShoot() {
        reloadingTime--;
        if(reloadingTime <= 0){
            readyShoot = true;
        }
    }


    public void shoot(){

    }

}
