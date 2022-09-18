package deque;

import edu.princeton.cs.algs4.Stopwatch;

public class TimeLinkedListDeque {
    private static void printTimingTable(LinkedListDeque<Integer> Ns, LinkedListDeque<Double> times, LinkedListDeque<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        LinkedListDeque<Integer> Ns  = new LinkedListDeque<>();
        LinkedListDeque<Double> times = new LinkedListDeque<>();
        LinkedListDeque<Integer> opCounts = new LinkedListDeque<>();
        int N = 100000;
        int operation_num = 1000000;
        int tableSize = 8;
        for (int i = 0; i < tableSize; i++) {
            Ns.addLast(N);
            LinkedListDeque<String> testList = new LinkedListDeque<>();

            for (int ops = 0; ops < N; ops++) {
                testList.addLast("CS61B");
            }

            Stopwatch sw = new Stopwatch();
            for (int ops = 0; ops < operation_num; ops++) {
                testList.get(N);
            }
            double timeInSeconds = sw.elapsedTime();

            times.addLast(timeInSeconds);
            opCounts.addLast(operation_num);

            N *= 2;
        }

        printTimingTable(Ns, times, opCounts);
    }
}
