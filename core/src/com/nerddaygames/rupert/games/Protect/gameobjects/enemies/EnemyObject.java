package com.nerddaygames.rupert.games.Protect.gameobjects.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.nerddaygames.rupert.games.Protect.gameobjects.towers.Tower;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;

/**
 * Created by robertwaite on 12/28/15.
 */
public class EnemyObject {

    // TODO setup enemies, and enemy waves
    int type = ProtectConstants.EnemyTypes.NULL;

    Vector2 position;
    Vector2 center;
    Vector2 velocity;
    Vector2 size;

    Vector2 target;

    protected float speed = ProtectConstants.GAME_OBJECT_SPEED;
    protected float rotation;

    protected boolean enabled = true;
    protected boolean dead = false;
    protected boolean moving = true;

    Circle collisionCircle;

    boolean collision = false;

    private float collisionRadius = 10;
    // TODO offset in front of position
    // TODO check row for playerGameObject
    // private float firingRadius = 20;


    protected int resetTime = 100;
    protected int resetEnabledTmer = resetTime;
    protected float hitStrength;
    protected float health;
    protected float startDelay = 0;


    public EnemyObject(Vector2 position, float startDelay){
        this.position = new Vector2(position);
        this.startDelay = startDelay;
        center = new Vector2(position.x - ProtectConstants.GAME_OBJECT_SIZE / 2,
                position.y - ProtectConstants.GAME_OBJECT_SIZE / 2);
        velocity = new Vector2();
        this.size = new Vector2(ProtectConstants.GAME_OBJECT_SIZE, ProtectConstants.GAME_OBJECT_SIZE);

        target = new Vector2(ProtectConstants.VIEWPORT_WIDTH_CENTER, ProtectConstants.VIEWPORT_HEIGHT_CENTER);

        setRotation();

        collisionCircle = new Circle(position.x + ProtectConstants.GAME_OBJECT_SIZE / 2,
                position.y + ProtectConstants.GAME_OBJECT_SIZE / 2, collisionRadius);


        hitStrength = 1; // hit strength
    }

    public void update(float delta){
            if(startDelay > 0){
                speed = 0;
                startDelay--;
            }else{
                speed = ProtectConstants.GAME_OBJECT_SPEED;
            }

            if (health <= 0) {
                dead = true;
            }

            if (moving && !dead) {
                velocity.set(target.x - position.x, target.y - position.y);
                velocity.nor();
                velocity.x *= speed * delta;
                velocity.y *= speed * delta;

                //
                position.add(velocity);


                //if(position.x <= )
            }
            if (!moving && !collision) {
                startMoving();
            }
            // Fix for getting stuck on destroyed towers
            collision = false;
        }


    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    // TODO make setters and have world renderer draw
    public void debugDraw(ShapeRenderer shapeRenderer){
        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.YELLOW);
        // shapeRenderer.circle(position.x, position.y, collisionRadius);
        shapeRenderer.circle(collisionCircle.x, collisionCircle.y, collisionCircle.radius);
    }

    public Vector2 getPosition(){
        return position;
    }

    public Vector2 getCenter(){
        center.set(position.x + ProtectConstants.GAME_OBJECT_SIZE / 2,
                position.y + ProtectConstants.GAME_OBJECT_SIZE / 2);
        return center;
    }

    public Circle getCollisionCircle(){
        collisionCircle.setPosition(position);
        return collisionCircle;
    }

    public void setRotation(){
        rotation = MathUtils.atan2(target.y - position.y, target.x - position.x);
        // convert to degrees
        rotation = rotation * (180 / MathUtils.PI);
        rotation += 90;
    }

    public void setPosition(Vector2 newPosition){
        position = newPosition;
    }

    private void setDead(){
        dead = true;
    }

    private void stopMoving(){
        moving = false;
    }

    private void startMoving(){
        moving = true;
    }

    public boolean checkCollision(Circle other) {
        // update collisionCircle
        collisionCircle.setPosition(position);
        if (collisionCircle.overlaps(other)){
            collision = true;
        }else{
            collision = false;
        }
        return collision;
    }

    public boolean isEnabled(){
        return enabled;
    }

    public boolean isDead(){ return dead;}

    public float getRotation() {
        return rotation;
    }

    public void debugInfo(){
        String tag = new String("EnemyObject");
        Gdx.app.log(tag, "position x: " + position.x + ", y: " + position.y);
        Gdx.app.log(tag, "rotation: " + rotation);
        Gdx.app.log(tag, "enabled: " + isEnabled());
    }

    public void hitByPlayerBullet(float hitStrength){

        Gdx.app.log("EnemyObject", "Hit, health: " + health);
        health -= hitStrength;
    }

    public void hitMineTower() {
        Gdx.app.log("EnemyObject", "hit mine");
        setDead();
    }

    public void hitTower(Tower tower) {
        if(tower.isDead()){
            startMoving();
        }
        if(!tower.isDead() && moving) {
            stopMoving();
            Gdx.app.log("EnemyObject", "hit tower");
        }

    }

    public void hitEarth() {
        setDead();
        Gdx.app.log("EnemyObject", "hit earth");
    }

    public float getHitStrength() {
        return hitStrength;
    }

    public int getType() {
        return type;
    }
}
