package consecutivethreadstask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        runThreads(Arrays.asList("Diana", "Vahe", "Grish", "Mary"));
    }

    private static void runThreads(List<String> names) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < names.size(); ) {
            threads.add(new Thread(new Printer(names.get(i), ++i, names.size())));
        }

        for (Thread t : threads) {
            t.start();
        }
    }
}