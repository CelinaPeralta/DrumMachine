import javax.sound.sampled.*;
import java.io.File;

/**
 * Created by celinaperalta on 5/24/17.
 */
/*
public class Sound {

    private DataLine audio;
    private File file;

    public Sound(String fileName) throws Exception {

        file = new File(fileName);
        if (file.exists()) {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            // load the sound into memory (a Clip)
            SourceDataLine srcDataLine = AudioSystem.getSourceDataLine(sound.getFormat());
            int availableBytes = sound.available();
            byte[] b = new byte[availableBytes];
            sound.read(b);
            srcDataLine.write(b, 0, availableBytes);
            //interface SourceDataLine supporting format PCM_SIGNED 44100.0 Hz, 16 bit, stereo, 4 bytes/frame, little-endian
            srcDataLine.open(srcDataLine.getFormat());
            audio = srcDataLine;
        }
    }

    public void play(){
        System.out.println(audio);
        audio.start();
    }

    public void changeReverse(){
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);

            byte[] byteArray = new byte[sound.available()];
            int counter = sound.available();

            while(sound.available() >0){
                byteArray[counter] = (byte)sound.read();
                counter--;
            }

            SourceDataLine sourceDataLine = AudioSystem.getSourceDataLine(sound.getFormat());
            sourceDataLine.write(byteArray, 0, byteArray.length);
            sourceDataLine.open(sourceDataLine.getFormat());

            audio = sourceDataLine;



        }
        catch(Exception e){

        }
    }
}
=======
>>>>>>> Stashed changes

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

    public void setGain(float gain){ //-80 - 6
        FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
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
