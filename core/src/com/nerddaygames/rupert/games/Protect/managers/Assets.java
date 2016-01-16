package com.nerddaygames.rupert.games.Protect.managers;

import com.badlogic.gdx.assets.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by robertwaite on 12/19/15.
 */
public class Assets implements Disposable {

    public static TextureRegion ship;


    public static final String TAG = Assets.class.getSimpleName();

    public static boolean rebuildAtlas = false;
    public static boolean drawDebugOutline = false;

    public static AssetManager manager;

    public static AssetManager getManager(){
        if(manager == null){
            manager = new AssetManager();
        }
        return manager;
    }

    public static final String TEXTURE_ATLAS_OBJECTS = "assets.atlas";
    // public static final String SKIN = "skin/uiskin.json";

    public static void load(){
        getManager();
        // manager.load(TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        // manager.load(SKIN, Skin.class);
        // loadSounds();
        loadImages();
    }

    public static void loadImages(){
        ship = new TextureRegion(new Texture("ship"));

    }

    public static void loadParticles(){
        // manager.load("particles/explosion.p", ParticleEffect.class);
    }

    public static void loadSounds(){
        // manager.load("sound/boom.ogg", Sound.class);
    }

    public static void create(){
        //TextureAtlas atlas = manager.get(TEXTURE_ATLAS_OBJECTS);

        //skin = manager.get(SKIN);
    }

    @Override
    public void dispose() {
        // manager.dispose();
        ship.getTexture().dispose();
    }

    public static class AssetPlayer{
       // public final TextureRegion player;

        public AssetPlayer(TextureAtlas atlas){
           // player = atlas.findRegion("player");
        }
    }



}
