package com.nerddaygames.rupert.games.Protect.selectionwindow;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.nerddaygames.rupert.games.Protect.gameobjects.towers.Tower;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;
import com.nerddaygames.rupert.games.Protect.utils.WorldRenderer;

/**
 * Created by robertwaite on 12/25/15.
 */
public class ObjectMenu {

    Vector2 position;
    float width = ProtectConstants.MENU_WIDTH;
    float height = ProtectConstants.MENU_HEIGHT;

    boolean selected;
    int counter; // for nextSpot counter

    Array<Vector2> spots;


    public ObjectMenu(){

        spots = new Array<Vector2>();
        setupSpots();

        position = new Vector2(0, 0);
        selected = false;

        counter = 0;
    }

    private void setupSpots() {
        int column = 2;
        int row = 5;

        float margin = ProtectConstants.MENU_OBJECT_MIN_MARGIN,
                yExtraMargin = (height - ((ProtectConstants.MENU_OBJECT_MIN_SIZE * row) + ((row + 1) * margin))) / 2;

        float x = margin;
        float y = margin; // + yExtraMargin;

        for(int i = 1; i < column + 1; i++){
            for(int j = 1; j < row + 1; j++){
                // TODO cycle thru types
                spots.add(new Vector2(x, y));
                y += ProtectConstants.MENU_OBJECT_MIN_SIZE + margin;
            }

            x += ProtectConstants.MENU_OBJECT_MIN_SIZE + margin;
            y = margin + yExtraMargin;
        }
        /*
        float margin = width / 3;
        float childWidth = width - (margin * 2);
        float childHeight = height / numberOfMenuObjects;

        for(int i = 1; i < numberOfMenuObjects + 1; i++){
            menuObjects.add(new MenuObject(position.x + margin, position.y + ((childHeight + margin)* i), childWidth, childHeight));
        }
        */
    }

    public Vector2 getPosition(){
        return position;
    }

    public float getWidth(){
        return width;
    }

    public float getHeight(){
        return height;
    }

    public Vector2 getNextSpot(){
        Vector2 nextSpot = new Vector2(spots.get(counter));
        counter++;
        return nextSpot;
    }
}
