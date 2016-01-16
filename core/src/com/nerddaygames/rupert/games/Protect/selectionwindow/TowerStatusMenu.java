package com.nerddaygames.rupert.games.Protect.selectionwindow;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.nerddaygames.rupert.games.Protect.gameobjects.towers.Tower;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;

/**
 * Created by robertwaite on 1/5/16.
 */
public class TowerStatusMenu {

    // TODO finish status box
    // health
    // pic
    // trade in or destroy value
    // upgrade or repair?

    Vector2 position;
    Vector2 healthBarPosition;
    Vector2 disableButtonPosition;
    Rectangle disableButtonCollision;


    Vector2 towerPosition; // where to draw pic of tower

    float width = ProtectConstants.MENU_WIDTH;
    float height = ProtectConstants.MENU_HEIGHT;
    float centerX;


    Tower selectedTower;

    public TowerStatusMenu(){
        position = new Vector2(ProtectConstants.VIEWPORT_WIDTH - width, 0);
        centerX = position.x + (width / 2) - (ProtectConstants.MENU_OBJECT_MIN_SIZE / 2);

        healthBarPosition = new Vector2(position.x + ProtectConstants.MENU_OBJECT_MIN_MARGIN,
                height - ProtectConstants.HEALTHBAR_HEIGHT - ProtectConstants.MENU_OBJECT_MIN_MARGIN);
         towerPosition = new Vector2(centerX,
                healthBarPosition.y - (ProtectConstants.MENU_OBJECT_MIN_SIZE * 2));

        disableButtonPosition = new Vector2(centerX, position.y + ProtectConstants.MENU_OBJECT_MIN_SIZE);
        disableButtonCollision = new Rectangle(disableButtonPosition.x, disableButtonPosition.y,
                ProtectConstants.MENU_OBJECT_MIN_SIZE, ProtectConstants.MENU_OBJECT_MIN_SIZE);


        selectedTower = new Tower(position); // TODO + offset
        selectedTower.setType(ProtectConstants.TowerTypes.NULL);
    }

    public Vector2 getPosition(){
        return position;
    }

    public Vector2 getDisableButtonPosition(){
        return disableButtonPosition;
    }
    public boolean checkDisableButtonCollision(Vector2 point){
        if(disableButtonCollision.contains(point)){
            return true;
        }
        return false;
    }

    public Vector2 getTowerPosition() {
        return towerPosition;
    }

    public Vector2 getHealthBarPosition() {
        return healthBarPosition;
    }
}



