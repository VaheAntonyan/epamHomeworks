package consecutivethreadstask;

import java.util.Arrays;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        runThreads(Arrays.asList("Diana", "Vahe", "Grish", "Mary"));
    }

    private static void runThreads(List<String> names) {
        Thread[] threads = new Thread[names.size()];
        for (int i = 0; i < names.size(); ++i) {
            threads[i] = new Thread(new Printer(names.get(i), i, names.size()));
            threads[i].start();
        }
    }
}