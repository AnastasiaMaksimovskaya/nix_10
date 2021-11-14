package ua.com.alevel;

import ua.com.alevel.data.MyData;
import ua.com.alevel.utils.DataUtils;

public class Converter {
    private final long MILLISEC_IN_DAY = 86_400_000L;


    private long fromDateToDays(MyData data) {
        DataUtils.fillMonthDayMap();
        long days = data.getDay() + fromMonthToDays(data.getMonth(), DataUtils.isBissextile(data.getYear()))
                + numberOfBissextile(data.getYear()) * 366
                + (data.getYear() - numberOfBissextile(data.getYear())) * 365;
        return days;
    }
    private long fromDaysToMilliseconds(long days){
        return days*MILLISEC_IN_DAY;
    }
    public long fromDateToMilliseconds(MyData data){
        return fromDaysToMilliseconds(fromDateToDays(data))+data.getMilliseconds()+data.getSeconds()*1000+
                data.getMinutes()*60*1000+data.getHours()*3600*1000;
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
