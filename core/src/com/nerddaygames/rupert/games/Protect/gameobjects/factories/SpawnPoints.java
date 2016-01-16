package com.nerddaygames.rupert.games.Protect.gameobjects.factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;

import java.util.Random;
import java.util.Vector;

/**
 * Created by robertwaite on 12/28/15.
 */
public class SpawnPoints {
    private Array<Vector2> playerSpawnPoints;
    private Array<Vector2> enemySpawnPoints;
    private Array<Vector2> bossOrbitPoints;
    private Array<Vector2> bossSpawnPoints;

    private Vector2 lastPoint;

    float offset = ProtectConstants.GAME_OBJECT_SIZE / 1.5f;


    int counter = 0;

    public SpawnPoints(){
        lastPoint = new Vector2();

        enemySpawnPoints = new Array<Vector2>();
        setupEnemySpawnPoints();

        playerSpawnPoints = new Array<Vector2>();
        setupPlayerSpawnPoints();

        bossOrbitPoints = new Array<Vector2>();
        setupBossOrbitPoints();

        // use bossOrbitPoints
        bossSpawnPoints = new Array<Vector2>();
        // setupBossSpawnPoints();
    }



    public void debugDraw(ShapeRenderer shapeRenderer){
        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(ProtectConstants.GRID_COLOR);
        // draw rings
        shapeRenderer.circle(ProtectConstants.VIEWPORT_WIDTH_CENTER,
                ProtectConstants.VIEWPORT_HEIGHT_CENTER,
                ProtectConstants.RING1 - offset);
        shapeRenderer.circle(ProtectConstants.VIEWPORT_WIDTH_CENTER,
                ProtectConstants.VIEWPORT_HEIGHT_CENTER,
                ProtectConstants.RING2 - offset);
        shapeRenderer.circle(ProtectConstants.VIEWPORT_WIDTH_CENTER,
                ProtectConstants.VIEWPORT_HEIGHT_CENTER,
                ProtectConstants.RING3 - offset);
        shapeRenderer.circle(ProtectConstants.VIEWPORT_WIDTH_CENTER,
                ProtectConstants.VIEWPORT_HEIGHT_CENTER,
                ProtectConstants.RING3 + offset);

        float angle = 0;
        for(int i = 1; i < ProtectConstants.COLUMNS + 1; i++) {
            // draw grid
            shapeRenderer.line(divideCircle(ProtectConstants.RING1 - ProtectConstants.GAME_OBJECT_SIZE / 2, angle),
                    divideCircle(ProtectConstants.RING3 + ProtectConstants.GAME_OBJECT_SIZE / 2, angle));
            angle += 360 / ProtectConstants.COLUMNS;
        }
    }

    private void setupPlayerSpawnPoints() {
        float angle = 18;
        // TODO nested loop with ProtectConstants.RINGS
        for(int i = 1; i < ProtectConstants.COLUMNS + 1; i++){
            playerSpawnPoints.add(divideCircle(ProtectConstants.RING1, angle));
            playerSpawnPoints.add(divideCircle(ProtectConstants.RING2, angle));
            playerSpawnPoints.add(divideCircle(ProtectConstants.RING3, angle));

            angle += 360 / ProtectConstants.COLUMNS;
        }
    }

    // TODO add some distance variations
    private void setupEnemySpawnPoints() {
        float ring = ProtectConstants.VIEWPORT_WIDTH / 2 + (ProtectConstants.GAME_OBJECT_SIZE * 2);
        float angle = 18; // start angle
        for(int i = 1; i < ProtectConstants.COLUMNS + 1; i++){
            enemySpawnPoints.add(divideCircle(ring, angle));
            angle += 360 / ProtectConstants.COLUMNS;
        }
        enemySpawnPoints.shuffle();
    }

    private void setupBossOrbitPoints(){
        float angle = 18; // start angle
        for(int i = 1; i < ProtectConstants.COLUMNS + 1; i++){
            bossOrbitPoints.add(divideCircle(ProtectConstants.ORBIT, angle));
            angle += 360 / ProtectConstants.COLUMNS;
        }
    }

    public Vector2 getRandomSpawnPoint(){
        Vector2 temp = new Vector2(enemySpawnPoints.random());
        while(temp == lastPoint){
            temp = enemySpawnPoints.random();
        }
        lastPoint = temp;
        return temp;
    }

    public Circle getBossSpawnPoint(){

        return new Circle(bossOrbitPoints.random(), ProtectConstants.GAME_OBJECT_SIZE / 2);
    }


    private Vector2 divideCircle(float radius, float angleDegree){
        float centerX = ProtectConstants.VIEWPORT_WIDTH_CENTER;
        float centerY = ProtectConstants.VIEWPORT_HEIGHT_CENTER;
        angleDegree = angleDegree % 360;
        Vector2 point = new Vector2(centerX + (radius) * MathUtils.cosDeg(angleDegree),
                centerY + (radius) * MathUtils.sinDeg(angleDegree));
        return point;
    }

    public Vector2 getNextPlayerSpawnPoint(){
        Vector2 playerSpawnPoint = new Vector2(playerSpawnPoints.get(counter));
        counter++;
        if(counter > playerSpawnPoints.size){
            Gdx.app.log("SpawnPoints", "Next spawn point does not exist");
            counter = 0;
        }
        return playerSpawnPoint;
    }

    public Vector2 getBossNextOrbitPoint(){
        Vector2 bossOrbitPoint = new Vector2(playerSpawnPoints.get(counter));
        counter++;
        if(counter > bossOrbitPoints.size){
            Gdx.app.log("SpawnPoints", "Next spawn point does not exist");
            counter = 0;
        }
        return bossOrbitPoint;
    }
}
