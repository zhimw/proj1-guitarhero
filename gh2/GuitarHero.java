package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 */
public class GuitarHero {

    /* Chars that are used for the strings. */
    static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";


    public static void main(String[] args) {

        /* Creates a list of guitar strings. */
        GuitarString keystrings[] = new GuitarString[37];
        for (int i = 0; i < 37; i++) {
            keystrings[i] = new GuitarString(440 * Math.pow(2, (i - 24) / 12));
        }

        int playtimes = 0;

        while (true) {
            GuitarString current = null;

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = Character.toLowerCase(StdDraw.nextKeyTyped());
                int index = keyboard.indexOf(key);

                if (index != -1) {
                    current = keystrings[index];
                    current.pluck();

                }

            }

            /* compute the superposition of samples */
            double sample = 0.0;
            for(GuitarString gs : keystrings) {
                sample += gs.sample();
            }

            /* play the sample on standard audio */

            StdAudio.play(sample);


            /* advance the simulation of each guitar string by one step */
            for(GuitarString gs : keystrings) {
                gs.tic();
            }

        }

    }
}

