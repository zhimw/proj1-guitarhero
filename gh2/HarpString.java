package gh2;

import deque.ArrayDeque;
import deque.Deque;


//Note: This file will not compile until you complete the Deque implementations
public class HarpString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = 0.989; // energy decay factor

    /* Buffer for storing sound data. */
    private Deque<Double> buffer;


    /* Create a guitar string of the given frequency.  */
    public HarpString(double frequency) {
        int capacity = (int) Math.round(SR / frequency / 2);
        buffer = new ArrayDeque<>();
        for (int i = 0; i < capacity; i++) {
            buffer.addFirst(0.0);
        }

    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        int size = buffer.size();
        for (int i = 0; i < size; i++) {
            double r = Math.random() - 0.5;
            buffer.addFirst(r);
            buffer.removeLast();
        }

    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double first = buffer.removeFirst();
        buffer.addLast(-DECAY * 0.5 * (first + buffer.get(0)));

    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.get(0);
    }
}
