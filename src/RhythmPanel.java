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

    private Controller controller;
    private int instrument;
    private boolean[] beats = new boolean[16];


    public RhythmPanel(Player player){
        super(new BorderLayout());

        instrument = 0;

        this.controller = controller;

        setLayout(new GridLayout(1,16,5,3));

        JCheckBox[] jCheckBoxes = new JCheckBox[16];

        for(int x = 0; x < jCheckBoxes.length; x++){
            JCheckBox beat = new JCheckBox();
            beat.addActionListener(new BeatButtonListener(1));

            this.add(beat);
        }
        /*
        JCheckBox beat2 = new JCheckBox();
        JCheckBox beat3 = new JCheckBox();
        JCheckBox beat4 = new JCheckBox();
        JCheckBox beat5 = new JCheckBox();
        JCheckBox beat6 = new JCheckBox();
        JCheckBox beat7 = new JCheckBox();
        JCheckBox beat8 = new JCheckBox();
        JCheckBox beat9 = new JCheckBox();
        JCheckBox beat10 = new JCheckBox();
        JCheckBox beat11 = new JCheckBox();
        JCheckBox beat12 = new JCheckBox();
        JCheckBox beat13 = new JCheckBox();
        JCheckBox beat14 = new JCheckBox();
        JCheckBox beat15 = new JCheckBox();
        JCheckBox beat16 = new JCheckBox();

        beat1.addActionListener(new BeatButtonListener(1));
        beat1.addActionListener(new BeatButtonListener(1));
        beat1.addActionListener(new BeatButtonListener(1));
        beat1.addActionListener(new BeatButtonListener(1));
        beat1.addActionListener(new BeatButtonListener(1));
        beat1.addActionListener(new BeatButtonListener(1));
        beat1.addActionListener(new BeatButtonListener(1));
        beat1.addActionListener(new BeatButtonListener(1));
        beat1.addActionListener(new BeatButtonListener(1));
        beat1.addActionListener(new BeatButtonListener(1));
        beat1.addActionListener(new BeatButtonListener(1));
        beat1.addActionListener(new BeatButtonListener(1));
        beat1.addActionListener(new BeatButtonListener(1));
        beat1.addActionListener(new BeatButtonListener(1));
        beat1.addActionListener(new BeatButtonListener(1));
        beat1.addActionListener(new BeatButtonListener(1));
        */

    }

    public Controller getController(){
        return controller;
    }

    public void setInstrument(int instrument){
        this.instrument = instrument;
        beats = new boolean[16];
    }

    public class BeatButtonListener implements ActionListener{

        private int beat;

        public BeatButtonListener(int beat){
            this.beat = beat;
        }

        public void actionPerformed(ActionEvent e){
            beats[beat] = !beats[beat];
            controller.addLoop(instrument, beats);
        }
    }
}
