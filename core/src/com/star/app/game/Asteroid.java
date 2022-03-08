package com.star.app.game;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.star.app.game.helpers.Poolable;
import com.star.app.screen.ScreenManager;

public class Asteroid implements Poolable {
    private Vector2 position;
    private Vector2 velocity;
    private boolean active;

    public Asteroid() {
        this.position = new Vector2();
        this.velocity = new Vector2();
        this.active = false;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void update(float dt) {
        position.mulAdd(velocity, dt);
        checkBorders();
    }

    public void activate() {
        position.set(-128f, MathUtils.random(0, ScreenManager.SCREEN_HEIGHT));
        velocity.set(MathUtils.random(-500, 500), MathUtils.random(-500, 500));
        active = true;
    }

    public void deactivate() {
        active = false;
    }

    public void checkBorders() {
        if (position.x < 0.0f) {
            position.x = ScreenManager.SCREEN_WIDTH + 128f;
        }
        if (position.x > ScreenManager.SCREEN_WIDTH + 128f) {
            position.x = 0.0f;
        }
        if (position.y < 0.0f) {
            position.y = ScreenManager.SCREEN_HEIGHT + 128f;
        }
        if (position.y > ScreenManager.SCREEN_HEIGHT + 128f) {
            position.y = 0.0f;
        }
    }
}
