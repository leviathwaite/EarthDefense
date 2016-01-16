package com.nerddaygames.rupert.games.Protect.gameobjects.factories;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.nerddaygames.rupert.games.Protect.gameobjects.enemies.Enemy;
import com.nerddaygames.rupert.games.Protect.gameobjects.enemies.EnemyObject;
import com.nerddaygames.rupert.games.Protect.gameobjects.enemies.EnemyObject1;
import com.nerddaygames.rupert.games.Protect.gameobjects.enemies.EnemyObject2;
import com.nerddaygames.rupert.games.Protect.gameobjects.enemies.EnemyObject3;
import com.nerddaygames.rupert.games.Protect.gameobjects.enemies.EnemyObject4;
import com.nerddaygames.rupert.games.Protect.gameobjects.enemies.EnemyObjectBoss;
import com.nerddaygames.rupert.games.Protect.managers.GameManager;

import java.util.Random;

/**
 * Created by robertwaite on 12/28/15.
 */
public class EnemyFactory {
    GameManager gameManager;
    SpawnPoints spawnPoints;
    int spawnCounter = 0;
    int spawnCounterLimit = 10;
    float startDelay = 100;
    Random random;

    private int wavesCompleted = 0;
    private int[] waves;
    private int[] wave1 =  {1, 2, 3, 5, 8};
    private int[] wave2 =  {1, 1, 1, 2, 1, 2, 1, 2, 3, 1, 2, 3, 5, 1, 2, 3, 5, 8};
    private int count = 0;

    private boolean waveDead = true;

    Array<EnemyObject> enemyObjects;

    public EnemyFactory(GameManager gameManager, SpawnPoints spawnPoints){
        this.gameManager = gameManager;
        this.spawnPoints = spawnPoints;
        random = new Random();
        enemyObjects = new Array<EnemyObject>();
        waves = wave1;
    }

    public void update(float delta) {

        if (spawnCounter > spawnCounterLimit) {
            spawnCounter = 1;
        }
    }

    // TODO add difficulty, random type and spawnWave
    public Array<EnemyObject> createEnemies(){
        // Gdx.app.log("EnemyFactory", "create enemies");
        waveDead = false;

        // TODO change to 3, 0 for Boss test
        if(wavesCompleted == 3) {
            // spawn boss
            enemyObjects.add(new EnemyObjectBoss(gameManager, spawnPoints.getRandomSpawnPoint(), 0));
        }else{

            // TODO else move to next wave
            if (count < waves.length) {

                for (int i = 0; i < waves[count]; i++) {
                    // 0 and 1 base
                    if (count <= 1) {
                        createEnemy1(spawnPoints.getRandomSpawnPoint());
                    }
                    // 2 and 3 tough
                    if (count > 1 && count < 4) {
                        createEnemy2(spawnPoints.getRandomSpawnPoint());
                    }
                    // 4 and greater tougher or jumper
                    if (count > 4) {
                        if (random.nextBoolean()) {
                            createEnemy3(spawnPoints.getRandomSpawnPoint());
                        } else {
                            createEnemy4(spawnPoints.getRandomSpawnPoint());
                        }
                    }
                    // big wave
                    if (count == 10) {
                        Gdx.app.log("EnemyWave", "Big wave");
                        gameManager.messageBigWave();
                        float pick = random.nextFloat();
                        if (pick <= 0.25f) {
                            createEnemy1(spawnPoints.getRandomSpawnPoint());
                            createEnemy1(spawnPoints.getRandomSpawnPoint());
                            createEnemy1(spawnPoints.getRandomSpawnPoint());

                        }
                        if (pick > 0.25f && pick <= 0.5f) {
                            createEnemy3(spawnPoints.getRandomSpawnPoint());
                            createEnemy4(spawnPoints.getRandomSpawnPoint());
                        }
                        if (pick > 0.5f && pick <= 0.75f) {
                            createEnemy2(spawnPoints.getRandomSpawnPoint());
                            createEnemy2(spawnPoints.getRandomSpawnPoint());
                        }
                        if (pick > 0.75f) {
                            createEnemy1(spawnPoints.getRandomSpawnPoint());
                            createEnemy2(spawnPoints.getRandomSpawnPoint());
                            createEnemy3(spawnPoints.getRandomSpawnPoint());
                            createEnemy4(spawnPoints.getRandomSpawnPoint());
                        }
                    }
                }
                count++;

            } else {
                Gdx.app.log("EnemyFactory", "reset wave");
                count = 0;
                wavesCompleted++;
                waves = wave2;
            }
        }
        return enemyObjects;
    }

    public EnemyObject createBossSpawn(Vector2 spawnPosition){
       return new EnemyObject1(spawnPosition, startDelay * spawnCounter);
    }

    public void setWaveDead(){
        waveDead = true;
    }

    public boolean isWaveDead(){
        return waveDead;
    }

    private void createEnemy1(Vector2 position){
        Gdx.app.log("EnemyFactory", "spawnCounter: " + spawnCounter + ", startDelay: " + startDelay * spawnCounter);
        spawnCounter++;
        enemyObjects.add(new EnemyObject1(position, startDelay * spawnCounter));
    }

    private void createEnemy2(Vector2 position){
        spawnCounter++;
        enemyObjects.add(new EnemyObject1(position, startDelay * spawnCounter));
    }

    private void createEnemy3(Vector2 position){
        spawnCounter++;
        enemyObjects.add(new EnemyObject1(position, startDelay * spawnCounter));
    }

    private void createEnemy4(Vector2 position){
        spawnCounter++;
        enemyObjects.add(new EnemyObject1(position, startDelay * spawnCounter));
    }
}
