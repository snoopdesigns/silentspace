package org.snoopdesigns.silentspace.core.audio;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioProcessor {
        private static Audio audio;
        private static Music main;
        static {
            audio = Gdx.audio;
        }

    public static void playIntro() {
        main = Gdx.audio.newMusic(Gdx.files.internal("audio/main.mp3"));
        main.play();
    }

    public static void stopIntro() {
        main.stop();
    }

    public static void playEffect(Sound sound) {
        sound.play();
    }
}
