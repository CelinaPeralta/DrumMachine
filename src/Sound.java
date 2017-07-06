import javax.sound.sampled.*;
import java.io.File;

/**
 * Created by celinaperalta on 5/24/17.
 */

public class Sound {

    private static Clip clip;

    public Sound(String fileName) throws Exception {

        try {
            File file = new File(fileName);
            if (file.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                // load the sound into memory (a Clip)
                clip = AudioSystem.getClip();
                clip.open(sound);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setGain(float gain){ //-80 - 6
        FloatControl gainControl;
        gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(gain);
    }

    public synchronized void play(){
        clip.setFramePosition(0);  // Must always rewind!
        clip.start();
        //clip.loop(20);
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
