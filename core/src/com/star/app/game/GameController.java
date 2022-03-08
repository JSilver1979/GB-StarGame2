package com.star.app.game;

public class GameController {
    private Background background;
    private BulletController bulletController;
    private Hero hero;
    private AsteroidController asteroidController;

    public BulletController getBulletController() {
        return bulletController;
    }

    public Background getBackground() {
        return background;
    }

    public Hero getHero() {
        return hero;
    }

    public AsteroidController getAsteroidController() {return asteroidController;}

    public GameController() {
        this.background = new Background(this);
        this.bulletController = new BulletController();
        this.hero = new Hero(this);
        this.asteroidController = new AsteroidController();

    }

    public void update(float dt) {
        background.update(dt);
        bulletController.update(dt);
        hero.update(dt);
        asteroidController.update(dt);
        startAsteroids();
        asteroidHits();
        checkCollisions();
    }


    public void checkCollisions() {
        for (int i = 0; i < bulletController.getActiveList().size(); i++) {
            Bullet b = bulletController.getActiveList().get(i);

            if (hero.getPosition().dst(b.getPosition()) < 32.0f){
                //b.deactivate();
            }
        }
    }
    public void startAsteroids(){
        if (asteroidController.getActiveList().size() < asteroidController.getAsteroidsCount()) {
            asteroidController.setup();
        }
    }

    public void asteroidHits() {
        for (int i = 0; i < bulletController.getActiveList().size(); i++) {
            Bullet b = bulletController.getActiveList().get(i);
            for (int j = 0; j < asteroidController.getActiveList().size(); j++) {
                Asteroid a = asteroidController.getActiveList().get(j);

                if (a.getPosition().dst(b.getPosition()) < 128.0f) {
                    a.deactivate();
                    b.deactivate();
                }
            }
        }
    }
}
