package ua.com.alevel;

import ua.com.alevel.data.MyData;
import ua.com.alevel.utils.DataUtils;

import java.util.Map;

public class FromDataToOuutput {

    public static String fromDataToDDMMYYY(MyData data) {
        return data.getDay() + "/" + data.getMonth() + "/" + data.getYear();
    }

    public static String fromDataToMMDDYYY(MyData data) {
        return data.getMonth() + "/" + data.getDay() + "/" + data.getYear();
    }

    public static String fromDataToDDMMMMYYY(MyData data) {
        DataUtils.fillMonthDayMap();
        return data.getDay() + "-" + getKey(DataUtils.getMonthNames(), data.getMonth()) + "-" + data.getYear();
    }

    public  static String fromDataToMMMMDDYYY(MyData data) {
        DataUtils.fillMonthDayMap();
        return getKey(DataUtils.getMonthNames(), data.getMonth()) + "-" + data.getDay() + "-" + data.getYear();
    }

    public static  String fromDataToDDMMYYYHHMM(MyData data) {
        return data.getDay() + "/" + data.getMonth() + "/" + data.getYear()+" "+ data.getHours()+":"+data.getMinutes();
    }

    public  static String fromDataToMMDDYYYHHMM(MyData data) {
        return data.getMonth() + "/" + data.getDay() + "/" + data.getYear()+" "+ data.getHours()+":"+data.getMinutes();
    }

    public static  String fromDataToDDMMMMYYYHHMM(MyData data) {
        DataUtils.fillMonthDayMap();
        return data.getDay() + "-" + getKey(DataUtils.getMonthNames(), data.getMonth()) + "-" + data.getYear()+" "+ data.getHours()+":"+data.getMinutes();
    }

    public static  String fromDataToMMMMDDYYYHHMM(MyData data) {
        DataUtils.fillMonthDayMap();
        return getKey(DataUtils.getMonthNames(), data.getMonth()) + "-" + data.getDay() + "-" + data.getYear()+" "+ data.getHours()+":"+data.getMinutes();
    }

    public static  String fromDataToDDMMYYYHHMMSS(MyData data) {
        return data.getDay() + "/" + data.getMonth() + "/" + data.getYear()+" "+ data.getHours()+":"+data.getMinutes()+":"+data.getSeconds();
    }

    public static  String fromDataToMMDDYYYHHMMSS(MyData data) {
        return data.getMonth() + "/" + data.getDay() + "/" + data.getYear()+" "+ data.getHours()+":"+data.getMinutes()+":"+data.getSeconds();
    }

    public  static String fromDataToDDMMMMYYYHHMMSS(MyData data) {
        DataUtils.fillMonthDayMap();
        return data.getDay() + "-" + getKey(DataUtils.getMonthNames(), data.getMonth()) + "-" + data.getYear()+" "+ data.getHours()+":"+data.getMinutes()+":"+data.getSeconds();
    }

    public static  String fromDataToMMMMDDYYYHHMMSS(MyData data) {
        DataUtils.fillMonthDayMap();
        return getKey(DataUtils.getMonthNames(), data.getMonth()) + "-" + data.getDay() + "-" + data.getYear()+" "+ data.getHours()+":"+data.getMinutes()+":"+data.getSeconds();
    }

    public  static String fromDataToDDMMYYYHHMMSSMs(MyData data) {
        return data.getDay() + "/" + data.getMonth() + "/" + data.getYear()+" "+ data.getHours()+":"+data.getMinutes()+":"+data.getSeconds()+":"+data.getMilliseconds();
    }

    public  static String fromDataToMMDDYYYHHMMSSMs(MyData data) {
        return data.getMonth() + "/" + data.getDay() + "/" + data.getYear()+" "+ data.getHours()+":"+data.getMinutes()+":"+data.getSeconds()+":"+data.getMilliseconds();
    }

    public static  String fromDataToDDMMMMYYYHHMMSSMs(MyData data) {
        DataUtils.fillMonthDayMap();
        return data.getDay() + "-" + getKey(DataUtils.getMonthNames(), data.getMonth()) + "-" + data.getYear()+" "+ data.getHours()+":"+data.getMinutes()+":"+data.getSeconds()+":"+data.getMilliseconds();
    }

    public static  String fromDataToMMMMDDYYYHHMMSSMs(MyData data) {
        DataUtils.fillMonthDayMap();
        return getKey(DataUtils.getMonthNames(), data.getMonth()) + "-" + data.getDay() + "-" + data.getYear()+" "+ data.getHours()+":"+data.getMinutes()+":"+data.getSeconds()+":"+data.getMilliseconds();
    }

    public static <K, V> K getKey(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
