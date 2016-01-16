package com.nerddaygames.rupert.games.Protect.utils;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.nerddaygames.rupert.games.Protect.managers.AssetsManager;

/**
 * Created by robertwaite on 1/13/16.
 */
public class ParticleEffectsManager {
    Array<ParticleEffect> effects;

    public ParticleEffectsManager(){
        effects = new Array<ParticleEffect>();
    }

    public void addEffect(ParticleEffect effect, Vector2 position){
        effect.setPosition(position.x, position.y);
        effect.start();
        effects.add(effect);

    }

    public void update(float delta){
        for(int i = 0; i < effects.size; i++){
            effects.get(i).update(delta);
            if(effects.get(i).isComplete()){
                effects.removeIndex(i);
            }
        }
    }

    public void draw(SpriteBatch batch){
        for(ParticleEffect effect: effects){
            effect.draw(batch);
        }
    }

    public void dispose(){
        for(int i = 0; i < effects.size; i++){
            effects.removeIndex(i);
        }
        effects.clear();
    }
}
