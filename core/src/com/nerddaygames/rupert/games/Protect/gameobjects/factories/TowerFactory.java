package com.nerddaygames.rupert.games.Protect.gameobjects.factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.nerddaygames.rupert.games.Protect.gameobjects.towers.BlockerTower;
import com.nerddaygames.rupert.games.Protect.gameobjects.towers.BoosterTower;
import com.nerddaygames.rupert.games.Protect.gameobjects.towers.EnergyTower;
import com.nerddaygames.rupert.games.Protect.gameobjects.towers.MineTower;
import com.nerddaygames.rupert.games.Protect.gameobjects.towers.MissileTower;
import com.nerddaygames.rupert.games.Protect.gameobjects.towers.ShooterTower;
import com.nerddaygames.rupert.games.Protect.gameobjects.towers.Tower;
import com.nerddaygames.rupert.games.Protect.managers.GameManager;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;

/**
 * Created by robertwaite on 12/28/15.
 */
public class TowerFactory {
    // called from GameManager, fed position from touch on playerSlot
    GameManager gameManager;

    public TowerFactory(GameManager gameManager){
        this.gameManager = gameManager;
    }

    public Tower createTower(int type, Vector2 position){
        switch (type){
            case ProtectConstants.TowerTypes.SHOOTER: {
                return new ShooterTower(gameManager, position);
            }
            case ProtectConstants.TowerTypes.MISSILE: {
                return new MissileTower(gameManager, position);
            }
            case ProtectConstants.TowerTypes.ENERGY: {
                return new EnergyTower(gameManager, position);
            }
            case ProtectConstants.TowerTypes.MINE: {
                return new MineTower(position);
            }
            case ProtectConstants.TowerTypes.BOOSTER: {
                return new BoosterTower(position);
            }
            case ProtectConstants.TowerTypes.BLOCKER: {
                    return new BlockerTower(position);
            }
            default:{
                Gdx.app.log("TowerFactory", "Type does not exist.");
            }

        }
        return null;
    }
}
