package ua.com.alevel;

import ua.com.alevel.data.MyData;
import ua.com.alevel.exeption.InvalidInputException;
import ua.com.alevel.utils.DataUtils;

public class FromInputToData {
    private final String dd_mm_yyyy = "\\d{0,2}/\\d{0,2}/\\d{0,4}";
    private final String m_d_yyyy = "\\d{0,2}/\\d{0,2}/\\d{0,4}";
    private final String mmm_d_yyyy = "\\D+-\\d{0,2}-\\d{0,4}";
    private final String dd_mmm_yyyy = "\\d{0,2}-\\D+-\\d{0,4}";
    private final String hours_minutes_seconds_milliseconds = "\\d{0,2}:\\d{0,2}:\\d{0,2}:\\d{0,3}";
    private final String hours_minutes_seconds = "\\d{0,2}:\\d{0,2}:\\d{0,2}";
    private final String hours_minutes = "\\d{0,2}:\\d{0,2}";
    boolean isDayFirst;
    MyData data;

    public MyData getData() {
        return data;
    }

    public void setData(MyData data) {
        this.data = data;
    }

    public MyData convertInputToData(String input) throws InvalidInputException {
        data = new MyData();
        DataUtils.fillMonthMap();

        String[] dmyAndHmsm = input.split(" ");
        if (dmyAndHmsm.length == 1) {
            convertDaysMonthsYears(dmyAndHmsm[0]);
        }
        if (dmyAndHmsm.length == 2) {
            convertDaysMonthsYears(dmyAndHmsm[0]);
            convertHoursMinutesSecondsMilliseconds(dmyAndHmsm[1]);
        }
        isValid();
        return data;
    }

    private void convertFromFormatDdMmYyyy(String format) {
        String[] dayMonthYear = format.split("/");
        if (dayMonthYear[0].equals("")) {
            data.setDay(1);
        } else data.setDay(Integer.parseInt(dayMonthYear[0]));
        if (dayMonthYear[1].equals("")) {
            data.setMonth(1);
        } else data.setMonth(Integer.parseInt(dayMonthYear[1]));
        if (dayMonthYear[2].equals("")) {
            data.setYear(0);
        } else data.setYear(Integer.parseInt(dayMonthYear[2]));
    }

    private void convertFromFormatMmDdYyyy(String format) {
        String[] monthDayYear = format.split("/");
        if (monthDayYear[1].equals("")) {
            data.setDay(1);
        } else data.setDay(Integer.parseInt(monthDayYear[1]));
        if (monthDayYear[0].equals("")) {
            data.setMonth(1);
        } else data.setMonth(Integer.parseInt(monthDayYear[0]));
        if (monthDayYear[2].equals("")) {
            data.setYear(0);
        } else data.setYear(Integer.parseInt(monthDayYear[2]));
    }

    private void convertFromFormatMmmDdYyyy(String format) {
        String monthDayYear[] = format.split("-");
        if (monthDayYear[0].equals("")) {
            data.setMonth(1);
        } else data.setMonth(DataUtils.getMonthNames().get(monthDayYear[0]));
        if (monthDayYear[1].equals("")) {
            data.setDay(1);
        } else data.setDay(Integer.parseInt(monthDayYear[1]));
        if (monthDayYear[2].equals("")) {
            data.setYear(0);
        } else data.setYear(Integer.parseInt(monthDayYear[2]));
    }

    private void convertFromFormatDdMmmYyyy(String format) {
        String dayMonthYear[] = format.split("-");
        if (dayMonthYear[0].equals("")) {
            data.setDay(1);
        } else data.setDay(Integer.parseInt(dayMonthYear[0]));
        if (dayMonthYear[1].equals("")) {
            data.setMonth(1);
        } else data.setMonth(DataUtils.getMonthNames().get(dayMonthYear[1]));
        if (dayMonthYear[2].equals("")) {
            data.setYear(0);
        } else data.setYear(Integer.parseInt(dayMonthYear[2]));
    }

    private void convertFromHoursMinuteSecondMillisecond(String format) {
        String hourMinuteSecondMilliSecond[] = format.split(":");
        if (hourMinuteSecondMilliSecond[0].equals("")) {
            data.setHours(0);
        } else data.setHours(Integer.parseInt(hourMinuteSecondMilliSecond[0]));
        if (hourMinuteSecondMilliSecond[1].equals("")) {
            data.setMinutes(0);
        } else data.setMinutes(Integer.parseInt(hourMinuteSecondMilliSecond[1]));
        if (hourMinuteSecondMilliSecond[2].equals("")) {
            data.setSeconds(0);
        } else data.setSeconds(Integer.parseInt(hourMinuteSecondMilliSecond[2]));

        if (hourMinuteSecondMilliSecond[3].equals("")) {
            data.setMilliseconds(0);
        } else data.setMilliseconds(Integer.parseInt(hourMinuteSecondMilliSecond[3]));


    }

    private void convertFromHoursMinuteSecond(String format) {
        String hourMinuteSecond[] = format.split(":");
        if (hourMinuteSecond[0].equals("")) {
            data.setHours(0);
        } else data.setHours(Integer.parseInt(hourMinuteSecond[0]));
        if (hourMinuteSecond[1].equals("")) {
            data.setMinutes(0);
        } else data.setMinutes(Integer.parseInt(hourMinuteSecond[1]));
        if (hourMinuteSecond[2].equals("")) {
            data.setSeconds(0);
        } else data.setSeconds(Integer.parseInt(hourMinuteSecond[2]));
    }

    private void convertFromHoursMinute(String format) {
        String hourMinute[] = format.split(":");
        if (hourMinute[0].equals("")) {
            data.setHours(0);
        } else data.setHours(Integer.parseInt(hourMinute[0]));
        if (hourMinute[1].equals("")) {
            data.setMinutes(0);
        } else data.setMinutes(Integer.parseInt(hourMinute[1]));
    }

    private void convertDaysMonthsYears(String inputData) {
        if (inputData.matches(dd_mm_yyyy) && isDayFirst) {
            convertFromFormatDdMmYyyy(inputData);
        } else if (inputData.matches(m_d_yyyy) && !isDayFirst) {
            convertFromFormatMmDdYyyy(inputData);
        } else if (inputData.matches(mmm_d_yyyy)) {
            convertFromFormatMmmDdYyyy(inputData);
        } else if (inputData.matches(dd_mmm_yyyy)) {
            convertFromFormatDdMmmYyyy(inputData);
        }
    }

    private void convertHoursMinutesSecondsMilliseconds(String inputdata) {
        if (inputdata.matches(hours_minutes_seconds_milliseconds)) {
            convertFromHoursMinuteSecondMillisecond(inputdata);
        } else if (inputdata.matches(hours_minutes_seconds)) {
            convertFromHoursMinuteSecond(inputdata);
        } else if (inputdata.matches(hours_minutes)) {
            convertFromHoursMinute(inputdata);
        }
    }

    private void isValid() throws InvalidInputException {
        if (data.getHours() > 23 || data.getMilliseconds() > 999 || data.getSeconds() > 59 || data.getMinutes() > 59 || data.getMonth() > 12 || !isValidDay()) {
            throw new InvalidInputException();
        }
    }

    private boolean isValidDay() {
        int day = data.getDay();
        int month = data.getMonth();
        if ((month == 1 | month == 3 | month == 5 | month == 7 | month == 8 | month == 10 | month == 12) && day > 31) {
            return false;
        } else if ((month == 4 | month == 6 | month == 9 | month == 11) && day > 30) {
            return false;
        } else if (DataUtils.isBissextile(data.getYear()) && month == 2 && day > 29) {
            return false;
        } else if (!DataUtils.isBissextile(data.getYear()) && month == 2 && day > 28) {
            return false;
        }
        return true;
    }
}
