package com.nerddaygames.rupert.games.Protect.animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;

/**
 * Created by robertwaite on 12/26/15.
 */
public class BulletAnimations {

    private Array<TextureRegion> plasmaFrames;
    private Animation plasmaAnimation;
    private TextureRegion currentFrame;
    private float stateTime;

    private float width, height;

    private String path = "animations/plasma/";


    public BulletAnimations(){
        plasmaFrames = new Array<TextureRegion>();
        createPlasmaAnimation(plasmaFrames, path, "plasma_warm_up", 6);

        plasmaAnimation = new Animation(0.5f, plasmaFrames);
        stateTime = 0f;

        width = ProtectConstants.MENU_OBJECT_MIN_SIZE;
        height = ProtectConstants.MENU_OBJECT_MIN_SIZE;

    }

    private void createPlasmaAnimation(Array<TextureRegion> array, String path, String fileName, int frameNumber) {
        int counter = 0;
        for(int i = 1; i < frameNumber+1; i++) {
            String texturePath = path + fileName + i + ".png";
            array.add(new TextureRegion(new Texture(texturePath)));
            counter++;

        }
    }

    public void drawPlasma(SpriteBatch batch){
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = plasmaAnimation.getKeyFrame(stateTime, true); // stateTime, looping

        Color orgColor = new Color(batch.getColor());
        batch.setColor(Color.FIREBRICK);
        batch.draw(currentFrame, 100 - currentFrame.getRegionWidth() / 2, 100 - currentFrame.getRegionHeight() / 2,
                currentFrame.getRegionWidth() / 2, currentFrame.getRegionHeight() / 2,
                currentFrame.getRegionWidth(), currentFrame.getRegionHeight(),
                1, 1, 0);
        batch.setColor(orgColor);
    }

}
