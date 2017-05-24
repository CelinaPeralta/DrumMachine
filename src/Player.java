import javafx.scene.media.AudioClip;

import java.util.*;

/**
 * Created by celinaperalta on 5/17/17.
 */
public class Player {

    //loop has 16 beats, each beat contains arraylist for different instruments

    private static AudioClip[][] audioClips = new AudioClip[16][12];
    private boolean[][] beats = new boolean[16][12];

    public Player() {

        //initialize instrument grid
        for (int i = 0; i < audioClips.length; i++) {
            for (int j = 0; j < audioClips[i].length; j++) {
                audioClips[i][j] = DrumSounds.audioClips[j];
            }
        }

    }

    public void updateLoop(int instrument, boolean[] newLoop) {

        //will be called after each loop

        //assuming instrument is the index of the appropriate audioclip in the array
        for (int i = 0; i < audioClips.length; i++) {
            beats[i][instrument] = newLoop[i];
        }
        
    }

    public void clearLoop(String instrument) {

    }


}
