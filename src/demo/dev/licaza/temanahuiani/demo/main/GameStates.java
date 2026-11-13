package dev.licaza.temanahuiani.demo.main;

import static dev.licaza.temanahuiani.demo.configs.MediaSource.*;

import javax.sound.sampled.Clip;

public enum GameStates {
    PLAYING,
    MENU,
    SETTINGS,
    EDIT,
    GAME_OVER;

    private static final Clip menuClip = Sounds.GetAudioClip("MENU");
    private static final Clip playClip = Sounds.GetAudioClip("PLAY");

    public static GameStates gameState = MENU;

    public static void setGameState(GameStates state) {

        // Select background music to play...
        if (state == MENU) {
            playClip.stop();
            menuClip.start();
        }
        if (state == PLAYING) {
            menuClip.stop();
            playClip.start();
        }

        gameState = state;
    }
}
