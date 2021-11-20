package ua.com.alevel.service;

import ua.com.alevel.data.MyData;
import ua.com.alevel.exeption.NegatoryTimeException;
import ua.com.alevel.exeption.OutOfEraException;
import ua.com.alevel.exeption.OutOfTenThousandException;

import java.util.Arrays;

public class Service {
    private final long CALENDAR_MAXVALUE_MS = 315569606399999L;

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

    public MyData addMilliseconds(MyData data, long ms) throws OutOfTenThousandException, NegatoryTimeException {
        if (ms < 0) {
            throw new NegatoryTimeException();
        }
        Converter converter = new Converter();
        Invertor invertor = new Invertor();
        long newMs = converter.fromDateToMilliseconds(data) + ms;
        if (newMs > CALENDAR_MAXVALUE_MS) {
            throw new OutOfTenThousandException();
        }
        MyData newData = invertor.extract(newMs);
        return newData;
    }

    public MyData addSeconds(MyData data, long s) throws OutOfTenThousandException, NegatoryTimeException {
        if (s < 0) {
            throw new NegatoryTimeException();
        }
        Converter converter = new Converter();
        Invertor invertor = new Invertor();
        long newMs = converter.fromDateToMilliseconds(data) + s * 1000;
        if (newMs > CALENDAR_MAXVALUE_MS) {
            throw new OutOfTenThousandException();
        }
        MyData newData = invertor.extract(newMs);
        return newData;
    }

    public MyData addMinutes(MyData data, long m) throws OutOfTenThousandException, NegatoryTimeException {
        if (m < 0) {
            throw new NegatoryTimeException();
        }
        Converter converter = new Converter();
        Invertor invertor = new Invertor();
        long newMs = converter.fromDateToMilliseconds(data) + m * 1000 * 60;
        if (newMs > CALENDAR_MAXVALUE_MS) {
            throw new OutOfTenThousandException();
        }
        MyData newData = invertor.extract(newMs);
        return newData;
    }

    public MyData addHours(MyData data, long h) throws OutOfTenThousandException, NegatoryTimeException {
        if (h < 0) {
            throw new NegatoryTimeException();
        }
        Converter converter = new Converter();
        Invertor invertor = new Invertor();
        long newMs = converter.fromDateToMilliseconds(data) + h * 1000 * 60 * 60;
        if (newMs > CALENDAR_MAXVALUE_MS) {
            throw new OutOfTenThousandException();
        }
        MyData newData = invertor.extract(newMs);
        return newData;
    }

    public MyData addDays(MyData data, long d) throws OutOfTenThousandException, NegatoryTimeException {
        if (d < 0) {
            throw new NegatoryTimeException();
        }
        Converter converter = new Converter();
        Invertor invertor = new Invertor();
        long newMs = converter.fromDateToMilliseconds(data) + d * 1000 * 60 * 60 * 24;
        if (newMs > CALENDAR_MAXVALUE_MS) {
            throw new OutOfTenThousandException();
        }
        MyData newData = invertor.extract(newMs);
        return newData;
    }

    public MyData addYear(MyData data, int y) throws OutOfTenThousandException, NegatoryTimeException {
        if (y < 0) {
            throw new NegatoryTimeException();
        }
        int currentYear = data.getYear();
        if (currentYear + y > 9999) {
            throw new OutOfTenThousandException();
        }
        data.setYear(currentYear + y);
        return data;
    }

    public MyData subtractMilliseconds(MyData data, long ms) throws OutOfEraException, NegatoryTimeException {
        if (ms < 0) {
            throw new NegatoryTimeException();
        }
        Converter converter = new Converter();
        Invertor invertor = new Invertor();
        long newMs = converter.fromDateToMilliseconds(data) - ms;
        if (newMs < 0) {
            throw new OutOfEraException();
        }
        MyData newData = invertor.extract(newMs);
        return newData;
    }

    public MyData subtractSeconds(MyData data, long s) throws OutOfEraException, NegatoryTimeException {
        if (s < 0) {
            throw new NegatoryTimeException();
        }
        Converter converter = new Converter();
        Invertor invertor = new Invertor();
        long newMs = converter.fromDateToMilliseconds(data) - s * 1000;
        if (newMs < 0) {
            throw new OutOfEraException();
        }
        MyData newData = invertor.extract(newMs);
        return newData;
    }

    public MyData subtractMinutes(MyData data, long m) throws OutOfEraException, NegatoryTimeException {
        if (m < 0) {
            throw new NegatoryTimeException();
        }
        Converter converter = new Converter();
        Invertor invertor = new Invertor();
        long newMs = converter.fromDateToMilliseconds(data) - m * 1000 * 60;
        if (newMs < 0) {
            throw new OutOfEraException();
        }
        MyData newData = invertor.extract(newMs);
        return newData;
    }

    public MyData subtractHours(MyData data, long h) throws OutOfEraException, NegatoryTimeException {
        if (h < 0) {
            throw new NegatoryTimeException();
        }
        Converter converter = new Converter();
        Invertor invertor = new Invertor();
        long newMs = converter.fromDateToMilliseconds(data) - h * 1000 * 60 * 60;
        if (newMs < 0) {
            throw new OutOfEraException();
        }
        MyData newData = invertor.extract(newMs);
        return newData;
    }

    public MyData subtractDays(MyData data, long d) throws OutOfEraException, NegatoryTimeException {
        if (d < 0) {
            throw new NegatoryTimeException();
        }
        Converter converter = new Converter();
        Invertor invertor = new Invertor();

        long newMs = converter.fromDateToMilliseconds(data) - d * 1000 * 60 * 60 * 24;
        if (newMs < 0) {
            throw new OutOfEraException();
        }
        MyData newData = invertor.extract(newMs);
        return newData;
    }

    public MyData subtractYear(MyData data, int y) throws OutOfEraException, NegatoryTimeException {
        if (y < 0) {
            throw new NegatoryTimeException();
        }
        int currentYear = data.getYear();
        if ((currentYear - y) < 0) {
            throw new OutOfEraException();
        }
        data.setYear(currentYear - y);
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
