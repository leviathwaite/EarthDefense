package com.nerddaygames.rupert.games.Protect.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;

import java.util.Properties;

/**
 * Created by robertwaite on 12/28/15.
 */
public class AssetsManager {

    public static TextureRegion earth;
    public static TextureRegion shooter;
    public static TextureRegion booster;
    public static TextureRegion mine;
    public static TextureRegion blocker;
    public static TextureRegion energyTower;
    public static TextureRegion missile;

    public static TextureRegion energy;

    public static TextureRegion enemy;

    public static TextureRegion enemy1;
    public static TextureRegion enemy2;
    public static TextureRegion enemy3;
    public static TextureRegion enemy4;
    public static TextureRegion enemyboss;

    public static TextureRegion rect;

    public static TextureRegion circle;
    public static TextureRegion disable;

    public static NinePatch circlePatch;
    public static NinePatch menuBox;
    public static NinePatch menuBoxFilled;
    public static NinePatch healthbarRed;
    public static NinePatch healthbarGreen;

    public static BitmapFont bitmapFont;
    public static BitmapFont messageBitmapFont;

    public static ParticleEffect explosion;
    public static ParticleEffect explosionBlue;
    // public static ParticleEffect fireWork;
    // public static ParticleEffect singleFirework;


    public AssetsManager() {
        loadAssets();
    }

    public static void loadAssets() {
        earth = new TextureRegion(new Texture("earth2.png"));
        shooter = new TextureRegion(new Texture("shooter.png"));
        booster = new TextureRegion(new Texture("booster.png"));
        mine = new TextureRegion(new Texture("mine.png"));
        blocker = new TextureRegion(new Texture("blocker.png"));
        energyTower = new TextureRegion(new Texture("tower_energy.png"));
        missile = new TextureRegion(new Texture("missile.png"));

        energy = new TextureRegion(new Texture("energy.png"));


        enemy = new TextureRegion(new Texture("enemy_invader1.png"));

        enemy1 = new TextureRegion(new Texture("enemy-invader1.png"));
        enemy2 = new TextureRegion(new Texture("enemy-invader2.png"));
        enemy3 = new TextureRegion(new Texture("enemy-invader3.png"));
        enemy4 = new TextureRegion(new Texture("enemy-invader4.png"));
        enemyboss = new TextureRegion(new Texture("enemy-invaderboss.png"));

        rect = new TextureRegion(new Texture("rect.png"));


        circle = new TextureRegion(new Texture("circle.png"));
        disable = new TextureRegion(new Texture("disabled.png"));


        circlePatch = new NinePatch(new Texture("circle.9.png"), 4, 4, 4, 4);

        menuBox = new NinePatch(new Texture("menu_box.9.png"), 4, 4, 4, 4);
        menuBoxFilled = new NinePatch(new Texture("menu_box_filled.9.png"), 4, 4, 4, 4);
        // TODO fix size?
        Texture healthbarGreenTex = new Texture("healthbar_green.9.png");
        Texture healthbarRedTex = new Texture("healthbar_red.9.png");
        healthbarGreen = new NinePatch(new TextureRegion(healthbarGreenTex, 65, 65), 17, 17, 17, 17);
        healthbarRed = new NinePatch(new TextureRegion(healthbarRedTex, 65, 65), 17, 17, 17, 17);

        bitmapFont = new BitmapFont();
        messageBitmapFont = new BitmapFont();

        explosion = new ParticleEffect();
        explosion.load(Gdx.files.internal("particles/explosion.p"), Gdx.files.internal("particles/"));

        // explosion.getEmitters().first().setPosition(ProtectConstants.VIEWPORT_WIDTH_CENTER, ProtectConstants.VIEWPORT_HEIGHT_CENTER);

        explosionBlue = new ParticleEffect();
        explosionBlue.load(Gdx.files.internal("particles/fire work explosion blue.p"), Gdx.files.internal("particles/"));

        // explosionBlue.getEmitters().first().setPosition(ProtectConstants.VIEWPORT_WIDTH_CENTER, ProtectConstants.VIEWPORT_HEIGHT_CENTER);

    }

    public static void dispose() {
        earth.getTexture().dispose();
        shooter.getTexture().dispose();
        booster.getTexture().dispose();
        mine.getTexture().dispose();
        blocker.getTexture().dispose();
        energyTower.getTexture().dispose();
        missile.getTexture().dispose();

        energy.getTexture().dispose();

        enemy.getTexture().dispose();

        enemy1.getTexture().dispose();
        enemy2.getTexture().dispose();
        enemy3.getTexture().dispose();
        enemy4.getTexture().dispose();
        enemyboss.getTexture().dispose();

        rect.getTexture().dispose();

        circle.getTexture().dispose();
        disable.getTexture().dispose();
        circlePatch.getTexture().dispose();

        menuBox.getTexture().dispose();
        menuBoxFilled.getTexture().dispose();
        healthbarGreen.getTexture().dispose();
        healthbarRed.getTexture().dispose();

        bitmapFont.dispose();
        messageBitmapFont.dispose();

        explosion.dispose();
        explosionBlue.dispose();
    }
}
