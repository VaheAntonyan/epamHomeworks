package consecutivethreadstask;

public class Printer implements Runnable {
    private static int threadIdToRun = 1;
    private static int curIterationNumber = 1;
    private static int threadCount = -999;
    private int threadId;
    private String name;

    public Printer(String name, int threadId, int threadCount) {
        this.threadId = threadId;
        this.name = name;
        if (Printer.threadCount == -999) {
            Printer.threadCount = threadCount;
        }
    }

    @Override
    public void run() {
        try {
            while (curIterationNumber <= 20) {
                synchronized (Printer.class) {
                    if (threadId != threadIdToRun) {
                        Printer.class.wait();
                    } else {
                        System.out.printf("Iteration: %d, Thread: %d, Name: %s%n", curIterationNumber, threadId, name);
                        if (threadId == threadCount) {
                            ++curIterationNumber;
                        }
                        threadIdToRun = threadId % threadCount + 1;
                        Printer.class.notifyAll();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
