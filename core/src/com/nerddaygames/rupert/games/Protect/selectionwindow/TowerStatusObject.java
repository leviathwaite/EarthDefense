package com.nerddaygames.rupert.games.Protect.selectionwindow;

import com.badlogic.gdx.math.Vector2;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;

import java.util.PriorityQueue;

/**
 * Created by robertwaite on 1/5/16.
 */
public class TowerStatusObject {

    private int type;
    Vector2 playerSlotPosition;


    public TowerStatusObject(){
        type = ProtectConstants.TowerTypes.NULL;
        playerSlotPosition = new Vector2();
    }

    public int getType(){
        return type;
    }

    public Vector2 getPlayerSlotPosition(){
        return playerSlotPosition;
    }

    public void setType(int type){
        this.type = type;
    }


    public void setPlayerSlotPosition(Vector2 playerSlotPosition) {
        this.playerSlotPosition = playerSlotPosition;
    }
}
