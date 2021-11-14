package ua.com.alevel;

import ua.com.alevel.data.MyData;
import ua.com.alevel.utils.DataUtils;

public class Converter {
    private final long MILLISEC_IN_DAY = 86_400_000L;


    public long fromDateToDays(MyData data) {
        DataUtils.fillMonthDayMap();
        long days = data.getDay() + fromMonthToDays(data.getMonth(), DataUtils.isBissextile(data.getYear()))
                + numberOfBissextile(data.getYear()) * 366
                + (data.getYear() - numberOfBissextile(data.getYear())) * 365;
        return days;
    }
    public long fromDaysToMilliseconds(long days){
        return days*MILLISEC_IN_DAY;
    }

    private int numberOfBissextile(int year) {
        int numberOfBissextile = 0;
        for (int i = 0; i < year; i++) {
            if (DataUtils.isBissextile(i)) {
                numberOfBissextile++;
            }
        }
        return numberOfBissextile;
    }



    protected int fromMonthToDays(int month, boolean isBissextile) {
        int numberOfDays = 0;
        if (month > 2) {
            for (int i = 1; i < month; i++) {
                numberOfDays += DataUtils.getMonthAndDays().get(i);
            }
            if (isBissextile) {
                numberOfDays++;
            }
        } else {
            for (int i = 1; i < month; i++) {
                numberOfDays += DataUtils.getMonthAndDays().get(i);
            }
        }
        return numberOfDays;
    }
}
