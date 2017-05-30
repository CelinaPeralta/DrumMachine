import javafx.scene.media.AudioClip;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by celinaperalta on 5/17/17.
 */


public class Tester {

    private static Player player;

    public static void main(String[] args) throws Exception {

        player = new Player();
        int TEMPO = 200;

        boolean[] beat1 = new boolean[16];
        boolean[] beat2 = new boolean[16];
        boolean[] beat3 = new boolean[16];

        for (int i = 0; i < 16; i++) {
            if (i % 3 == 1) beat1[i] = true;
            if (i % 4 == 0) beat1[i] = true;
            if (i % 3 == 1) beat2[i] = true;
            beat3[i] = true;
        }

        //0 = hihat, 1 = kick, 2 = snare
        player.addLoop(0, beat3);
        player.addLoop(1, beat1);
        player.addLoop(2, beat2);

        playLoop(2, TEMPO);

        for (int i = 0; i < 4; i++) {
            player.addLoop(0, randomBeat());
            player.addLoop(2, randomBeat());
            playLoop(1, TEMPO);

        }

        player.addLoop(1, beat1);
        player.addLoop(2, beat2);
        player.addLoop(0, beat3);

        playLoop(4, TEMPO);
    }

    private static void playLoop(int bars, int tempo) throws Exception {
        for (int i = 0; i < bars * 16; i++) {
            player.play();
            Thread.sleep(tempo);
        }
    }

    private static boolean[] randomBeat() {
        boolean[] beat = new boolean[16];

        for (int i = 0; i < 16; i++) {
            beat[i] = (Math.random() < 0.6);
        }

        return beat;

    }

}
