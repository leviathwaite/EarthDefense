package com.nerddaygames.rupert.games.Protect.gameobjects.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.nerddaygames.rupert.games.Protect.gameobjects.factories.SpawnPoints;
import com.nerddaygames.rupert.games.Protect.managers.GameManager;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;

import java.util.Random;

/**
 * Created by robertwaite on 12/28/15.
 */
public class EnemyObjectBoss extends EnemyObject {
    // TODO when destroyed go to win screen

    GameManager gameManager;
    Array<Circle> spawnAreas;
    // Circle spawnArea;
    SpawnPoints spawnPoints;
    Random random;

    Circle orbit;
    boolean inOrbit = false;
    float angle = 0;

    float spawnTime = 20;
    float spawnTimer = spawnTime;


    public EnemyObjectBoss(GameManager gameManager, Vector2 position, float startDelay) {
        super(position, startDelay);
        this.gameManager = gameManager;
        type = ProtectConstants.EnemyTypes.BOSS;

        spawnAreas = new Array<Circle>();
        //spawnArea = null;

        health = ProtectConstants.BOSS_MAX_HEALTH;
        hitStrength = ProtectConstants.BOSS_HITSTRENGTH;
        spawnPoints = new SpawnPoints();
        random = new Random();
        position.set(spawnPoints.getBossNextOrbitPoint());

        target.set(spawnPoints.getBossNextOrbitPoint());
        angle = getAngle() * MathUtils.radiansToDegrees;
        speed = ProtectConstants.GAME_OBJECT_SPEED;

        orbit = new Circle(ProtectConstants.VIEWPORT_WIDTH_CENTER,
                ProtectConstants.VIEWPORT_HEIGHT_CENTER,
                ProtectConstants.ORBIT);
    }

    // TODO override update, make boss move into orbit height and then orbit player area while spawning new enemes
    @Override
    public void update(float delta){
        if(!inOrbit) {
            super.update(delta);
            if(checkCollision(orbit)){
                inOrbit = true;
                angle = getAngle() * MathUtils.radiansToDegrees;
                target.set(ProtectConstants.VIEWPORT_WIDTH_CENTER, ProtectConstants.VIEWPORT_HEIGHT_CENTER);
            }

        }
        if(inOrbit) {

            // Gdx.app.log("EnemyObjectBoss", "Angle" + angle);


            position = divideCircle(ProtectConstants.ORBIT, angle);
            angle += 0.25f;
            angle = angle % 360;
            setRotation();

            if(health <= 0){
                dead = true;
            }

            if(spawnAreas.size > 0){
                checkSpawnArea();
            }

            /*
            if(spawnArea != null){
                if(checkCollision(spawnArea)){
                    gameManager.createBossSpawn(new Vector2(spawnArea.x, spawnArea.y));
                    spawnArea = null;
                }
            }
            */
                tickSpawnTimer();


            /*
            // this is degrees per second
   float speed = 10f;
   float rate = 5f;
   float circleX = (float) (Math.cos(drone.getAngle()) *
          (ship.getWidth() / 1.25) + centerX);
   float circleY = (float) (Math.sin(drone.getAngle()) *
          (ship.getHeight() / 1.25) + centerY);
   float angle = drone.getAngle() + (speed * (rate/1000)) % 360;
   if (angle >= 360) {
      angle = 0;
   }
   drone.setAngle(angle);
   drone.setX(circleX);
   drone.setY(circleY);
             */
        }

    }

    private void checkSpawnArea() {
        for(int i = 0; i < spawnAreas.size; i++){
            if(checkCollision(spawnAreas.get(i))) {
                gameManager.createBossSpawn(new Vector2(spawnAreas.get(i).x, spawnAreas.get(i).y));
                spawnAreas.removeIndex(i);
            }
        }
    }

    private void tickSpawnTimer() {
        if(spawnTimer > 0){
            spawnTimer--;
        }else{
            if(spawnAreas.size == 0) {
                if(random.nextBoolean()) {
                    spawnAreas.add(gameManager.getBossSpawnCircle());
                }else{
                    spawnAreas.add(gameManager.getBossSpawnCircle());
                    spawnAreas.add(gameManager.getBossSpawnCircle());
                }
            }
            spawnTimer = spawnTime * random.nextFloat();

        }
    }

    @Override
    public void setRotation(){
        rotation = MathUtils.atan2(target.y - position.y, target.x - position.x);
        // convert to degrees
        rotation = rotation * (180 / MathUtils.PI);
        rotation += 90;
    }

    private float getAngle(){
        double dx = ProtectConstants.VIEWPORT_WIDTH_CENTER - position.x,
                dy = ProtectConstants.VIEWPORT_HEIGHT_CENTER - position.y;
        return (float)Math.atan2(-dy, -dx);
    }

    private Vector2 divideCircle(float radius, float angleDegree){
        float centerX = ProtectConstants.VIEWPORT_WIDTH_CENTER;
        float centerY = ProtectConstants.VIEWPORT_HEIGHT_CENTER;
        angleDegree = angleDegree % 360;
        Vector2 point = new Vector2(centerX + (radius) * MathUtils.cosDeg(angleDegree),
                centerY + (radius) * MathUtils.sinDeg(angleDegree));
        return point;
    }

    public void debugDraw(ShapeRenderer shapeRenderer){
        super.debugDraw(shapeRenderer);
        /*
        for(Circle spawnArea: spawnAreas){
            shapeRenderer.circle(spawnArea.x, spawnArea.y, spawnArea.radius);
        }
        */
    }
}