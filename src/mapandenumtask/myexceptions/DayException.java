package mapandenumtask.myexceptions;

public class DayException extends Exception {
    private static String ERROR_MSG = "Invalid Day: Day must be between [0, %s]: Provided day: %s";
    public DayException(int dayCount, int provided) {
        super(String.format(ERROR_MSG, dayCount, provided));
    }
}
