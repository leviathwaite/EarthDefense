package com.nerddaygames.rupert.games.Protect.gameobjects.towers;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.nerddaygames.rupert.games.Protect.gameobjects.enemies.Enemy;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;

import java.util.Random;


/**
 * Created by robertwaite on 1/10/16.
 */
public class Energy {
    // TODO energy from no where incase player gets stuck
    Vector2 position;
    Random random;
    Vector2 velocity;

    float spinSpeed;
    float rotation = 0; // TODO make rotate, fade out and pulse. move shorter distance

    Circle collisionCircle;
    boolean dead = false;

    float fadeTime = 150;
    float fadeTimer = fadeTime;
    int value = 25; // TODO move to constants
    private float scale = 1;

    public Energy(Vector2 position){
        this.position = new Vector2(position);
        random = new Random();
        collisionCircle = new Circle(position.x, position.y, ProtectConstants.GAME_OBJECT_SIZE / 2);

        spinSpeed = random.nextFloat();
        if(random.nextBoolean()){
            spinSpeed = -spinSpeed;
        }
        setVelocity();
    }

    private void setVelocity(){

        velocity = new Vector2(random.nextFloat() / 2, random.nextFloat() / 2);
        if(random.nextBoolean()){
            velocity.x = -velocity.x;
        }
        if(random.nextBoolean()){
            velocity.y = -velocity.y;
        }
    }


    public void update(float delta){
        rotation += spinSpeed;

        if(fadeTimer > 0){
            fadeTimer--;
            position.x += velocity.x;
            position.y += velocity.y;
        }

        if(fadeTimer <= 0){
            dead = true;
        }
        scale = fadeTimer / fadeTime;
    }

    public void touched() {
        dead = true;
    }

    public boolean isDead(){
        return dead;
    }

    public boolean checkCollision(Vector2 point){
        collisionCircle.setPosition(position);
        return collisionCircle.contains(point);
    }

    public int getValue(){
        return value;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getRotation() {
        return rotation;
    }


    public float getScale() {
        return scale;
    }
}
