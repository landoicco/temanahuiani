package dev.licaza.temanahuiani.demo.actors.enemies;

import static dev.licaza.temanahuiani.demo.configs.Constants.*;

import dev.licaza.temanahuiani.engine.commons.actors.Enemy;

import java.util.function.IntConsumer;

public class Bat extends Enemy {

    private static final float speed = 0.65f;
    private static final int startHealth = 1;

    public Bat(float x, float y, int id, IntConsumer callback) {
        super(x, y, id, Enemies.BAT, startHealth, callback);
    }

    @Override
    public float getEnemySpeed() {
        return speed;
    }
}
