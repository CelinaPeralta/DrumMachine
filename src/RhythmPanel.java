import javax.naming.ldap.Control;
import javax.sound.midi.Instrument;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by brian on 5/29/2017.
 */
public class RhythmPanel extends JPanel {

    private Player player;
    private int instrument;
    private boolean[] beats = new boolean[16];
    private JCheckBox[] jCheckBoxes;


    public RhythmPanel(Player player) {
        super(new BorderLayout());

        instrument = 1;

        this.player = player;

        setLayout(new GridLayout(1, 16, 5, 3));

        jCheckBoxes = new JCheckBox[16];

        for (int x = 0; x < jCheckBoxes.length; x++) {
            JCheckBox beat = new JCheckBox();
            beat.addActionListener(new BeatButtonListener(x));
            jCheckBoxes[x] = beat;
            this.add(beat);
        }
    }

    public void updateBeats(boolean[] beats) {
        for (int i = 0; i < jCheckBoxes.length; i++) {
            jCheckBoxes[i].setSelected(beats[i]);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setInstrument(int instrument, boolean[] newBeats) {
        this.instrument = instrument;
        beats = newBeats;
    }

    public boolean[] getBeats() {
        return beats;
    }

    public class BeatButtonListener implements ActionListener {

        private int beat;

        public BeatButtonListener(int beat) {
            this.beat = beat;
        }

        public void actionPerformed(ActionEvent e) {
            beats[beat] = !beats[beat];
            player.addLoop(instrument, beats);
        }
    }

    public void play() {
        player.play();
    }
}
