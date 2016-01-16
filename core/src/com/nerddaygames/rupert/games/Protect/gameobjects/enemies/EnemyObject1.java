package com.nerddaygames.rupert.games.Protect.gameobjects.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.nerddaygames.rupert.games.Protect.gameobjects.towers.Tower;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;

/**
 * Created by robertwaite on 12/28/15.
 */
public class EnemyObject1 extends EnemyObject {


    public EnemyObject1(Vector2 position, float startDelay) {
        super(position, startDelay);
        type = ProtectConstants.EnemyTypes.BASE;
        health = ProtectConstants.BASE_MAX_HEALTH;
        hitStrength = ProtectConstants.BASE_HITSTRENGTH;
    }
}