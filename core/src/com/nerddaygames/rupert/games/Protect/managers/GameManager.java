package com.nerddaygames.rupert.games.Protect.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.nerddaygames.rupert.games.Protect.gameobjects.enemies.EnemyObject;
import com.nerddaygames.rupert.games.Protect.gameobjects.factories.EnemyFactory;
import com.nerddaygames.rupert.games.Protect.gameobjects.factories.PlayerObjectSlot;
import com.nerddaygames.rupert.games.Protect.gameobjects.factories.SpawnPoints;
import com.nerddaygames.rupert.games.Protect.gameobjects.factories.TowerFactory;
import com.nerddaygames.rupert.games.Protect.gameobjects.projectiles.EnemyBullet;
import com.nerddaygames.rupert.games.Protect.gameobjects.projectiles.PlayerBullet;
import com.nerddaygames.rupert.games.Protect.gameobjects.towers.Energy;
import com.nerddaygames.rupert.games.Protect.gameobjects.towers.EnergyTower;
import com.nerddaygames.rupert.games.Protect.gameobjects.towers.Tower;
import com.nerddaygames.rupert.games.Protect.selectionwindow.MenuObject;
import com.nerddaygames.rupert.games.Protect.selectionwindow.ObjectMenu;
import com.nerddaygames.rupert.games.Protect.selectionwindow.TowerStatusMenu;
import com.nerddaygames.rupert.games.Protect.gameobjects.Earth;
import com.nerddaygames.rupert.games.Protect.utils.MessageRenderer;
import com.nerddaygames.rupert.games.Protect.utils.ParticleEffectsManager;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;
import com.nerddaygames.rupert.games.Protect.utils.SpecialEffects;
import com.nerddaygames.rupert.games.Protect.utils.StarField;
import com.nerddaygames.rupert.games.Protect.utils.UIRenderer;
import com.nerddaygames.rupert.games.Protect.utils.WorldRenderer;
import com.nerddaygames.rupert.games.SpaceLifeGame;

import java.util.Iterator;

/**
 * Created by robertwaite on 12/28/15.
 */
public class GameManager {

    SpaceLifeGame game;
    SpriteBatch batch;
    public StretchViewport viewport;
    WorldRenderer worldRenderer;
    UIRenderer uiRenderer;
    SpecialEffects specialEffects;
    ParticleEffectsManager particleEffects;
    StarField starField;
    MessageRenderer messageRenderer;

    Array<MenuObject> menuObjects;
    MenuObject selected;
    ObjectMenu menu;

    TowerStatusMenu statusMenu;
    Tower selectedStatusTower;

    SpawnPoints spawnPoints;

    TowerFactory towerFactory;
    EnemyFactory enemyFactory;

    com.nerddaygames.rupert.games.Protect.managers.PlayerWallet playerWallet;

    Array<PlayerObjectSlot> playerSlots;

    Array<Energy> energys;

    Array<Tower> towers;
    Array<EnemyObject>enemiesInRange;

    Array<EnemyObject> enemyObjects;



    // TODO add Earth, animation???
    Earth earth;

    Array<PlayerBullet> playerBullets;
    Array<EnemyBullet> enemyBullets;

    boolean pause = false;
    boolean gameOver = false;
    boolean earthDead = false;

    int gameOverTime = 90;
    int gameOverTimer = gameOverTime;

    float slowMotion = 0.5f;

    boolean bossCreated = false;
    boolean bossDefeated = false;

    public GameManager(SpaceLifeGame game, SpriteBatch batch, StretchViewport viewport){
        this.game = game;
        this.batch = batch;
        this.viewport = viewport;
        init();
    }

    private void init() {
        worldRenderer = new WorldRenderer(game, batch);
        uiRenderer = new UIRenderer(game, batch);
        specialEffects = new SpecialEffects(this, batch);
        particleEffects = new ParticleEffectsManager();
        starField = new StarField();
        messageRenderer = new MessageRenderer();
        spawnPoints = new SpawnPoints();
        towerFactory = new TowerFactory(this);

        enemyFactory = new EnemyFactory(this, spawnPoints);

        playerWallet = new com.nerddaygames.rupert.games.Protect.managers.PlayerWallet();

        playerSlots = new Array<PlayerObjectSlot>();
        setupPlayerSlots();

        energys = new Array<Energy>();

        towers = new Array<Tower>();
        enemiesInRange = new Array<EnemyObject>();

        enemyObjects = new Array<EnemyObject>();

        // TODO make level spawn list
        createEnemyWave();

        playerBullets = new Array<PlayerBullet>();
        enemyBullets = new Array<EnemyBullet>();

        earth = new Earth();

        menu = new ObjectMenu();

        menuObjects = new Array<MenuObject>();
        setupMenuObjects();

        selected = new MenuObject();

        statusMenu = new TowerStatusMenu();
        selectedStatusTower = new Tower();
        selectedStatusTower.setType(ProtectConstants.TowerTypes.NULL);

        playLevelMusic();
    }


    private void setupPlayerSlots() {
        int numberOfSlots = ProtectConstants.COLUMNS * ProtectConstants.RINGS;
        for(int i = 0; i < numberOfSlots; i++){
        playerSlots.add(new PlayerObjectSlot(spawnPoints.getNextPlayerSpawnPoint()));
        }

    }


    public void update(float delta) {
        // iterate thru towers
        Iterator<Tower> towersUpdateIter = towers.iterator();
        while (towersUpdateIter.hasNext()) {
            Tower updateTower = towersUpdateIter.next();
            updateTower.update(delta);
        }

        // iterate thru energys
        Iterator<Energy> energyObjectsIter = energys.iterator();
        while (energyObjectsIter.hasNext()) {
            Energy energyObject = energyObjectsIter.next();

            // remove dead energy objects
            if (energyObject.isDead()) {
                energyObjectsIter.remove();
            }

            energyObject.update(delta);
        }

        if(earthDead || bossDefeated) {
            stopLevelMusic();
            stopBossMusic();
            if (gameOverTimer <= 0) {
                gameOver = true;
            } else {
                gameOverTimer--;
            }
        }

        if(gameOver) {
            if(bossDefeated){
                enemyObjects.clear();
                game.setWinScreen();
            }else {
                messageRenderer.setGameOver();
            }
        }else{
            // game is over, slow down time to let it sink in
            if(gameOverTimer < gameOverTime){
                delta *= slowMotion;
            }
            earth.update(delta);
            particleEffects.update(delta);

            // AssetsManager.explosionBlue.update(delta);

            // TODO create with wave controller/manager
            if (enemyObjects.size == 0) {
                enemyFactory.setWaveDead();
                createEnemyWave();
            }




            // earth.update(delta);

            // iterate thru enemies
            Iterator<EnemyObject> enemyObjectsIter = enemyObjects.iterator();
            while (enemyObjectsIter.hasNext()) {
                EnemyObject enemyObject = enemyObjectsIter.next();

                // remove dead enemies
                if (enemyObject.isDead()) {
                    if (enemyObject.getType() == ProtectConstants.EnemyTypes.BOSS) {
                        playBossDestroyed();
                        blueExplosion(enemyObject.getPosition());
                        bossDefeated = true;
                    }
                    playThud();
                    enemyObjectsIter.remove();
                }

                enemyObject.update(delta);

                // check enemy collision with earth
                if (earth.checkForCollision(enemyObject.getCollisionCircle())) {
                    enemyObject.hitEarth();
                    float power = .5f;
                    float time = .5f;
                    specialEffects.setShake(power, time);
                    specialEffects.setFlashRed();
                    earth.hitByEnemy(enemyObject.getHitStrength());
                    playHitEarth();
                    earthDead = true;
                }

                // if displayed tower destroyed, remove from menu
                if (selectedStatusTower.isDead()) {
                    selectedStatusTower = new Tower();
                    selectedStatusTower.setType(ProtectConstants.TowerTypes.NULL);
                }

                // iterate thru towers
                Iterator<Tower> towersIter = towers.iterator();
                while (towersIter.hasNext()) {
                    Tower tower = towersIter.next();

                    // Remove dead towers
                    if (tower.isDead()) {
                        playTowerDied();
                        // TODO clear playerslot when tower destroyed
                        for (PlayerObjectSlot playerSlot : playerSlots) {
                            if (playerSlot.checkOverlap(tower.getCollisionCircle())) {
                                playerSlot.setOccupied(false, ProtectConstants.TowerTypes.NULL);

                            }
                        }
                        towersIter.remove();

                    }



                    if (enemyObject.checkCollision(tower.getCollisionCircle())) {
                        enemyObject.hitTower(tower);
                        float power = .5f;
                        float time = .5f;
                        specialEffects.setShake(power, time);
                        tower.hitByEnemyObject(enemyObject.getHitStrength());
                    }

                    // ShooterTower
                    if (tower.getType() == ProtectConstants.TowerTypes.SHOOTER) {

                        // Narrow down collisions
                        if (tower.inRange(enemyObject.getCollisionCircle())) {
                            enemiesInRange.add(enemyObject);
                        }

                        EnemyObject closestEnemy = new EnemyObject(new Vector2(-100, -100), 0);
                        if (enemiesInRange.size > 1) {

                            float distance = 2000; // ridiculous distance
                            // check in range for target/closest
                            for (EnemyObject enemyObjectInRange : enemiesInRange) {
                                if (distance < distanceBetweenTwoPoints(tower.getPosition(),
                                        enemyObjectInRange.getPosition())) {
                                    closestEnemy = enemyObjectInRange;
                                }
                            }
                            // pass enemy
                            tower.setTarget(closestEnemy, delta);
                        }
                        if (enemiesInRange.size == 1) {
                            closestEnemy = enemiesInRange.first();
                            tower.setTarget(closestEnemy, delta);
                        }
                        enemiesInRange.clear();
                    }

                    if (tower.getType() == ProtectConstants.TowerTypes.MINE) {
                        if (tower.checkCollision(enemyObject.getCollisionCircle())) {
                            enemyObject.hitMineTower();
                            playBoom();
                            float power = .75f;
                            float time = .75f;
                            specialEffects.setShake(power, time);
                            particleEffects.addEffect(AssetsManager.explosion, enemyObject.getPosition());

                            // find player slot and free it
                            for (PlayerObjectSlot playerSlot : playerSlots) {
                                if (playerSlot.checkOverlap(tower.getCollisionCircle())) {
                                    playerSlot.setOccupied(false, ProtectConstants.TowerTypes.NULL);

                                }
                            }
                            tower.destoryed();
                        }
                    }

                    Iterator<PlayerBullet> playerBulletIter = playerBullets.iterator();
                    while (playerBulletIter.hasNext()) {
                        PlayerBullet playerBullet = playerBulletIter.next();

                        if (playerBullet.isDead()) {
                            playerBulletIter.remove();
                        }

                        playerBullet.update(delta);

                        // Booster Tower collision with playerBullet
                        if (tower.getType() == ProtectConstants.TowerTypes.BOOSTER) {
                            if (tower.checkCollision(playerBullet.getCollisionCircle())) {
                                playerBullet.setBoosted();
                            }
                        }

                        if (enemyObject.checkCollision(playerBullet.getCollisionCircle())) {
                            enemyObject.hitByPlayerBullet(playerBullet.getHitStrength());
                            playerBullet.hitEnemy();
                        }

                    }

                    Iterator<EnemyBullet> enemyBulletIter = enemyBullets.iterator();
                    while (enemyBulletIter.hasNext()) {
                        EnemyBullet enemyBullet = enemyBulletIter.next();

                        if (enemyBullet.isDead()) {
                            enemyBulletIter.remove();
                        }

                        enemyBullet.update(delta);

                        if (tower.checkCollision(enemyBullet.getCollisionCircle())) {
                            // decrement tower health
                            tower.hitByEnemyBullet(enemyBullet.getHitStrength());
                        }

                    }

                }
            }

            specialEffects.update(delta);
            messageRenderer.update(delta);
            enemyFactory.update(delta);
        }
    }



    public void createEnemyWave(){
        if(enemyFactory.isWaveDead()) {
            enemyObjects.clear();
            enemyObjects = enemyFactory.createEnemies();
        }
    }

    public Circle getBossSpawnCircle(){
        Gdx.app.log("GameManager", "get boss spawn location");
        return  new Circle(spawnPoints.getBossSpawnPoint());
    }

    public void createBossSpawn(Vector2 spawnPosition){
        Gdx.app.log("GameManager", "create boss spawn");
        enemyObjects.add(enemyFactory.createBossSpawn(spawnPosition));
    }

    public void drawUI() {
        //Selection Menu
        // TODO need a available type array

        // object selection menu border
        uiRenderer.drawMenuBoxBorder(menu.getPosition());

        for (MenuObject menuObject : menuObjects) {
            switch (menuObject.getType()) {
                case ProtectConstants.TowerTypes.SHOOTER:
                    uiRenderer.drawShooter(menuObject.getPosition(), 0);
                    if(menuObject.getSelected()){
                        uiRenderer.drawMenuBoxSelected(menuObject.getPosition());
                    }

                    uiRenderer.drawMenuBox(menuObject.getPosition());


                    break;
                case ProtectConstants.TowerTypes.BLOCKER:
                    uiRenderer.drawBlocker(menuObject.getPosition(), 0);
                    if(menuObject.getSelected()){
                        uiRenderer.drawMenuBoxSelected(menuObject.getPosition());
                    }

                    uiRenderer.drawMenuBox(menuObject.getPosition());

                    break;
                case ProtectConstants.TowerTypes.BOOSTER:
                    uiRenderer.drawBooster(menuObject.getPosition(), 0);
                    if(menuObject.getSelected()){
                        uiRenderer.drawMenuBoxSelected(menuObject.getPosition());
                    }

                    uiRenderer.drawMenuBox(menuObject.getPosition());

                    break;
                case ProtectConstants.TowerTypes.MINE:
                    uiRenderer.drawMine(menuObject.getPosition(), 0);
                    if(menuObject.getSelected()){
                        uiRenderer.drawMenuBoxSelected(menuObject.getPosition());
                    }

                    uiRenderer.drawMenuBox(menuObject.getPosition());

                    break;
                case ProtectConstants.TowerTypes.ENERGY:
                    uiRenderer.drawEnergy(menuObject.getPosition(), 0);
                    if(menuObject.getSelected()){
                        uiRenderer.drawMenuBoxSelected(menuObject.getPosition());
                    }

                    uiRenderer.drawMenuBox(menuObject.getPosition());

                    break;
                case ProtectConstants.TowerTypes.MISSILE:
                    uiRenderer.drawMissile(menuObject.getPosition(), 0);
                    if(menuObject.getSelected()){
                        uiRenderer.drawMenuBoxSelected(menuObject.getPosition());
                    }

                    uiRenderer.drawMenuBox(menuObject.getPosition());

                    break;
            }
        }
        // TODO status menu upgrade button
        uiRenderer.drawMenuBoxBorder(statusMenu.getPosition());
        if(selectedStatusTower.getType() != ProtectConstants.TowerTypes.NULL){
            // draw health bar
            uiRenderer.drawHealthBar(statusMenu.getHealthBarPosition(), selectedStatusTower.getHealth(),
                    selectedStatusTower.getMaxHealth());
            // draw disable button
            uiRenderer.drawDisableButton(statusMenu.getDisableButtonPosition());

            // draw icon
            switch (selectedStatusTower.getType()){
                case ProtectConstants.TowerTypes.SHOOTER:
                    uiRenderer.drawShooter(statusMenu.getTowerPosition(), 0);
                    uiRenderer.drawMenuBox(statusMenu.getTowerPosition());

                    break;
                case ProtectConstants.TowerTypes.BLOCKER:
                    uiRenderer.drawBlocker(statusMenu.getTowerPosition(), 0);
                    uiRenderer.drawMenuBox(statusMenu.getTowerPosition());

                    break;
                case ProtectConstants.TowerTypes.BOOSTER:
                    uiRenderer.drawBooster(statusMenu.getTowerPosition(), 0);
                    uiRenderer.drawMenuBox(statusMenu.getTowerPosition());

                    break;
                case ProtectConstants.TowerTypes.MINE:
                    uiRenderer.drawMine(statusMenu.getTowerPosition(), 0);
                    uiRenderer.drawMenuBox(statusMenu.getTowerPosition());

                    break;
                case ProtectConstants.TowerTypes.ENERGY:
                    uiRenderer.drawEnergy(statusMenu.getTowerPosition(), 0);
                    uiRenderer.drawMenuBox(statusMenu.getTowerPosition());

                    break;
                case ProtectConstants.TowerTypes.MISSILE:
                    uiRenderer.drawMissile(statusMenu.getTowerPosition(), 0);
                    uiRenderer.drawMenuBox(statusMenu.getTowerPosition());

                    break;
            }
        }
        uiRenderer.drawEnergyBalance(playerWallet.getEnergyBalance());

        messageRenderer.draw(batch);
    }


    private void setupMenuObjects() {
        // TODO add menuObjects from another screen
        menuObjects.add(new MenuObject(ProtectConstants.TowerTypes.SHOOTER, menu.getNextSpot()));
        menuObjects.add(new MenuObject(ProtectConstants.TowerTypes.BLOCKER, menu.getNextSpot()));
        menuObjects.add(new MenuObject(ProtectConstants.TowerTypes.BOOSTER, menu.getNextSpot()));
        menuObjects.add(new MenuObject(ProtectConstants.TowerTypes.MINE, menu.getNextSpot()));
        menuObjects.add(new MenuObject(ProtectConstants.TowerTypes.ENERGY, menu.getNextSpot()));
        // menuObjects.add(new MenuObject(ProtectConstants.TowerTypes.MISSILE, menu.getNextSpot()));
    }


    public void draw(SpriteBatch batch){
        particleEffects.draw(batch);
        if(!earthDead) {
            worldRenderer.drawEarth(earth.getPosition(), earth.getRotation());
        }

        // TODO need a available type array
        for (Tower tower: towers) {
            switch (tower.getType()) {
                case ProtectConstants.TowerTypes.SHOOTER:
                    worldRenderer.drawShooter(tower.getPosition(), tower.getRotation());
                    break;
                case ProtectConstants.TowerTypes.BLOCKER:
                    worldRenderer.drawBlocker(tower.getPosition(), tower.getRotation());
                    break;
                case ProtectConstants.TowerTypes.BOOSTER:
                    worldRenderer.drawBooster(tower.getPosition(), tower.getRotation());
                    break;
                case ProtectConstants.TowerTypes.MINE:
                    worldRenderer.drawMine(tower.getPosition(), tower.getRotation());
                    break;
                case ProtectConstants.TowerTypes.ENERGY:
                    worldRenderer.drawEnergy(tower.getPosition(), tower.getRotation());
                    break;
                case ProtectConstants.TowerTypes.MISSILE:
                    worldRenderer.drawMissile(tower.getPosition(), tower.getRotation());
                    break;

            }
        }
        for(Energy energy: energys){
            worldRenderer.drawEnergyPickup(energy.getPosition(), energy.getRotation(), energy.getScale());
        }

        for (EnemyObject enemyObject: enemyObjects) {
            worldRenderer.drawEnemy(enemyObject.getPosition(), enemyObject.getType(), enemyObject.getRotation());
        }

        for (EnemyBullet enemyBullet: enemyBullets) {
            if(!enemyBullet.isDead()){
                worldRenderer.drawEnemyBullet(enemyBullet.getPosition());
            }
        }

        for (PlayerBullet playerBullet: playerBullets) {
            if(!playerBullet.isDead()){
                worldRenderer.drawPlayerBullet(playerBullet.getPosition());
            }
        }

        specialEffects.draw();
        /*
        AssetsManager.explosionBlue.draw(batch);
        AssetsManager.explosionBlue.start();

        if(AssetsManager.explosionBlue.isComplete()){
            AssetsManager.explosionBlue.reset();
        }
        */

    }

    public void playerShoots(Tower tower){
        // incase target destroyed
        if(tower.getTarget() != null) {
            playShoot();
            float power = .25f;
            float time = .25f;
            specialEffects.setShake(power, time);
             // TODO add Particles
            playerBullets.add(new PlayerBullet(tower.getPosition(),
                    tower.getTarget(), tower.getBulletDamage()));
        }
    }

    public void generateEnergy(EnergyTower energyTower) {
        energys.add(new Energy(energyTower.getPosition()));
        // Gdx.app.log("GameManager", "generateEnergy Called");
    }

    public void dispose(){
        // TODO dispoe of arrays???
        worldRenderer.dispose();
        uiRenderer.dispose();
        batch.dispose();
        messageRenderer.dispose();
    }

    public void printInfo() {
        for (EnemyObject enemyObject: enemyObjects) {
            enemyObject.debugInfo();
        }

        for (EnemyBullet enemyBullet: enemyBullets) {
           // enemyBullet.debugInfo();
        }

        for (PlayerBullet playerBullet: playerBullets) {
            // playerBullet.debugInfo();
        }
    }

    public void reset() {
        init();
    }

    public void touched(Vector2 worldClick) {
        if(!pause) {
            // check energy pickup for touches
            for (Energy energy : energys) {
                if (energy.checkCollision(worldClick)) {
                    energy.touched();
                    playCollectEnergy();
                    // TODO add particle effect
                    specialEffects.setFlashYellow();
                    playerWallet.addEnergy(energy.getValue());
                }
            }


            // TODO add selecting towers for upgrade/repair
            if (statusMenu.checkDisableButtonCollision(worldClick)) {
                // disable button touched
                // find player slot and free it
                for (PlayerObjectSlot playerSlot : playerSlots) {
                    if (playerSlot.checkOverlap(selectedStatusTower.getCollisionCircle())) {
                        playerWallet.addEnergy(selected.getCost(selectedStatusTower.getType()) / 2);
                        playerSlot.setOccupied(false, ProtectConstants.TowerTypes.NULL);

                    }
                }
                selectedStatusTower.disableTouched();
            }

            for (PlayerObjectSlot playerSlot : playerSlots) {
                if (playerSlot.checkCollision(worldClick)) {
                    if (playerSlot.isOccupied()) {
                        // show tower stats
                        for (Tower tower : towers) {
                            if (tower.checkCollisionPoint(worldClick)) {
                                selectedStatusTower = tower;
                            }
                        }
                    } else {
                        if (selected.getSelected()) {
                            // place selected
                            playerWallet.makePurchase(selected.getCost());
                            towers.add(towerFactory.createTower(selected.getType(), playerSlot.getPosition()));
                            playerSlot.setOccupied(true, selected.getType());
                            // TODO
                            selected.setSelected(false);
                        }
                    }
                }
            }

            // ui object selection
            // TODO check energy
            for (MenuObject menuObject : menuObjects) {
                if (menuObject.checkForTouch(worldClick)) {
                    if (playerWallet.getEnergyBalance() >= menuObject.getCost()) {
                        selected.setSelected(false);
                        menuObject.setSelected(true);
                        selected = menuObject;
                        playButton();
                    } else {
                        messageRenderer.setString("Not Enough Energy");
                        selected.setSelected(false);
                        menuObject.setSelected(false);
                        playNoEnergy();
                    }


                }
            }
        }
    }

    public void debugDraw(ShapeRenderer shapeRenderer) {
        starField.draw(shapeRenderer);

        // TODO move out of debugDraw()
        spawnPoints.debugDraw(shapeRenderer);

        shapeRenderer.setColor(Color.WHITE);

        earth.debugDraw(shapeRenderer);

        for(Tower tower: towers){
            tower.debugDraw(shapeRenderer);
        }

        shapeRenderer.setColor(Color.WHITE);


        for(PlayerObjectSlot playerSlot: playerSlots){
            playerSlot.debugDraw(shapeRenderer);
        }

        for(EnemyObject enemyObject: enemyObjects){
            enemyObject.debugDraw(shapeRenderer);
        }

        for(PlayerBullet playerBullet: playerBullets){
            playerBullet.debugDraw(shapeRenderer);
        }
    }

    public void debugInfo(){

    }

    // TODO needs to be sabs
    private float distanceBetweenTwoPoints(Vector2 vectorA, Vector2 vectorB){
        return (float) Math.sqrt(((vectorA.x - vectorB.x) * (vectorA.x - vectorB.x)) +
                ((vectorA.y * vectorB.y) + (vectorA.y * vectorB.y)));
    }

    // TODO remove this and s key method from game screen
    public void specialEffectTest() {
        //TODO remove money cheat
        playerWallet.addEnergy(10000);
        /*
        float power = .5f;
        float time = .5f;
        specialEffects.setShake(power, time);
        */
        playBossIsComing();
    }

    public void messageBigWave() {
        messageRenderer.setString("Big Wave Coming!");
    }

    public void playBoom(){
        /*
        long id =  AssetsManager.boom.play();
        AssetsManager.bossIsComing.setPitch(id, 0.4f);
        */
        AssetsManager.boom.play();
    }

    public void playBossIsComing(){
        long id =  AssetsManager.bossIsComing.play();
        AssetsManager.bossIsComing.setPitch(id, 0.4f);
    }

    public void playButton(){
        AssetsManager.button.play();
    }

    public void playNoEnergy(){
        AssetsManager.noenergy.play();
    }

    public void playSpawn(){
        AssetsManager.spawn.play();
    }

    public void playSpawn2(){
        AssetsManager.spawn2.play();
    }

    public void playCollectEnergy(){
        AssetsManager.collectenergy.play();
    }

    public void playShoot(){
        AssetsManager.shoot.play();
    }

    public void playHitEarth(){
        AssetsManager.hitearth.play();
    }

    public void playThud(){
        AssetsManager.thud.play();
    }

    private void playTowerDied() {
    }

    private void playBossDestroyed(){

    }

    public void playLevelMusic(){
        AssetsManager.levelMusic.setLooping(true);
        AssetsManager.levelMusic.play();
    }

    public void stopLevelMusic(){
            AssetsManager.levelMusic.stop();
    }

    public void playBossMusic(){
        stopLevelMusic();
        AssetsManager.bossMusic.setLooping(true);
        AssetsManager.bossMusic.play();
    }

    public void stopBossMusic(){
        if(AssetsManager.bossMusic.isPlaying()) {
            AssetsManager.bossMusic.stop();
        }
    }

    public boolean getGameOver(){
        return gameOver;
    }

    public void setGameOver(){
        gameOver = false;
        gameOverTimer = gameOverTime;
        earthDead = false;
    }

    public void blueExplosion(Vector2 position){
        particleEffects.addEffect(AssetsManager.explosionBlue, position);
    }

    // TODO sounds need moved to a manager, long sound effects will continue to play whe pause pressed
    public void togglePause() {
        pause = !pause;
        if(pause) {
            stopLevelMusic();
            if(bossCreated) {
                stopBossMusic();
            }
        }else{
            if(bossCreated){
                playBossMusic();
            }else {
                playLevelMusic();
            }
        }
    }

    public void setPause(){
        pause = true;
        if(pause) {
            stopLevelMusic();
            if(bossCreated) {
                stopBossMusic();
            }
        }else{
            if(bossCreated){
                playBossMusic();
            }else {
                playLevelMusic();
            }
        }
    }

    public boolean isPaused(){
        return pause;
    }

    public void setBossCreated(){
        bossCreated = true;
    }
}
