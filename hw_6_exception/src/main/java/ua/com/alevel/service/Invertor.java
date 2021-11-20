package ua.com.alevel.service;

import ua.com.alevel.data.MyData;
import ua.com.alevel.utils.DataUtils;

public class Invertor {
    private final long MILLISEC_IN_DAY = 86_400_000L;
    MyData invertedData;

    public MyData extract(long milliseconds) {
        invertedData = new MyData();
        int day = 0;
        while (milliseconds / MILLISEC_IN_DAY > 0) {
            milliseconds -= MILLISEC_IN_DAY;
            day++;
        }
        int year = 0;
        while (day > 365) {
            if (DataUtils.isBissextile(year)) {
                if (day == 366) {
                    break;
                } else {
                    year++;
                    day -= 366;
                }
            } else {
                year++;
                day -= 365;
            }
        }
        int month = fromDayToMonth(day, year);
        day -= new Converter().fromMonthToDays(month, DataUtils.isBissextile(year));
        invertedData.setDay(day);
        invertedData.setMonth(month);
        invertedData.setYear(year);
        int invertedMilliseconds = (int) milliseconds % 1000;
        invertedData.setMilliseconds(invertedMilliseconds);
        int invrtedSeconds = (int) ((milliseconds - invertedMilliseconds) / 1000) % 60;
        invertedData.setSeconds(invrtedSeconds);
        int invertedHours = (int) (milliseconds / 3600000);
        invertedData.setHours(invertedHours);
        int invertedMinutes = (int) (milliseconds / 60000 - invertedHours * 60);
        invertedData.setMinutes(invertedMinutes);
        return invertedData;
    }

    private int fromDayToMonth(int days, int year) {
        int month = 0;
        int februaryAndJanuary = 0;
        if (DataUtils.isBissextile(year)) {
            februaryAndJanuary = 61;
        } else {
            februaryAndJanuary = 60;
        }
        if (days < 32) {
            month = 1;
        } else if (days < februaryAndJanuary) {
            month = 2;
        } else if (days < februaryAndJanuary + 31) {
            month = 3;
        } else if (days < februaryAndJanuary + 61) {
            month = 4;
        } else if (days < februaryAndJanuary + 92) {
            month = 5;
        } else if (days < februaryAndJanuary + 122) {
            month = 6;
        } else if (days < februaryAndJanuary + 153) {
            month = 7;
        } else if (days < februaryAndJanuary + 184) {
            month = 8;
        } else if (days < februaryAndJanuary + 214) {
            month = 9;
        } else if (days < februaryAndJanuary + 245) {
            month = 10;
        } else if (days < februaryAndJanuary + 275) {
            month = 11;
        } else if (days < februaryAndJanuary + 306) {
            month = 12;
        }
        return month;
    }
}
