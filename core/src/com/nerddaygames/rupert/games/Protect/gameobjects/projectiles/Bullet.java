package com.nerddaygames.rupert.games.Protect.gameobjects.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;

/**
 * Created by robertwaite on 12/27/15.
 */
public class Bullet {

    Vector2 position;
    Vector2 center;
    Vector2 target;
    Vector2 velocity;
    Vector2 size;

    Circle collisionCircle;

    float speed;
    float life;
    boolean dead;
    float hitStrength;

    public Bullet(Vector2 position, Vector2 target, float hitStrength){
        this.position = new Vector2(position.x,
                position.y);
        center = new Vector2(position.x + ProtectConstants.GAME_OBJECT_SIZE / 2,
                position.y + ProtectConstants.GAME_OBJECT_SIZE / 2);
        this.target = new Vector2(target.x,
                target.y);
        this.hitStrength = hitStrength;

        init();
    }

    // TODO set direction at creation and not in update()
    private void init(){
        velocity = new Vector2(target.x - position.x, target.y - position.y);
        velocity.nor();
        size = new Vector2(ProtectConstants.GAME_OBJECT_SIZE, ProtectConstants.GAME_OBJECT_SIZE);
        collisionCircle = new Circle(position.x,
                position.y,
                ProtectConstants.GAME_OBJECT_SIZE / 2);
        speed = 50;
        life = 200;
        dead = false;
        // TODO remove and set in constructor
        hitStrength = 10;
    }

    public void update(float delta){

        if(!dead) {
            velocity = new Vector2(target.x - position.x, target.y - position.y);
            velocity.nor();

            position.x += velocity.x;
            position.y += velocity.y;
        }

        //angle = -Math.toDegrees(Math.atan2(startX - endX, startY - endY)) + 180;

        /*
        float dx = targetX - startX;
        float dy = targetY - startY;
        double h = Math.sqrt(dx * dx + dy * dy);
        float dn = (float)(h / sqrt2);

        Vector bulletHeading = new Vector(dx / dn, dy / dn, 0);
        */

        /*
        velocity.x = target.x - position.x;
        velocity.y = target.y - position.y;
        double heading = Math.sqrt(velocity.x * velocity.x + velocity.y * velocity.y);
        float dn = (float)(heading / (heading * heading));
        velocity.x = velocity.x / dn;
        velocity.y = velocity.y / dn;
        */

        /*
            velocity.set(target.x - position.x, target.y - position.y);
            velocity.nor();
            velocity.x *= speed * delta;
            velocity.y *= speed * delta;

            position.x += velocity.x;
            position.y += velocity.y;
            */

            tickLife();

    }

    public void debugDraw(ShapeRenderer shapeRender){
        shapeRender.set(ShapeRenderer.ShapeType.Line);
        shapeRender.setColor(Color.YELLOW);
        shapeRender.circle(collisionCircle.x, collisionCircle.y, collisionCircle.radius);
    }

    private void tickLife() {
        life--;
        if(life <= 0){
            dead = true;
        }
    }

    public void hitEnemy(){
        // Gdx.app.log("Bullet", "hit enemy");
        dead = true;
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
        collisionCircle.setPosition(position.x,
                position.y);
        return collisionCircle;
    }

    public boolean isDead(){
        return dead;
    }

    public void dispose(){
    }

    public float getHitStrength() {
        return hitStrength;
    }
}
