package dev.licaza.temanahuiani.engine.core.main;

import dev.licaza.temanahuiani.engine.tools.helpers.LoadSave;

import java.util.concurrent.locks.LockSupport;

import javax.swing.JFrame;

public class Game extends JFrame {

    private final double FPS_SET = 120.0;
    private final double UPS_SET = 60.0;
    private Runnable updateGameCallback;

    protected Game() {
        this("Tower Defender - Java");
    }

    protected Game(String title) {
        super(title);

        // from JFrame...
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    protected void setUpdateGameCallback(Runnable updateGameCallback) {
        this.updateGameCallback = updateGameCallback;
    }

    protected void createDefaultLevel(String fileName) {
        int[] arr = new int[400];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }

        LoadSave.CreateLevel(fileName, arr);
    }

    protected Runnable getGameLoop() {
        return gameLoop;
    }

    private final Runnable gameLoop =
            () -> {
                double timePerFrame = 1_000_000_000.0 / FPS_SET;
                double timePerUpdate = 1_000_000_000.0 / UPS_SET;
                long lastFrame = System.nanoTime();
                long lastTimeUPS = System.nanoTime();

                long now;

                while (true) {
                    now = System.nanoTime();

                    // Render
                    if (now - lastFrame >= timePerFrame) {
                        lastFrame = now;
                        repaint();
                    }

                    // Update
                    if (now - lastTimeUPS >= timePerUpdate) {
                        lastTimeUPS = now;
                        updateGameCallback.run();
                    }

                    // Sleep until the next render or update is due, instead of
                    // spinning and keeping a CPU core busy
                    double nextEvent =
                            Math.min(lastFrame + timePerFrame, lastTimeUPS + timePerUpdate);
                    long sleepNanos = (long) nextEvent - System.nanoTime();
                    if (sleepNanos > 0) {
                        LockSupport.parkNanos(sleepNanos);
                    }
                }
            };
}
