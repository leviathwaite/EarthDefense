package com.nerddaygames.rupert.games.Protect.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;

/**
 * Created by robertwaite on 12/24/15.
 */
public class Earth {
    private Vector2 position;
    private Circle collisionCircle;
    private float rotation = 0;

    private boolean hit = false;
    private int timer = 20;
    private int hitTimer = timer; // time to reset hit
    private int health = 100;

    public Earth() {
        position = new Vector2(ProtectConstants.VIEWPORT_WIDTH_CENTER, ProtectConstants.VIEWPORT_HEIGHT_CENTER);
        collisionCircle = new Circle(position, ProtectConstants.GAME_OBJECT_SIZE / 2);
    }



    public void debugDraw(ShapeRenderer shapeRenderer){
        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.circle(collisionCircle.x, collisionCircle.y, collisionCircle.radius);
    }

    public boolean checkForCollision(Circle other){
        collisionCircle.setPosition(collisionCircle.x, collisionCircle.y);
       return collisionCircle.overlaps(other);
    }

    public Circle getCollisionCircle() {
        collisionCircle.setPosition(collisionCircle.x, collisionCircle.y);
        return collisionCircle;
    }

    public Vector2 getPosition(){
        return position;
    }

    public void update(float delta) {
        rotation += 0.25f;
        // TODO game over when health <= 0

    }

    public void dispose() {
    }

    public float getRotation() {
        return rotation;
    }

    public void hitByEnemy(float hitStrength) {
        health -= hitStrength;
    }
}
