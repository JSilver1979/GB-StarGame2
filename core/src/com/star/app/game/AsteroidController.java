package com.star.app.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.star.app.game.helpers.ObjectPool;

public class AsteroidController extends ObjectPool<Asteroid> {
    private Texture asteroidTexture;
    private int asteroidsCount = 2;

    @Override
    protected Asteroid newObject() {
        return new Asteroid();
    }

    public AsteroidController() {
        this.asteroidTexture = new Texture("asteroid.png");
    }

    public void render(SpriteBatch batch){
        for (int i = 0; i < activeList.size(); i++) {
            Asteroid asteroid = activeList.get(i);
            batch.draw(asteroidTexture, asteroid.getPosition().x - 128, asteroid.getPosition().y - 128,128,128,256,256,1,1,0,0,0,256,256,false,false);
        }
    }

    public int getAsteroidsCount() {
        return asteroidsCount;
    }

    public void setup(){
        getActiveElement().activate();
    }

    public void update(float dt) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).update(dt);
        }
        checkPool();
    }
}
