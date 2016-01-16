package com.nerddaygames.rupert.games.Protect.gameobjects.enemies;

import com.badlogic.gdx.math.Vector2;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;

/**
 * Created by robertwaite on 12/28/15.
 */
public class EnemyObject4 extends EnemyObject {


    public EnemyObject4(Vector2 position, float startDelay) {
        super(position, startDelay);
        type = ProtectConstants.EnemyTypes.JUMPER;
        health = ProtectConstants.JUMPER_MAX_HEALTH;
        hitStrength = ProtectConstants.JUMPER_HITSTRENGTH;
    }
}