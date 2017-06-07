import javafx.scene.media.AudioClip;

import java.util.*;

/**
 * Created by celinaperalta on 5/17/17.
 */
public class Player {

    //loop has 16 beats, each beat contains arraylist for different instruments

    private Sound[][] audioClips = new Sound[16][DrumSounds.NUM_SOUNDS];
    private boolean[][] beats = new boolean[16][DrumSounds.NUM_SOUNDS];
    private static int beat_count = 0;
    private boolean timeSignature4 = true;
    private short totalBeats = 15;


    public Player() throws Exception {

        //initialize instrument grid
        for (int i = 0; i < audioClips.length; i++) {
            for (int j = 0; j < audioClips[i].length; j++) {
                audioClips[i][j] = new Sound(DrumSounds.audioNames[j]);
            }
        }
    }

    public void addLoop(int instrument, boolean[] newLoop) {

        //will be called after each loop

        //assuming instrument is the index of the appropriate audioclip in the array
        for (int i = 0; i < audioClips.length; i++) {
            beats[i][instrument] = newLoop[i];
        }
    }

    public synchronized void clearLoop() {
        for (int y = 0; y < beats.length; y++) {
            for (int x = 0; x < beats[y].length; x++)
                beats[y][x] = false;
        }
    }

    public int getBeat() {
        return beat_count;
    }

    public void setTimeSignature4(boolean timeSignature) {
        timeSignature4 = timeSignature;
        if (timeSignature4) {
            totalBeats = 15;
        } else if (!timeSignature4) {
            totalBeats = 11;
        }
    }

    public void play() {

        if (beat_count >= totalBeats)
            beat_count = 0;
        else
            beat_count++;

        for (int j = 0; j < audioClips[beat_count].length; j++) {
            if (beats[beat_count][j]) {
                audioClips[beat_count][j].play();
                System.out.println("test2");
            }
        }

    }
}
