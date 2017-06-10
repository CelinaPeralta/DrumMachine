/**
 * Created by celinaperalta on 5/22/17.
 */


public class DrumSounds {

    public static final int NUM_SOUNDS = 10;

    public Sound CH;
    public Sound BD;
    public Sound SD;
    public Sound CL;
    public Sound HT;
    public Sound MT;
    public Sound LT;
    public Sound RC;
    public Sound CR;
    public Sound SN;


    public static Sound[] audioClips;
    public static String[] audioNames = new String[]{"HiHat.wav", "BassDrum.wav", "Snare.wav","Clap.wav", "HiTom", "MedTom.wav", "LowTom.wav", "RideCymbal.wav", "Crash.wav", "Snap.wav"};


    public DrumSounds() throws Exception {

        this.CH = new Sound(audioNames[0]);
        this.BD = new Sound(audioNames[1]);
        this.SD = new Sound(audioNames[2]);
        this.CL = new Sound(audioNames[3]);
        this.HT = new Sound(audioNames[4]);
        this.MT = new Sound(audioNames[5]);
        this.LT = new Sound(audioNames[6]);
        this.RC = new Sound(audioNames[7]);
        this.CR = new Sound(audioNames[8]);
        this.SN = new Sound(audioNames[9]);

        audioClips = new Sound[]{CH, BD, SD, CL, HT, MT, LT, RC, CR, SN};

    }
}
