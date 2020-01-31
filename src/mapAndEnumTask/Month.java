package mapAndEnumTask;

import mapAndEnumTask.myExceptions.DayException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public enum Month {
    JANUARY(31, new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 28))),
    FEBRUARY(29, new ArrayList<Integer>(Collections.emptyList())),
    MARCH(31, new ArrayList<Integer>(Collections.singletonList(8))),
    APRIL(30, new ArrayList<Integer>(Arrays.asList(24, 25))),
    MAY(31, new ArrayList<Integer>(Arrays.asList(1, 9, 28))),
    JUNE(30, new ArrayList<Integer>(Collections.emptyList())),
    JULY(31, new ArrayList<Integer>(Collections.singletonList(5))),
    AUGUST(31, new ArrayList<Integer>(Collections.emptyList())),
    SEPTEMBER(30, new ArrayList<Integer>(Collections.singletonList(21))),
    OCTOBER(31, new ArrayList<Integer>(Collections.emptyList())),
    NOVEMBER(30, new ArrayList<Integer>(Collections.emptyList())),
    DECEMBER(31, new ArrayList<Integer>(Collections.singletonList(31)));

    private final ArrayList<Integer> holidays;
    private final int daysCount;

    Month(int daysCount, ArrayList<Integer> holidays) {
        this.holidays = holidays;
        this.daysCount = daysCount;
    }

    public ArrayList<Integer> getHolidays() {
        return holidays;
    }

    public int getDaysCount() {
        return daysCount;
    }

    public static boolean isPublicHoliday(Month month, int day) throws DayException {
        if (day < 0 || day > month.daysCount)
            throw new DayException(month.daysCount, day);
        return month.getHolidays().contains(day);
    }
}
