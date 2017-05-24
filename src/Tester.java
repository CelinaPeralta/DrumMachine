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


    public static void main(String[] args) throws Exception {

        Sound sound = new Sound("HiHat1.wav");
        Player player = new Player();

        boolean[] beat1 = new boolean[16];
        boolean[] beat2 = new boolean[16];
        boolean[] beat3 = new boolean[16];


        for (int i = 0; i < 16; i++) {
            if (i % 2 == 1) beat1[i] = true;
            if (i % 3 == 0) beat2[i] = true;
            beat3[i] = true;
        }

        player.updateLoop(1, beat1);
        player.updateLoop(2, beat2);
        player.updateLoop(0, beat3);

        for (int i = 0; i < 64; i++) {
            player.play();
            Thread.sleep(250);
        }


    }

}
