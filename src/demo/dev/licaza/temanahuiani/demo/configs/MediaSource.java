package dev.licaza.temanahuiani.demo.configs;

import dev.licaza.temanahuiani.engine.tools.helpers.ResourcesLoader;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.sound.sampled.Clip;

public class MediaSource {
    public static final class Fonts {
        private static final Font HEADER_FONT =
                ResourcesLoader.Fonts.LoadFontFromFile(new File("caveat-brush.ttf"));
        private static final Font BASE_FONT =
                ResourcesLoader.Fonts.LoadFontFromFile(new File("patrick-hand.ttf"));

        public static final Font GetHeaderFont() {
            return HEADER_FONT;
        }

        public static final Font GetBaseFont() {
            return BASE_FONT;
        }
    }

    public static final class Sounds {
        private static final Clip HOVER =
                ResourcesLoader.Audio.LoadClipFromFile(new File("equip.wav"));
        private static final Clip CLICK =
                ResourcesLoader.Audio.LoadClipFromFile(new File("use-item.wav"));
        private static final Clip MENU =
                ResourcesLoader.Audio.LoadClipFromFile(new File("menu.wav"));
        private static final Clip PLAY =
                ResourcesLoader.Audio.LoadClipFromFile(new File("playing.wav"));
        private static final Clip MONEY =
                ResourcesLoader.Audio.LoadClipFromFile(new File("money.wav"));

        public static final Clip GetAudioClip(String s) {
            return switch (s) {
                case "HOVER" -> HOVER;
                case "CLICK" -> CLICK;
                case "MENU" -> MENU;
                case "PLAY" -> PLAY;
                case "MONEY" -> MONEY;
                default -> throw new IllegalArgumentException("Audio clip not defined!");
            };
        }
    }

    public static final class Sprites {
        private static final BufferedImage
                LEGACY =
                        ResourcesLoader.Sprites.LoadSpriteFromFile(
                                new File("spriteatlas_legacy.png")),
                ACTORS =
                        ResourcesLoader.Sprites.LoadSpriteFromFile(
                                new File("spriteatlas_actors.png"));

        public static final BufferedImage GetSprite(String s) {
            return switch (s) {
                case "LEGACY" -> LEGACY;
                case "ACTORS" -> ACTORS;
                default -> throw new IllegalArgumentException("Sprite not defined!");
            };
        }
    }

    private MediaSource() {}
}
