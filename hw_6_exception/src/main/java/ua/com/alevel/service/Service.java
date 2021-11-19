package ua.com.alevel.service;

import ua.com.alevel.Converter;
import ua.com.alevel.Invertor;
import ua.com.alevel.data.MyData;

import java.util.Arrays;

public class Service {

    public MyData[] compareDatas(MyData... data) {
        Converter converter = new Converter();
        Invertor invertor = new Invertor();
        long dataMills[] = new long[data.length];
        for (int i = 0; i < dataMills.length; i++) {
            dataMills[i] = converter.fromDateToMilliseconds(data[i]);
        }
        Arrays.sort(dataMills);
        MyData[] myData = new MyData[dataMills.length];
        for (int i = 0; i < dataMills.length; i++) {
            myData[i] = invertor.extract(dataMills[i]);
        }
        return myData;
    }

    public MyData addMilliseconds(MyData data, long ms) {
        Converter converter = new Converter();
        Invertor invertor = new Invertor();
        long newMs = converter.fromDateToMilliseconds(data) + ms;
        MyData newData = invertor.extract(newMs);
        return newData;
    }

    public MyData addSeconds(MyData data, long s) {
        Converter converter = new Converter();
        Invertor invertor = new Invertor();
        long newMs = converter.fromDateToMilliseconds(data) + s * 1000;
        MyData newData = invertor.extract(newMs);
        return newData;
    }

    public MyData addMinutes(MyData data, long m) {
        Converter converter = new Converter();
        Invertor invertor = new Invertor();
        long newMs = converter.fromDateToMilliseconds(data) + m * 1000 * 60;
        MyData newData = invertor.extract(newMs);
        return newData;
    }

    public MyData addHours(MyData data, long h) {
        Converter converter = new Converter();
        Invertor invertor = new Invertor();
        long newMs = converter.fromDateToMilliseconds(data) + h * 1000 * 60 * 60;
        MyData newData = invertor.extract(newMs);
        return newData;
    }

    public MyData addDays(MyData data, long d) {
        Converter converter = new Converter();
        Invertor invertor = new Invertor();
        long newMs = converter.fromDateToMilliseconds(data) + d * 1000 * 60 * 60 * 24;
        MyData newData = invertor.extract(newMs);
        return newData;
    }

    public MyData addYear(MyData data, int y) {
        int currentYear = data.getYear();
        data.setHours(currentYear + y);
        return data;
    }

    public MyData subtractMilliseconds(MyData data, long ms) {
        Converter converter = new Converter();
        Invertor invertor = new Invertor();
        long newMs = converter.fromDateToMilliseconds(data) - ms;
        MyData newData = invertor.extract(newMs);
        return newData;
    }

    public MyData subtractSeconds(MyData data, long s) {
        Converter converter = new Converter();
        Invertor invertor = new Invertor();
        long newMs = converter.fromDateToMilliseconds(data) - s * 1000;
        MyData newData = invertor.extract(newMs);
        return newData;
    }

    public MyData subtractMinutes(MyData data, long m) {
        Converter converter = new Converter();
        Invertor invertor = new Invertor();
        long newMs = converter.fromDateToMilliseconds(data) - m * 1000 * 60;
        MyData newData = invertor.extract(newMs);
        return newData;
    }

    public MyData subtractHours(MyData data, long h) {
        Converter converter = new Converter();
        Invertor invertor = new Invertor();
        long newMs = converter.fromDateToMilliseconds(data) - h * 1000 * 60 * 60;
        MyData newData = invertor.extract(newMs);
        return newData;
    }

    public MyData subtractDays(MyData data, long d) {
        Converter converter = new Converter();
        Invertor invertor = new Invertor();
        long newMs = converter.fromDateToMilliseconds(data) - d * 1000 * 60 * 60 * 24;
        MyData newData = invertor.extract(newMs);
        return newData;
    }

    public MyData subtractYear(MyData data, int y) {
        int currentYear = data.getYear();
        data.setHours(currentYear - y);
        return data;
    }

    public long differenceInMilliseconds(MyData data1, MyData data2) {
        Converter converter = new Converter();
        long ms1 = converter.fromDateToMilliseconds(data1);
        long ms2 = converter.fromDateToMilliseconds(data2);
        long difference = Math.abs(ms2 - ms1);
        return difference;
    }

    public double differenceInSeconds(MyData data1, MyData data2) {
        Converter converter = new Converter();
        long ms1 = converter.fromDateToMilliseconds(data1);
        long ms2 = converter.fromDateToMilliseconds(data2);
        long difference = Math.abs(ms2 - ms1);
        return difference / 1000;
    }

    public double differenceInMinutes(MyData data1, MyData data2) {
        Converter converter = new Converter();
        long ms1 = converter.fromDateToMilliseconds(data1);
        long ms2 = converter.fromDateToMilliseconds(data2);
        long difference = Math.abs(ms2 - ms1);
        return difference / 60000;
    }

    public double differenceInHours(MyData data1, MyData data2) {
        Converter converter = new Converter();
        long ms1 = converter.fromDateToMilliseconds(data1);
        long ms2 = converter.fromDateToMilliseconds(data2);
        long difference = Math.abs(ms2 - ms1);

        return difference / (1000 * 3600);
    }

    public double differenceInDays(MyData data1, MyData data2) {
        return differenceInHours(data1, data2) / 24;
    }
}
