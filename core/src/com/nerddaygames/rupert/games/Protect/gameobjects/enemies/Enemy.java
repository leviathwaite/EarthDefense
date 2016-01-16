package com.nerddaygames.rupert.games.Protect.gameobjects.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;

/**
 * Created by robertwaite on 12/23/15.
 */
public class Enemy {

    private Vector2 position;
    private Vector2 center;
    private Vector2 velocity;
    private Vector2 target;
    private float speed = 50;
    private float width, height, rotation;

    private boolean enabled = true;

    protected float collisionRadius = ProtectConstants.GAME_OBJECT_SIZE / 2;
    // TODO offset in front of position
    // TODO check row for playerGameObject
    private float firingRadius = 20;

    private int resetTime = 100;
    private int resetEnabledTmer = resetTime;


    // TODO move to assetmanager and gameRenderer
    TextureRegion textureRegion;

    public Enemy(Vector2 spawnPoint){
        position = new Vector2(spawnPoint);
        center = new Vector2(position.x + ProtectConstants.GAME_OBJECT_SIZE / 2,
                position.y + ProtectConstants.GAME_OBJECT_SIZE / 2);
        velocity = new Vector2(0, 0);
        target = new Vector2(ProtectConstants.VIEWPORT_WIDTH / 2, ProtectConstants.VIEWPORT_HEIGHT / 2);
        setRotation();


        textureRegion = new TextureRegion(new Texture("enemy_invader1.png"));
        width = ProtectConstants.GAME_OBJECT_SIZE;
        height = ProtectConstants.GAME_OBJECT_SIZE;
    }

    public void resetEnemy(Vector2 newPosition){
        setPosition(newPosition);
    }

    public void update(float delta){
        // move towards target
        // vel = normalize(target - position)* speed;
        /*
        velocity.set(target.x - position.x, target.y - position.y).nor().scl(Math.min(position.dst(target.x, target.y), speed));
        position.add(velocity);
        */
        if(enabled) {
            velocity.set(target.x - position.x, target.y - position.y);
            velocity.nor();
            velocity.x *= speed * delta;
            velocity.y *= speed * delta;

            //
            position.add(velocity);

            //if(position.x <= )
        }

        if(!enabled){
            resetEnabledTmer--;
            if(resetEnabledTmer <= 0){
                resetEnabledTmer = resetTime;
                setEnabled();
            }
        }

    }



    public void draw(SpriteBatch batch){
        if(enabled) {
            batch.draw(textureRegion, position.x - width / 2, position.y - height / 2,
                    width / 2, height / 2, // originX, originY
                    width, height,
                    1, 1, rotation); // scaleX, scaleY, rotation
        }
    }

    public boolean checkForCollision(float x, float y){
       if(position.x - x < collisionRadius && position.y - y < collisionRadius){
            return true;
        }
        return false;
    }

    public void hitEarth(){
        enabled = false;

    }

    public boolean isEnabled(){
        return enabled;
    }

    public void setEnabled(){
        enabled = true;
        setRotation();
    }

    public void setRotation(){
        rotation = MathUtils.atan2(target.y - position.y, target.x - position.x);
        // convert to degrees
        rotation = rotation * (180 / MathUtils.PI);
        rotation += 45;
    }

    public void setPosition(Vector2 newPosition){
        position = newPosition;
    }

    public void dispose(){
        textureRegion.getTexture().dispose();
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getCenter(){
        return center;
    }
}
