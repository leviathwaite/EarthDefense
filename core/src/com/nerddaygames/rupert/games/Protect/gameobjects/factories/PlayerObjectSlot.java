package com.nerddaygames.rupert.games.Protect.gameobjects.factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;

/**
 * Created by robertwaite on 12/25/15.
 */
public class PlayerObjectSlot {


    boolean occupied = false;

    Vector2 position;
    float width, height;

    int type = ProtectConstants.TowerTypes.NULL;

    Circle collisionCircle;


    public PlayerObjectSlot(Vector2 position){
        this.position = position;

        width = ProtectConstants.GAME_OBJECT_SIZE;
        height = ProtectConstants.GAME_OBJECT_SIZE;

        collisionCircle = new Circle(position, width / 2);

    }

    public void debugDraw(ShapeRenderer shapeRenderer){

        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.circle(collisionCircle.x, collisionCircle.y, collisionCircle.radius);

    }

    public boolean isOccupied(){
        return occupied;
    }


    public void setOccupied(boolean occupied, int type) {
        this.occupied = occupied;
        this.type = type;
    }

    public Vector2 getPosition(){
        return position;
    }

    public int getType(){
        return type;
    }

    // used to check for touch
    public boolean checkCollision(Vector2 otherPosition){
        if(collisionCircle.contains(otherPosition)){
            Gdx.app.log("PlayerObjectSlot", "slot hit");
            return true;
        }
        return false;
    }

    // used for tower removal
    public boolean checkOverlap(Circle other){
        if(collisionCircle.overlaps(other)){
            return true;
        }
        return false;
    }

    public void dispose(){

    }

}
