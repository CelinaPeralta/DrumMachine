import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by celinaperalta on 5/24/17.
 */
/*
public class Sound {

    private TargetDataLine audio;
    private File file;

    public Sound(String fileName) throws Exception {

        file = new File(fileName);
        if (file.exists()) {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            // load the sound into memory (a Clip)
            audio = AudioSystem.getTargetDataLine(sound.getFormat());
        }
    }

    public void play(){
        audio.start();
    }

    public void stop(){
        audio.stop();
    }

    public void change(){
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            File newAudio = new File(File.createNewFile());

            AudioSystem.write(sound, AudioFileFormat.Type.WAVE, newAudio);
                    SourceDataLine srcDataLine = new SourceDataLine()
        }
        catch(Exception e){

        }
    }
}
*/

public class Sound {

    private Clip clip;

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

    public synchronized void play(){
        clip.setFramePosition(0);  // Must always rewind!
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }

}
