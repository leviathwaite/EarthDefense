package com.nerddaygames.rupert.games.Protect.screens.Buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.nerddaygames.rupert.games.Protect.managers.AssetsManager;
import com.nerddaygames.rupert.games.Protect.utils.ProtectConstants;

import java.util.PriorityQueue;

/**
 * Created by robertwaite on 1/17/16.
 */
public class StartButton {
    Rectangle touchArea;
    float width = ProtectConstants.BUTTON_WIDTH;
    float height = ProtectConstants.BUTTON_HEIGHT;

    public StartButton(Vector2 position){
        touchArea = new Rectangle(position.x, position.y, width, height);
    }

    public void debugDraw(ShapeRenderer shapeRenderer){
        /*
        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(touchArea.x, touchArea.y, width, height);
        */
    }

    public void draw(SpriteBatch batch){
        batch.draw(AssetsManager.startbutton, touchArea.x, touchArea.y,
                touchArea.getWidth() / 2, touchArea.getHeight() / 2,
                touchArea.getWidth(), touchArea.getHeight(),
                1, 1, 0);
    }

    public boolean checkForTouch(Vector2 point){
        return touchArea.contains(point);
    }
}
