package com.nerddaygames.rupert.games.Protect.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by robertwaite on 12/20/15.
 */
public class ProtectConstants {

    // 1,920 × 1,080
    public static final float VIEWPORT_WIDTH = 960; // 1060 itch.io sizes
    public static final float VIEWPORT_HEIGHT = 540; // 620 itch.io sizes
    // public static final float VIEWPORT_WIDTH = 800;
    // public static final float VIEWPORT_HEIGHT = 450;
    public static final float VIEWPORT_WIDTH_CENTER = VIEWPORT_WIDTH / 2;
    public static final float VIEWPORT_HEIGHT_CENTER = VIEWPORT_HEIGHT / 2;


    public static final float MENU_WIDTH = (VIEWPORT_WIDTH - VIEWPORT_HEIGHT) / 2;;
    public static final float MENU_HEIGHT = VIEWPORT_HEIGHT;

    public static final float MENU_OBJECT_MIN_MARGIN = 5;
    public static final float MENU_OBJECT_MIN_SIZE = 80;

    public static final float MENU_TEXT_OFFSET_X = MENU_OBJECT_MIN_SIZE / 2;
    public static final float MENU_TEXT_OFFSET_Y = MENU_OBJECT_MIN_SIZE / 4;


    public static final float HEALTHBAR_WIDTH = MENU_WIDTH - (MENU_OBJECT_MIN_MARGIN * 2);
    public static final float HEALTHBAR_HEIGHT = 25;

    public static final float GAME_OBJECT_SIZE = 35;
    public static final float GAME_OBJECT_SPEED = 25;

    public static final Vector2 BALANCE_POSITION = new Vector2(MENU_WIDTH / 2, VIEWPORT_HEIGHT - MENU_OBJECT_MIN_MARGIN);


    public static final float BOOSTERTOWER_MULTIPLIER = 2;

    // used in SpawnPoints to draw grid
    public static final Color GRID_COLOR = Color.LIGHT_GRAY;

    public static final float EARTH_SIZE = 50;

    public static final int RINGS = 3; // ROWS
    public static final int COLUMNS = 10;

    public static float RING1 = ProtectConstants.EARTH_SIZE * 1.5f - (ProtectConstants.GAME_OBJECT_SIZE / 2);
    public static float RING2 = ProtectConstants.EARTH_SIZE * 2.5f - (ProtectConstants.GAME_OBJECT_SIZE / 2);
    public static float RING3 = ProtectConstants.EARTH_SIZE * 3.5f - (ProtectConstants.GAME_OBJECT_SIZE / 2);

    public static float ORBIT = ProtectConstants.EARTH_SIZE * 4.5f - (ProtectConstants.GAME_OBJECT_SIZE / 2);

    // TODO draw health text
    public static float SHOOTER_MAX_HEALTH = 100;
    public static float BOOSTER_MAX_HEALTH = 100;
    public static float MINE_MAX_HEALTH = 100;
    public static float BLOCKER_MAX_HEALTH = 500;
    public static float ENERGY_MAX_HEALTH = 50;
    public static float MISSILE_MAX_HEALTH = 100;

    public static int SHOOTER_COST = 50;
    public static String SHOOTER_COST_STRING = "50";

    public static int BOOSTER_COST = 25;
    public static String BOOSTER_COST_STRING = "25";

    public static int MINE_COST = 75;
    public static String MINE_COST_STRING = "75";

    public static int BLOCKER_COST = 50;
    public static String BLOCKER_COST_STRING = "50";

    public static int ENERGY_COST = 100;
    public static String ENERGY_COST_STRING = "100";

    public static int MISSILE_COST = 100;
    public static String MISSILE_COST_STRING = "100";


    public static class TowerTypes{
        // TODO remove NULL type
        public static final int NULL = -1, SHOOTER = 0, BOOSTER = 1, MINE = 2, BLOCKER = 3, ENERGY = 4, MISSILE = 5;
    }

    // Enemy Constants
    public static class EnemyTypes{
        // TODO remove NULL type
        public static final int NULL = -1, BASE = 0, TOUGH = 1, TOUGHER = 2, JUMPER = 3, BOSS = 4;
    }

    // Enemy Health
    public static float BASE_MAX_HEALTH = 50;
    public static float TOUGH_MAX_HEALTH = 100;
    public static float TOUGHER_MAX_HEALTH = 150;
    public static float JUMPER_MAX_HEALTH = 50;
    public static float BOSS_MAX_HEALTH = 500;

    // Enemy HitStrength
    public static float BASE_HITSTRENGTH = 5;
    public static float TOUGH_HITSTRENGTH = 5;
    public static float TOUGHER_HITSTRENGTH = 5;
    public static float JUMPER_HITSTRENGTH = 5;
    public static float BOSS_HITSTRENGTH = 0; // doesn't hit only spawns

}
