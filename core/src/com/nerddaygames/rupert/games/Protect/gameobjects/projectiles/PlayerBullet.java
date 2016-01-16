package com.nerddaygames.rupert.games.Protect.gameobjects.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;

/**
 * Created by robertwaite on 12/28/15.
 */
public class PlayerBullet extends Bullet{

    boolean boosted = false;
    float hitStrength;

    public PlayerBullet(Vector2 position, Vector2 target, float hitStrength) {
        super(position, target, hitStrength);
        this.hitStrength = hitStrength;
    }

    public void setBoosted() {
        Gdx.app.log("PlayeBullet", "before boosted: " + hitStrength);
        if(!boosted) {
            hitStrength *= ProtectConstants.BOOSTERTOWER_MULTIPLIER;
            Gdx.app.log("PlayeBullet", "after boosted: " + hitStrength);
            boosted = true;
        }
    }
}
