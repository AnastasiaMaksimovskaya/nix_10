package ua.com.alevel;

import ua.com.alevel.utils.DataUtils;

public class Converter {
    private final long MILLISEC_IN_YEAR =31_536_000_000L;
    private final long MILLISEC_IN_BISSEXTILE_YEAR =31_622_400_000L;

    public long fromDateToMilliSeconds(MyData data) {
        DataUtils.fillMonthDayMap();
        long millesec = data.getMilliseconds() +
                fromSecondsToMilliSeconds(data.getSeconds()) +
                fromMinutesToMilliseconds(data.getMinutes()) +
                fromHoursToMilliseconds(data.getHours()) +
                fromDaysToMilliseconds(data.getDay()) +
                fromDaysToMilliseconds(fromMonthToDays(data.getMonth(),DataUtils.isBissextile(data.getYear())))+
                numberOfBissextile(data.getYear())*MILLISEC_IN_BISSEXTILE_YEAR+
                (data.getYear()-numberOfBissextile(data.getYear()))*MILLISEC_IN_YEAR;
                return millesec;
    }

    private int numberOfBissextile(int year) {
        int numberOfBissextile = 0;
        for (int i = 0; i < year; i++) {
            if (DataUtils.isBissextile(year)) {
                numberOfBissextile++;
            }
        }
        return numberOfBissextile;
    }

    private long fromSecondsToMilliSeconds(int sec) {
        return sec * 1000;
    }

    private long fromMinutesToMilliseconds(int min) {
        return fromSecondsToMilliSeconds(60 * min);
    }

    private long fromHoursToMilliseconds(int hours) {
        return fromSecondsToMilliSeconds(3600 * hours);
    }

    private long fromDaysToMilliseconds(int days) {
        return fromHoursToMilliseconds(24 * days);
    }

    private int fromMonthToDays(int month, boolean isBissextile) {
        int numberOfDays=0;
        if (month > 2) {
            for (int i = 1; i < month; i++) {
                numberOfDays+=DataUtils.getMonthAndDays().get(i);
            }
            if (isBissextile){
                numberOfDays++;
            }
        }
        else {
            for (int i = 1; i < month; i++) {
                numberOfDays+=DataUtils.getMonthAndDays().get(i);
            }
        }
        return numberOfDays;
    }
}
