package com.nerddaygames.rupert.games.Protect.gameobjects.towers;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by robertwaite on 12/27/15.
 */
public abstract class Entity {
    protected Vector2 position;
    protected boolean active;

    public Entity(){
        this.active = false;
    }


    public void update(float delta){

    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setPosition(Vector2 position){
        this.position = position;
    }


    public Vector2 getPosition() {
        return position;
    }
}