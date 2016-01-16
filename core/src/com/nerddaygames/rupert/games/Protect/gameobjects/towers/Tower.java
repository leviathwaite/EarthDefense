package com.nerddaygames.rupert.games.Protect.gameobjects.towers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.nerddaygames.rupert.games.Protect.gameobjects.enemies.EnemyObject;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;

/**
 * Created by robertwaite on 12/27/15.
 */
public class Tower extends Entity {


    protected Vector2 position;
    protected Vector2 center;
    protected float health = 100;

    protected boolean dead = false;

    protected float rotation;

    protected int type;

    protected float bulletDamage = 10;

    protected Circle range;
    protected Circle collisionCircle;
    protected float maxHealth;

    // for shooter tower, for iterating thru array of towers
    protected EnemyObject target;
    protected float targetTime = 0;
    protected Vector2 leadTarget;
    private int cost;

    // for iterater and status tower
    public Tower(){
    }

    public Tower(Vector2 position){
        type = ProtectConstants.TowerTypes.NULL;
        this.position = position;
        center = new Vector2(position.x + ProtectConstants.GAME_OBJECT_SIZE / 2,
                position.y + ProtectConstants.GAME_OBJECT_SIZE / 2);

        range = new Circle(position, ProtectConstants.GAME_OBJECT_SIZE * 2.5f);
        collisionCircle = new Circle(position, ProtectConstants.GAME_OBJECT_SIZE / 2);

        // TODO better way?
        this.target = null;
        leadTarget = new Vector2();

    }
    /*
    // needs 180
     Vector2 target = new Vector2(ProtectConstants.VIEWPORT_WIDTH / 2, ProtectConstants.VIEWPORT_HEIGHT / 2);
        // (y, x)
        rotation = MathUtils.atan2(target.y - position.y, target.x - position.x);
        // convert to degrees
        rotation = rotation * (180 / MathUtils.PI);

     */


    public void update(float delta){
        if(health <= 0){
            setDead();
        }
    }

    private void setDead() {
        dead = true;
    }

    public void debugDraw(ShapeRenderer shapeRenderer) {
        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.circle(range.x, range.y, range.radius);

        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(collisionCircle.x, collisionCircle.y, collisionCircle.radius);


    }


    public int getType(){
        return type;
    }

    public boolean isDead(){
        return dead;
    }

    public Vector2 getPosition(){
        return position;
    }

    public Vector2 getCenter(){
        center.set(position.x + ProtectConstants.GAME_OBJECT_SIZE / 2,
                position.y + ProtectConstants.GAME_OBJECT_SIZE / 2);
        return center;
    }

    public float getHealth(){
        return health;
    }

    public float getRotation(){
        return rotation;
    }

    public Circle getCollisionCircle(){
        collisionCircle.setPosition(position);
        return collisionCircle;
    }

    public boolean inRange(Circle other){
        return range.overlaps(other);
    }

    public boolean checkCollision(Circle other){
        // update collisionCircle
        collisionCircle.setPosition(position);
        return collisionCircle.overlaps(other);
    }

    public boolean checkCollisionPoint(Vector2 other){
        collisionCircle.setPosition(position);
        return collisionCircle.contains(other);
    }

    public void setType(int type){
        this.type = type;
    }

    public void setTarget(EnemyObject closestEnemy, float delta) {
        target = closestEnemy;
        targetTime = delta;
    }

    public Vector2 getTarget(){
        if(!target.isEnabled()){
            return null;
        }
        // TODO aim ahead
        return leadTarget;
    }



    public void destoryed() {
        dead = true;
    }

    public float getBulletDamage() {
        return bulletDamage;
    }


    public void hitByEnemyObject(float hitStrength) {
            health -= hitStrength;

    }

    public void hitByEnemyBullet(float hitStrength) {
        health -= hitStrength;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public void disableTouched() {
        dead = true;
    }

    public int getCost() {
        return cost;
    }
}

    /*
    protected int damage, cost, upgradeCost, sellCost, level;
    protected float attackRange, attackRate, attackTimer, attackCooldown;
    protected Point2D.Float center;
    protected String towerName;

    private ArrayList<Enemy> targets = null;

    public Tower(int damage, float attackRange,
                 float attackRate, int cost, int level, String towerName) {
        this.damage = damage;
        this.attackRange = attackRange;
        this.attackRate = attackRate;
        this.cost = cost;
        this.towerName = towerName;
        this.level = level;
        this.position = Vector2.Zero;
        attackTimer = 0;
        setAttackCooldown(attackRate);
        targets = new ArrayList<Enemy>();
        center = new Point2D.Float();
        upgradeCost = 0;
        sellCost = 0;
    }



    public void update(float delta) {
        super.update(delta);
        List<Enemy> enemies = GameState.getInstance().getEnemies();
        acquireTarget(enemies);
        updateTargets();
        shoot(delta);

    }

    private void acquireTarget(List<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            if (!targets.contains(enemy)) {
                if (intersects(enemy)) {
                    targets.add(enemy);
                }
            }
        }
    }

    private void updateTargets() {

        for (int i = targets.size() - 1; i >= 0; i--) {
            if (!intersects(targets.get(i)) || !targets.get(i).active) {
                targets.remove(i);
            }

        }
    }

    private void shoot(float delta) {
        attackTimer += delta;
        if(targets.size() > 0){
            List<Projectile> projectiles = GameState.getInstance().getProjectiles();
            if(attackTimer >= attackCooldown){
                attackTimer = 0;
                ProjectileType type = Projectile.interpretTypeFromTowerName(towerName);
                Projectile shotProjectile = Projectile.createProjectile(this, type, targets.get(0));
                projectiles.add(shotProjectile);
            }
        }
    }

    public int getDamage() {
        return damage;
    }

    public float getAttackRange() {
        return attackRange;
    }

    public float getAttackRate() {
        return attackRate;
    }

    public float getAttackTimer() {
        return attackTimer;
    }

    public String getTowerName() {
        return towerName;
    }

    public ArrayList<Enemy> getTarget() {
        return targets;
    }

    protected void setAttackCooldown(float attackRate){
        attackCooldown = 1f / attackRate;
    }


    public abstract void upgrade();

	/*
	 * TODO: Increment the level of the tower Use the level of the tower as the index in the array of upgrade/sell costs Set the upgradeCost to
	 * upgradeArray[level] Set the sellCost to sellArray[level]
	 */
/*
    public int getUpgradeCost() {
        return upgradeCost;
    }

    public int getSellCost() {
        return sellCost;
    }

    public int getLevel() {
        return level;
    }

    public Tower clone() {
        Tower t = TowerFactory.createTower(TowerFactory.interpretType(towerName));
        t.active = true;
        return t;
    }

    public boolean intersects(Enemy enemy) {
        float circleDistanceX = (float) Math.abs(center.getX() - enemy.getPosition().x);
        float circleDistanceY = (float) Math.abs(center.getY() - enemy.getPosition().y);

        if (circleDistanceX > Config.tileSize / 2 + attackRange) {
            return false;
        }

        if (circleDistanceY > Config.tileSize / 2 + attackRange) {
            return false;
        }

        if (circleDistanceX <= Config.tileSize / 2) {
            return true;
        }

        if (circleDistanceY <= Config.tileSize / 2) {
            return true;
        }

        float cornerDistance = (circleDistanceX - Config.tileSize / 2) * (circleDistanceX - Config.tileSize / 2) + (circleDistanceY - Config.tileSize / 2) * (circleDistanceY - Config.tileSize / 2);

        return (cornerDistance <= attackRange * attackRange);
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setCenter(float x, float y) {
        center.setLocation(x, y);
    }


}*/