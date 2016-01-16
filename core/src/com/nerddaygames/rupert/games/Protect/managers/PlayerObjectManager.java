package com.nerddaygames.rupert.games.Protect.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.nerddaygames.rupert.games.Protect.selectionwindow.ObjectMenu;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;

/**
 * Created by robertwaite on 12/23/15.
 */
public class PlayerObjectManager {
    /*
    // TODO move this
    private Array<Vector2> playerPositions;
    private Array<com.nerddaygames.rupert.games.Protect.gameobjects.factories.PlayerObjectSlot> playerObjectSlots;
    private float ring1 = ProtectConstants.EARTH_SIZE * 1.5f - (ProtectConstants.GAME_OBJECT_SIZE / 2);
    private float ring2 = ProtectConstants.EARTH_SIZE * 2.5f - (ProtectConstants.GAME_OBJECT_SIZE / 2);
    private float ring3 = ProtectConstants.EARTH_SIZE * 3.5f - (ProtectConstants.GAME_OBJECT_SIZE / 2);

    ObjectMenu objectMenu;

    public PlayerObjectManager(ObjectMenu objectMenu){
        this.objectMenu = objectMenu;
        init();
    }


    public void init(){


        playerPositions = new Array<Vector2>();
        setupPlayerPositions();

        playerObjectSlots = new Array<com.nerddaygames.rupert.games.Protect.gameobjects.factories.PlayerObjectSlot>();

        int counter = 0;
        for (Vector2 position: playerPositions){
            // playerObjects.add(new GameObject(position.x, position.y));
            playerObjectSlots.add(new com.nerddaygames.rupert.games.Protect.gameobjects.factories.PlayerObjectSlot(position));
            counter++;
            Gdx.app.log("PlayerPositions", "playerPositions: " + position);
        }

    }

    private void setupPlayerPositions() {
        // 3 rings * 10 sections
        // TODO use vector 3s z for object tracking
        float centerX = ProtectConstants.VIEWPORT_WIDTH / 2;
        float centerY = ProtectConstants.VIEWPORT_HEIGHT / 2;
        float angle = 18;
        for (int i = 1; i < 11; i++) {
            // get rid of ring1 - ring3 and multiply by i or something...
            playerPositions.add(divideCircle(centerX, centerY, ring1, angle));
            playerPositions.add(divideCircle(centerX, centerY, ring2, angle));
            playerPositions.add(divideCircle(centerX, centerY, ring3, angle));

            angle += 360 / 10;
        }
    }

    private Vector2 divideCircle(float centerX, float centerY, float radius, float angleDegree){
        angleDegree = angleDegree % 360;
        Vector2 point = new Vector2(centerX + (radius) * MathUtils.cosDeg(angleDegree),
                centerY + (radius) * MathUtils.sinDeg(angleDegree));
        return point;
    }

    public void debugDraw(ShapeRenderer shapeRenderer){
        // grid
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        shapeRenderer.circle(ProtectConstants.VIEWPORT_WIDTH / 2, ProtectConstants.VIEWPORT_HEIGHT / 2, ProtectConstants.EARTH_SIZE * 2);
        shapeRenderer.circle(ProtectConstants.VIEWPORT_WIDTH / 2, ProtectConstants.VIEWPORT_HEIGHT / 2, ProtectConstants.EARTH_SIZE * 3);
        shapeRenderer.circle(ProtectConstants.VIEWPORT_WIDTH / 2, ProtectConstants.VIEWPORT_HEIGHT / 2, ProtectConstants.EARTH_SIZE * 4);


        float angle = 0;
        shapeRenderer.setColor(Color.LIGHT_GRAY);
        for(int i = 0; i < 10; i++) {

            float circleCenterX = ProtectConstants.VIEWPORT_WIDTH / 2;
            float circleCenterY = ProtectConstants.VIEWPORT_HEIGHT / 2;
            // x = centerX + radius * cos(angle)
            // y = centerY + radius * sin(angle)
            float smallCircleX = circleCenterX + (ProtectConstants.EARTH_SIZE) * MathUtils.cosDeg(angle);
            float smallCircleY = circleCenterY + (ProtectConstants.EARTH_SIZE) * MathUtils.sinDeg(angle);

            float medCircleX = circleCenterX + (ProtectConstants.EARTH_SIZE * 4) * MathUtils.cosDeg(angle);
            float medCircleY = circleCenterY + (ProtectConstants.EARTH_SIZE * 4) * MathUtils.sinDeg(angle);

            angle += 360 / 10;

            shapeRenderer.line(smallCircleX, smallCircleY,
                    medCircleX, medCircleY);
        }
                    //
        Vector2 circlePoint1 = divideCircle(ProtectConstants.VIEWPORT_WIDTH / 2, ProtectConstants.VIEWPORT_HEIGHT / 2,
                ProtectConstants.EARTH_SIZE, 180);

        Vector2 circlePoint2 = divideCircle(ProtectConstants.VIEWPORT_WIDTH / 2, ProtectConstants.VIEWPORT_HEIGHT / 2,
                ProtectConstants.EARTH_SIZE * 4, 180);

        shapeRenderer.setColor(Color.RED);
        shapeRenderer.line(circlePoint1.x, circlePoint1.y, circlePoint2.x, circlePoint2.y);
        //


        // earth
        shapeRenderer.circle(ProtectConstants.VIEWPORT_WIDTH / 2, ProtectConstants.VIEWPORT_HEIGHT / 2, ProtectConstants.EARTH_SIZE/2);

    }

    public void draw(float delta, SpriteBatch batch){
        for (com.nerddaygames.rupert.games.Protect.gameobjects.factories.PlayerObjectSlot playerObjectSlot: playerObjectSlots){
            playerObjectSlot.update(delta);
            playerObjectSlot.draw(batch);
        }


    }


    public void dispose(){
        for (com.nerddaygames.rupert.games.Protect.gameobjects.factories.PlayerObjectSlot playerObjectSlot: playerObjectSlots){
            playerObjectSlot.dispose();
        }
    }

    public void touched(Vector2 worldClick) {
        for (com.nerddaygames.rupert.games.Protect.gameobjects.factories.PlayerObjectSlot playerObjectSlot: playerObjectSlots){
           // playerObjectSlot.checkCollision(worldClick);

            if(playerObjectSlot.checkCollision(worldClick) &&
                    objectMenu.getSelected() != null){
                playerObjectSlot.setType(objectMenu.getSelected().getType());
                objectMenu.deselect();
            }

        }
    }
    */
}
