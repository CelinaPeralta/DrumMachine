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

        boolean[] beat1 = new boolean[16];
        boolean[] beat2 = new boolean[16];
        boolean[] beat3 = new boolean[16];
        boolean[] beat4 = new boolean[16];
        boolean[] beat5 = new boolean[16];

        for (int i = 0; i < 16; i++) {
            if (i % 7 == 1) beat1[i] = true;
            if (i % 12 == 0) beat1[i] = true;
            if (i % 6 == 0) beat2[i] = true;
            if (i % 3 == 0) beat2[i] = true;
            beat3[i] = true;
            beat4[i] = (Math.random() < 0.5);
            beat5[i] = (Math.random() < 0.5);
        }

        player.addLoop(1, beat1);
        player.addLoop(2, beat2);
        player.addLoop(0, beat3);

        playLoop(2, 200);

        player.clearLoop(0);
        player.addLoop(2, beat4);

        playLoop(1, 200);

        player.addLoop(2, beat5);

        playLoop(1, 200);

        player.addLoop(2, beat2);
        player.addLoop(0, beat3);

        playLoop(2, 200);
    }

    private static void playLoop(int bars, int tempo) throws Exception {
        for (int i = 0; i < bars * 16; i++) {
            player.play();
            Thread.sleep(tempo);
        }
    }

}
