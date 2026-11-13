package dev.licaza.temanahuiani.demo.actors.enemies;

import static dev.licaza.temanahuiani.demo.configs.Constants.*;

import java.util.function.IntConsumer;

import dev.licaza.temanahuiani.engine.commons.actors.Enemy;

public class Wolf extends Enemy {

    private final static float speed = 0.75f;
    private final static int startHealth = 90;

    public Wolf(float x, float y, int id, IntConsumer callback) {
        super(x, y, id, Enemies.WOLF, startHealth, callback);
    }

    @Override
    public float getEnemySpeed() {
        return speed;
    }

}
