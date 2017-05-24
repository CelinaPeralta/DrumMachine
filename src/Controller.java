import java.util.ArrayList;
import java.util.List;

/**
 * Created by celinaperalta on 5/17/17.
 */


public class Controller {

    //Takes an array of instruments, a mixer, and a player and controls everything
    private Player player;
    private Mixer mixer;
    private int tempo;
    private String currentInstrument = "";

    public Controller() {

        player = new Player();

    }

    public void playLoop() {

    }

    public void addLoop(int instrument, boolean[] beats) {

        if(beats.length != 16)
            return;

        //where beats is a boolean array of size 16
        player.updateLoop(instrument, beats);

    }

    public void clearLoop(String instrument) {

    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }


}
