package com.nerddaygames.rupert.games.Protect.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
    // TODO put in texture atlas
    public static TextureRegion thanks;
    public static TextureRegion splash;
    public static TextureRegion menubackground;
    public static TextureRegion helpbackground;
    public static TextureRegion help;

    public static TextureRegion startbutton;
    public static TextureRegion helpbutton;
    public static TextureRegion menubutton;

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
    public static ParticleEffect fireWork;
    public static ParticleEffect singleFirework;

    public static Sound bossIsComing;
    public static Sound boom;
    public static Sound spawn;
    public static Sound spawn2;
    public static Sound noenergy;
    public static Sound button;
    public static Sound collectenergy;
    public static Sound hitearth;
    public static Sound shoot;
    public static Sound thud;

    public static Music bossMusic;
    public static Music levelMusic;

    public AssetsManager() {
        loadAssets();
    }

    public static void loadAssets() {

        thanks = new TextureRegion(new Texture("thanks.png"));


        splash = new TextureRegion(new Texture("splash.png"));

        menubackground = new TextureRegion(new Texture("menubackground.png"));
        helpbackground = new TextureRegion(new Texture("helpbackground.png"));
        help = new TextureRegion(new Texture("help.png"));


        startbutton = new TextureRegion(new Texture("startbutton.png"));
        menubutton = new TextureRegion(new Texture("menubutton.png"));
        helpbutton = new TextureRegion(new Texture("helpbutton.png"));

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

        explosionBlue = new ParticleEffect();
        explosionBlue.load(Gdx.files.internal("particles/fire work explosion blue.p"), Gdx.files.internal("particles/"));

        fireWork = new ParticleEffect();
        fireWork.load(Gdx.files.internal("particles/fire work explosion.p"), Gdx.files.internal("particles/"));

        singleFirework = new ParticleEffect();
        singleFirework.load(Gdx.files.internal("particles/single firework.p"), Gdx.files.internal("particles/"));


        bossIsComing = Gdx.audio.newSound(Gdx.files.internal("sounds/bosscoming.wav"));
        boom = Gdx.audio.newSound(Gdx.files.internal("sounds/boom.wav"));
        spawn = Gdx.audio.newSound(Gdx.files.internal("sounds/spawn.wav"));
        spawn2 = Gdx.audio.newSound(Gdx.files.internal("sounds/spawn2.wav"));
        button = Gdx.audio.newSound(Gdx.files.internal("sounds/button.wav"));
        noenergy = Gdx.audio.newSound(Gdx.files.internal("sounds/noenergy.wav"));
        collectenergy = Gdx.audio.newSound(Gdx.files.internal("sounds/collectenergy.wav"));
        hitearth = Gdx.audio.newSound(Gdx.files.internal("sounds/hitearth.wav"));
        shoot = Gdx.audio.newSound(Gdx.files.internal("sounds/shoot.wav"));;
        thud = Gdx.audio.newSound(Gdx.files.internal("sounds/thud.wav"));;

        bossMusic = Gdx.audio.newMusic(Gdx.files.internal("music/DST-AngryRobotIII.mp3"));
        levelMusic = Gdx.audio.newMusic(Gdx.files.internal("music/DST-OldTune.mp3"));

    }

    public static void dispose() {
        thanks.getTexture().dispose();
        splash.getTexture().dispose();
        menubackground.getTexture().dispose();
        helpbackground.getTexture().dispose();
        help.getTexture().dispose();

        startbutton.getTexture().dispose();
        helpbutton.getTexture().dispose();
        menubutton.getTexture().dispose();

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
        fireWork.dispose();
        singleFirework.dispose();

        bossIsComing.dispose();
        boom.dispose();
        spawn.dispose();
        spawn2.dispose();
        noenergy.dispose();
        button.dispose();
        collectenergy.dispose();
        hitearth.dispose();
        shoot.dispose();
        thud.dispose();

        bossMusic.dispose();
        levelMusic.dispose();
    }
}
