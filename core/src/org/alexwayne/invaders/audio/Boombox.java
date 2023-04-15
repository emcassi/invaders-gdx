package org.alexwayne.invaders.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.Sound;

public class Boombox {
    AudioDevice audioDevice;
    Sound playerShoot, explosion1, explosion2;

    public Boombox() {
        audioDevice = Gdx.audio.newAudioDevice(1, false);

        playerShoot = Gdx.audio.newSound(Gdx.files.internal("sfx/shoot.mp3"));

        explosion1 = Gdx.audio.newSound(Gdx.files.internal("sfx/boom1.wav"));
        explosion2 = Gdx.audio.newSound(Gdx.files.internal("sfx/boom2.wav"));
    }

    public void playSound(SFX sound){
        switch(sound) {
            case PLAYER_SHOOT:
                playerShoot.play();
                break;
            case BOOM1:
                explosion1.play();
                break;
            case BOOM2:
                explosion2.play();
                break;
            default:
                break;
        }
    }

    public void setVolume(float volume){
        audioDevice.setVolume(volume);
    }
}
