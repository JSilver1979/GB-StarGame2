package com.star.app.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.star.app.StarGame;

import static com.star.app.screen.ScreenManager.*;

public class Background {
    private class Star {
        private Vector2 position;
        private Vector2 velocity;
        private float scale;

        public Star() {
            this.position = new Vector2(MathUtils.random(-200, SCREEN_WIDTH + 200),
                    MathUtils.random(-200, SCREEN_HEIGHT + 200));
            this.velocity = new Vector2(MathUtils.random(-40, -5), 0);
            scale = Math.abs(velocity.x / 40f) * 0.8f;
        }

        public void update(float dt) {
            position.x += (velocity.x - gc.getHero().getVelocity().x * 0.1f) * dt;
            position.y += (velocity.y - gc.getHero().getVelocity().y * 0.1f) * dt;

            if (position.x < -200) {
                position.x = SCREEN_WIDTH + 200;
                position.y = MathUtils.random(0, SCREEN_HEIGHT);
                scale = Math.abs(velocity.x / 40f) * 0.8f;
            }
        }
    }

    private final int STAR_COUNT = 600;
    private Texture textureCosmos;
    private Texture textureStar;
    private GameController gc;
    private Star[] stars;

    public Background(GameController gc) {
        this.gc = gc;
        this.textureCosmos = new Texture("bg.png");
        this.textureStar = new Texture("star16.png");
        this.stars = new Star[STAR_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star();
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(textureCosmos, 0, 0);
        for (int i = 0; i < stars.length; i++) {
            batch.draw(textureStar, stars[i].position.x - 8, stars[i].position.y - 8, 8, 8,
                    16, 16, stars[i].scale, stars[i].scale,
                    0, 0, 0, 16, 16, false, false);
            if (MathUtils.random(300) < 1) {
                batch.draw(textureStar, stars[i].position.x - 8, stars[i].position.y - 8, 8, 8,
                        16, 16, stars[i].scale * 2, stars[i].scale * 2,
                        0, 0, 0, 16, 16, false, false);
            }
        }
    }

    public void update(float dt) {
        for (int i = 0; i < stars.length; i++) {
            stars[i].update(dt);
        }
    }

}
