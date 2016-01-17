package com.nerddaygames.rupert.games.Protect.gameobjects.towers;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.nerddaygames.rupert.games.Protect.managers.GameManager;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;

import java.util.Random;

/**
 * Created by robertwaite on 1/10/16.
 */
public class EnergyTower extends Tower{

    Circle range;
    Vector2 position;
    GameManager gameManager;
    Random random;

    boolean readyToGenerate = false;
    float reloadTime = 200;
    float reloadingTime = reloadTime;

    public EnergyTower(GameManager gameManager, Vector2 position){
        super(position);
        range = new Circle(position, ProtectConstants.GAME_OBJECT_SIZE / 2);
        collisionCircle = new Circle(position, ProtectConstants.GAME_OBJECT_SIZE / 2);
        this.gameManager = gameManager;
        random = new Random();

        init();
    }

    private void init(){
        type = ProtectConstants.TowerTypes.ENERGY;

        maxHealth = ProtectConstants.ENERGY_MAX_HEALTH;
        health = maxHealth;


    }

    public void update(float delta) {
        super.update(delta);


        if(!readyToGenerate){
            tickReadyToGenerate();
        }else{
            generateEnergy();
        }


    }

    public void debugDraw(ShapeRenderer shapeRenderer){
        super.debugDraw(shapeRenderer);

    }

    private void tickReadyToGenerate() {
        reloadingTime--;
        if(reloadingTime <= 0){
            readyToGenerate = true;
        }
    }



    // TODO make generation a little random
    public void generateEnergy(){
        if(readyToGenerate) {
            // TODO base gearation off time
            gameManager.generateEnergy(this);
            readyToGenerate = false;
            reloadingTime = reloadTime * random.nextFloat();
        }
    }




}
