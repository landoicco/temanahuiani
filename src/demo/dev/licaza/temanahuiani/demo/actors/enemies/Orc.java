package dev.licaza.temanahuiani.demo.actors.enemies;

import static dev.licaza.temanahuiani.demo.configs.Constants.*;

import java.util.function.IntConsumer;

import dev.licaza.temanahuiani.engine.commons.actors.Enemy;

public class Orc extends Enemy {

    private final static float speed = 0.5f;
    private final static int startHealth = 50;

    public Orc(float x, float y, int id, IntConsumer callback) {
        super(x, y, id, Enemies.ORC, startHealth, callback);
    }

    @Override
    public float getEnemySpeed() {
        return speed;
    }

}
