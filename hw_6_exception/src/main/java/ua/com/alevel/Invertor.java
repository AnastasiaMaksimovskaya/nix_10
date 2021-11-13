package ua.com.alevel;

import ua.com.alevel.utils.DataUtils;

public class Invertor {
    private final long MILLISEC_IN_YEAR = 31_536_000_000L;
    private final long MILLISEC_IN_BISSEXTILE_YEAR = 31_622_400_000L;
    private final long MILLISEC_IN_DAY = 86_400_000L;
    private int years;
    private int months;
    private int days;
    private int hours;
    private int minutes;
    private int seconds;
    private int milliseconds;

    public void extract(long milliseconds) {
        int day = 0;
        while (milliseconds / MILLISEC_IN_DAY > 0) {
            milliseconds -= MILLISEC_IN_DAY ;
            day++;
        }
        System.out.println("day = " + day);
        System.out.println("milliseconds = " + milliseconds);

    }
}
