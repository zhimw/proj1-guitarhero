package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * A client that uses the synthesizer package to replicate a plucked harp string sound and a hit on a drum
 */
public class InstrumentsHero {

    /* Chars that are used for the strings. */
    static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    static String drumKeyboard = "QWERTY";


    public static void main(String[] args) {

        /* Creates a list of harp strings. */
        HarpString keystrings[] = new HarpString[37];
        for (int i = 0; i < 37; i++) {
            keystrings[i] = new HarpString(440 * Math.pow(2, (i - 24) / 12));
        }

        /* Creates a list of drum strikes. */
        Drum drumKeys[] = new Drum[6];
        for (int i = 0; i < 6; i++) {
            drumKeys[i] = new Drum(440 * Math.pow(2, (3 * i - 24) / 12));
        }


        while (true) {
            HarpString harpCurrent = null;
            Drum drumCurrent = null;

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int harpIndex = keyboard.indexOf(key);
                int drumIndex = drumKeyboard.indexOf(key);

                if (harpIndex != -1) {
                    harpCurrent = keystrings[harpIndex];
                    harpCurrent.pluck();
                }

                if (drumIndex != -1) {
                    drumCurrent = drumKeys[drumIndex];
                    drumCurrent.pluck();;
                }

            }
            /* compute the superposition of samples */
            double sample = 0.0;
            for(HarpString hs : keystrings) {
                sample += hs.sample();
            }
            /* play the sample on standard audio */
            StdAudio.play(sample);
            /* advance the simulation of each harp string by one step */
            for(HarpString hs : keystrings) {
                hs.tic();
            }

            for(Drum dm : drumKeys) {
                sample += dm.sample();
            }
            /* play the sample on standard audio */
            StdAudio.play(sample);
            /* advance the simulation of each drum strike by one step */
            for(Drum dm : drumKeys) {
                dm.tic();
            }
        }


    }
}

