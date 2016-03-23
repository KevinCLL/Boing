package boing;

import java.applet.Applet;
import java.applet.AudioClip;

public class Musica {

    public static final AudioClip BACKGROUND = Applet.newAudioClip(Musica.class.getResource("sound.wav"));
    public static final AudioClip GAMEOVER = Applet.newAudioClip(Musica.class.getResource("GameOver.wav"));
}
