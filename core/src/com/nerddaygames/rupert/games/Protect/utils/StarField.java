package com.nerddaygames.rupert.games.Protect.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * Created by robertwaite on 1/13/16.
 */
public class StarField {


    private static final float STAR_DENSITY = 0.001f;
    Array<Vector3> stars;

    public StarField(){
        // this.shapeRenderer = shapeRenderer;
        initStars(STAR_DENSITY);
    }

    private void initStars(float density){
        // TODO: Figure out how many stars to draw. You'll need the screen dimensions, which you can get using Gdx.graphics.getWidth() and Gdx.graphics.getHeight().
        int screenWidth = (int)ProtectConstants.VIEWPORT_WIDTH;
        int screenHeight = (int)ProtectConstants.VIEWPORT_HEIGHT;
        int starCount = (int)(screenHeight * screenWidth * density);
        // Gdx.app.log("StarField", "starCount: " + starCount);

        // TODO: Create a new array of Vector2's to hold the star positions
        stars = new Array<Vector3>(starCount);
        // TODO: Use java.util.Random to fill the array of star positions
        Random random = new Random();
        for (int i = 0; i < starCount; i++){
            int x = random.nextInt(screenWidth);
            int y = random.nextInt(screenHeight);
            stars.add(new Vector3(x, y, random.nextFloat()));
        }
    }

    public void draw(ShapeRenderer shapeRenderer){

        shapeRenderer.setColor(Color.WHITE);
        // TODO: Loop through the star positions and use shapeRenderer to draw points
        for (Vector3 star : stars){
            shapeRenderer.circle(star.x, star.y, star.z);
            // shapeRenderer.point(star.x, star.y, 0);
            // Gdx.app.log("StarField", "x: " + star.x + ", y: " + star.y + ", z: " + z);
        }
    }
}
