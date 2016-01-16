package com.nerddaygames.rupert.games.Protect.managers;

import com.badlogic.gdx.Gdx;

/**
 * Created by robertwaite on 1/10/16.
 */

// TODO stores energy and later money for player
public class PlayerWallet {
    int energy;

    public PlayerWallet(){
        energy = 500;
    }

    public int getEnergyBalance(){
        // Gdx.app.log("PlayerWallet", "Energy balance: " + energy);
        return energy;
    }

    public void makePurchase(int amount){
        energy -= amount;
    }

    public void addEnergy(int amount){
        energy += amount;
    }
}
