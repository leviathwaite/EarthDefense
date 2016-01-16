package com.nerddaygames.rupert.games.Protect.selectionwindow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;


/**
 * Created by robertwaite on 12/25/15.
 */
public class MenuObject {

    Vector2 position;
    Rectangle collisionRect;

    boolean selected = false;
    int type;

    int cost;

    // constructor for temp selected
    // TODO refill timer for recently used objects
    public MenuObject(){
    }

    public MenuObject(int type, Vector2 position){
        this.type = type;
        setCost();
        this.position = new Vector2(position);
        //this.position = position;

        collisionRect = new Rectangle(position.x, position.y, ProtectConstants.MENU_OBJECT_MIN_SIZE, ProtectConstants.MENU_OBJECT_MIN_SIZE);


    }

    private void setCost() {
            switch (type){
                case ProtectConstants.TowerTypes.SHOOTER:
                    cost = ProtectConstants.SHOOTER_COST;


                    break;
                case ProtectConstants.TowerTypes.BLOCKER:
                    cost = ProtectConstants.BLOCKER_COST;

                    break;
                case ProtectConstants.TowerTypes.BOOSTER:
                    cost = ProtectConstants.BOOSTER_COST;

                    break;
                case ProtectConstants.TowerTypes.MINE:
                    cost = ProtectConstants.MINE_COST;

                    break;
                case ProtectConstants.TowerTypes.ENERGY:
                    cost = ProtectConstants.ENERGY_COST;


                    break;
                case ProtectConstants.TowerTypes.MISSILE:
                    cost = ProtectConstants.MISSILE_COST;

                    break;
            }

    }

    public int getCost(int type) {
        switch (type){
            case ProtectConstants.TowerTypes.SHOOTER:
                return ProtectConstants.SHOOTER_COST;

            case ProtectConstants.TowerTypes.BLOCKER:
                return ProtectConstants.BLOCKER_COST;

            case ProtectConstants.TowerTypes.BOOSTER:
                return ProtectConstants.BOOSTER_COST;

            case ProtectConstants.TowerTypes.MINE:
                return ProtectConstants.MINE_COST;

            case ProtectConstants.TowerTypes.ENERGY:
                return ProtectConstants.ENERGY_COST;

            case ProtectConstants.TowerTypes.MISSILE:
                return ProtectConstants.MISSILE_COST;

        }
        return 0;
    }

    public boolean checkForTouch(Vector2 touch){
        if(collisionRect.contains(touch)){
            // Gdx.app.log("MenuObject", "menu object hit");
            setSelected(true);
            return true;
        }
        return false;
    }

    public Vector2 getPosition(){
        return position;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getCost(){
        return cost;
    }

    public boolean getSelected(){
        return selected;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    // get type
}
