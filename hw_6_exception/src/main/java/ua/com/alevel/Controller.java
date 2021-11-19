package ua.com.alevel;

import ua.com.alevel.data.MyData;
import ua.com.alevel.exeption.InvalidInputException;
import ua.com.alevel.service.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Controller {
    String dataformat = "2";
    String regex = "\\d{0,2}/\\d{0,2}/\\d{0,4}";
    Scanner scanner = new Scanner(System.in);

    public void run() {
        navigation();
        while (scanner.hasNext()) {
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    selectDataFormat();
                    break;
                case "2":
                    addMilliseconds(scanner);
                    break;
                case "3":
                    addSeconds(scanner);
                    break;
                case "4":
                    addMinutes(scanner);
                    break;
                case "5":
                    addHours(scanner);
                    break;
                case "6":
                    addDays(scanner);
                    break;
                case "7":
                    addYears(scanner);
                    break;
                case "8":
                    substractMilliseconds(scanner);
                    break;
                case "9":
                    substractSeconds(scanner);
                    break;
                case "10":
                    substractMinutes(scanner);
                    break;
                case "11":
                    substractHours(scanner);
                    break;
                case "12":
                    substractDays(scanner);
                    break;
                case "13":
                    substractYears(scanner);
                    break;
                case "14":
                    sortDatas(scanner);
                    break;
                case "15":
                    differenceInMilliseconds(scanner);
                    break;
                case "16":
                    differenceInSeconds(scanner);
                    break;
                case "17":
                    differenceInMinutes(scanner);
                    break;
                case "18":
                    differenceInHours(scanner);
                    break;
                case "19":
                    differenceInDays(scanner);
                    break;
                case "0":
                    System.exit(0);
            }
            navigation();
        }
    }

    private void navigation() {
        System.out.println("выберите опцию:" +
                "выбрать формат даты (по умолчанию mm/dd/yyyy)  нажмите 1\n" +
                "прибавить к дате миллисекунды нажмите 2\n" +
                "прибавить к дате секунды нажмите 3\n" +
                "прибавить к дате минуты нажмите 4\n" +
                "прибавить к дате часы нажмите 5\n" +
                "прибавить к дате дни нажмите 6\n" +
                "прибавить к дате года нажмите 7\n" +
                "вычесть из даты миллисекунды нажмите 8\n" +
                "вычесть из даты секунды нажмите 9\n" +
                "вычесть из даты минуты нажмите 10\n" +
                "вычесть из даты часы нажмите 11\n" +
                "вычесть из даты дни нажмите 12\n" +
                "вычесть из даты года нажмите 13\n" +
                "отсортировать даты по хронологии нажмите 14\n" +
                "найти разницу между двумя датами в миллисекундах 15\n" +
                "найти разницу между двумя датами в секундах 16\n" +
                "найти разницу между двумя датами в минутах 17\n" +
                "найти разницу между двумя датами в часах 18\n" +
                "найти разницу между двумя датами в днях 19\n" +
                "чтобы выйти нажмите 0"
        );
    }


    public void selectDataFormat() {
        System.out.println("dd/mm/yyyy press 1 \n" +
                "mm/dd/yyyy press 2 \n" +
                "mmm-dd-yyyy press 3\n" +
                "dd-mmm-yyyy press 4 \n" +
                "dd/mm/yyyy 00:00 press 5 \n" +
                "mm/dd/yyyy 00:00 press 6 \n" +
                "mmm-dd-yyyy 00:00 press 7\n" +
                "dd-mmm-yyyy 00:00 press 8 \n" +
                "dd/mm/yyyy 00:00:00 press 9 \n" +
                "mm/dd/yyyy 00:00:00 press 10 \n" +
                "mmm-dd-yyyy 00:00:00 press 11\n" +
                "dd-mmm-yyyy 00:00:00:000 press 12 \n" +
                "dd/mm/yyyy 00:00:00:000 press 13 \n" +
                "mm/dd/yyyy 00:00:00:000 press 14 \n" +
                "mmm-dd-yyyy 00:00:00:000 press 15\n" +
                "dd-mmm-yyyy 00:00:00:000 press 16 \n"
        );
        String choiceFormat = scanner.nextLine();
        switch (choiceFormat) {
            case "1":
                dataformat = "1";
                FromInputToData.isDayFirst = true;
                regex = "\\d{0,2}/\\d{0,2}/\\d{0,4}";
                break;
            case "2":
                FromInputToData.isDayFirst = false;
                dataformat = "2";
                regex = "\\d{0,2}/\\d{0,2}/\\d{0,4}";
                break;
            case "3":
                dataformat = "3";
                regex = "\\D+-\\d{0,2}-\\d{0,4}";
                break;
            case "4":
                dataformat = "4";
                regex = "\\d{0,2}-\\D+-\\d{0,4}";
                break;
            case "5":
                FromInputToData.isDayFirst = true;
                dataformat = "5";
                regex = "\\d{0,2}/\\d{0,2}/\\d{0,4} \\d{0,2}:\\d{0,2}";
                break;
            case "6":
                FromInputToData.isDayFirst = false;
                dataformat = "6";
                regex = "\\d{0,2}/\\d{0,2}/\\d{0,4} \\d{0,2}:\\d{0,2}";
                break;
            case "7":
                dataformat = "7";
                regex = "\\D+-\\d{0,2}-\\d{0,4} \\d{0,2}:\\d{0,2}";
                break;
            case "8":
                dataformat = "8";
                regex = "\\d{0,2}-\\D+-\\d{0,4} \\d{0,2}:\\d{0,2}";
                break;
            case "9":
                FromInputToData.isDayFirst = true;
                dataformat = "9";
                regex = "\\d{0,2}/\\d{0,2}/\\d{0,4} \\d{0,2}:\\d{0,2}:\\d{0,2}";
                break;
            case "10":
                FromInputToData.isDayFirst = false;
                dataformat = "10";
                regex = "\\d{0,2}/\\d{0,2}/\\d{0,4} \\d{0,2}:\\d{0,2}:\\d{0,2}";
                break;
            case "11":
                dataformat = "11";
                regex = "\\D+-\\d{0,2}-\\d{0,4} \\d{0,2}:\\d{0,2}:\\d{0,2}";
                break;
            case "12":
                dataformat = "12";
                regex = "\\d{0,2}-\\D+-\\d{0,4 \\d{0,2}:\\d{0,2}:\\d{0,2}}";
                break;
            case "13":
                FromInputToData.isDayFirst = true;
                dataformat = "13";
                regex = "\\d{0,2}/\\d{0,2}/\\d{0,4} \\d{0,2}:\\d{0,2}:\\d{0,2}:\\d{0,3}";
                break;
            case "14":
                FromInputToData.isDayFirst = false;
                dataformat = "14";
                regex = "\\d{0,2}/\\d{0,2}/\\d{0,4} \\d{0,2}:\\d{0,2}:\\d{0,2}:\\d{0,3}";
                break;
            case "15":
                dataformat = "15";
                regex = "\\D+-\\d{0,2}-\\d{0,4} \\d{0,2}:\\d{0,2}:\\d{0,2}:\\d{0,3}";
                break;
            case "16":
                dataformat = "16";
                regex = "\\d{0,2}-\\D+-\\d{0,4} \\d{0,2}:\\d{0,2}:\\d{0,2}:\\d{0,3}";
                break;
        }
    }

    private void addMilliseconds(Scanner scanner) {
        Service service = new Service();
        FromInputToData fromInputToData = new FromInputToData();
        try {
            System.out.println("введите дату в указаном формате");
            String date = scanner.nextLine();
            MyData data;
            if (date.matches(regex)) {
                data = fromInputToData.convertInputToData(date);
            } else {
                throw new InvalidInputException();
            }
            System.out.println("введите количество миллисекунд");
            long ms = Long.parseLong(scanner.nextLine());
            toOutput(service.addMilliseconds(data, ms));
        } catch (InvalidInputException e) {
            System.out.println(e);
        } catch (NumberFormatException exception) {
            System.out.println("неправильный ввод миллисекунд");
            return;
        }
    }

    private void addSeconds(Scanner scanner) {
        Service service = new Service();
        FromInputToData fromInputToData = new FromInputToData();
        try {
            System.out.println("введите дату в указаном формате");
            String date = scanner.nextLine();
            MyData data;
            if (date.matches(regex)) {
                data = fromInputToData.convertInputToData(date);
            } else {
                throw new InvalidInputException();
            }
            System.out.println("введите количество секунд");
            long ms = Long.parseLong(scanner.nextLine());
            toOutput(service.addSeconds(data, ms));
        } catch (InvalidInputException e) {
            System.out.println(e);
        } catch (NumberFormatException exception) {
            System.out.println("неправильный ввод секунд");
            return;
        }
    }

    private void addMinutes(Scanner scanner) {
        Service service = new Service();
        FromInputToData fromInputToData = new FromInputToData();
        try {
            System.out.println("введите дату в указаном формате");
            String date = scanner.nextLine();
            MyData data;
            if (date.matches(regex)) {
                data = fromInputToData.convertInputToData(date);
            } else {
                throw new InvalidInputException();
            }
            System.out.println("введите количество минут");
            long ms = Long.parseLong(scanner.nextLine());
            toOutput(service.addMinutes(data, ms));
        } catch (InvalidInputException e) {
            System.out.println(e);
        } catch (NumberFormatException exception) {
            System.out.println("неправильный ввод минут");
            return;
        }
    }

    private void addHours(Scanner scanner) {
        Service service = new Service();
        FromInputToData fromInputToData = new FromInputToData();
        try {
            System.out.println("введите дату в указаном формате");
            String date = scanner.nextLine();
            MyData data;
            if (date.matches(regex)) {
                data = fromInputToData.convertInputToData(date);
            } else {
                throw new InvalidInputException();
            }
            System.out.println("введите количество часов");
            long ms = Long.parseLong(scanner.nextLine());
            toOutput(service.addHours(data, ms));
        } catch (InvalidInputException e) {
            System.out.println(e);
        } catch (NumberFormatException exception) {
            System.out.println("неправильный ввод часов");
            return;
        }
    }

    private void addDays(Scanner scanner) {
        Service service = new Service();
        FromInputToData fromInputToData = new FromInputToData();
        try {
            System.out.println("введите дату в указаном формате");
            String date = scanner.nextLine();
            MyData data;
            if (date.matches(regex)) {
                data = fromInputToData.convertInputToData(date);
            } else {
                throw new InvalidInputException();
            }
            System.out.println("введите количество дней");
            long ms = Long.parseLong(scanner.nextLine());
            toOutput(service.addDays(data, ms));
        } catch (InvalidInputException e) {
            System.out.println(e);
        } catch (NumberFormatException exception) {
            System.out.println("неправильный ввод дней");
            return;
        }
    }

    private void addYears(Scanner scanner) {
        Service service = new Service();
        FromInputToData fromInputToData = new FromInputToData();
        try {
            System.out.println("введите дату в указаном формате");
            String date = scanner.nextLine();
            MyData data;
            if (date.matches(regex)) {
                data = fromInputToData.convertInputToData(date);
            } else {
                throw new InvalidInputException();
            }
            System.out.println("введите количество годов");
            int ms = Integer.parseInt(scanner.nextLine());
            toOutput(service.addYear(data, ms));
        } catch (InvalidInputException e) {
            System.out.println(e);
        } catch (NumberFormatException exception) {
            System.out.println("неправильный ввод годов");
            return;
        }
    }

    private void substractMilliseconds(Scanner scanner) {
        Service service = new Service();
        FromInputToData fromInputToData = new FromInputToData();
        try {
            System.out.println("введите дату в указаном формате");
            String date = scanner.nextLine();
            MyData data;
            if (date.matches(regex)) {
                data = fromInputToData.convertInputToData(date);
            } else {
                throw new InvalidInputException();
            }
            System.out.println("введите количество миллисекунд");
            long ms = Long.parseLong(scanner.nextLine());
            toOutput(service.subtractMilliseconds(data, ms));
        } catch (InvalidInputException e) {
            System.out.println(e);
        } catch (NumberFormatException exception) {
            System.out.println("неправильный ввод миллисекунд");
            return;
        }
    }

    private void substractSeconds(Scanner scanner) {
        Service service = new Service();
        FromInputToData fromInputToData = new FromInputToData();
        try {
            System.out.println("введите дату в указаном формате");
            String date = scanner.nextLine();
            MyData data;
            if (date.matches(regex)) {
                data = fromInputToData.convertInputToData(date);
            } else {
                throw new InvalidInputException();
            }
            System.out.println("введите количество секунд");
            long ms = Long.parseLong(scanner.nextLine());
            toOutput(service.subtractSeconds(data, ms));
        } catch (InvalidInputException e) {
            System.out.println(e);
        } catch (NumberFormatException exception) {
            System.out.println("неправильный ввод секунд");
            return;
        }
    }

    private void substractMinutes(Scanner scanner) {
        Service service = new Service();
        FromInputToData fromInputToData = new FromInputToData();
        try {
            System.out.println("введите дату в указаном формате");
            String date = scanner.nextLine();
            MyData data;
            if (date.matches(regex)) {
                data = fromInputToData.convertInputToData(date);
            } else {
                throw new InvalidInputException();
            }
            System.out.println("введите количество минут");
            long ms = Long.parseLong(scanner.nextLine());
            toOutput(service.subtractMinutes(data, ms));
        } catch (InvalidInputException e) {
            System.out.println(e);
        } catch (NumberFormatException exception) {
            System.out.println("неправильный ввод минут");
            return;
        }
    }

    private void substractHours(Scanner scanner) {
        Service service = new Service();
        FromInputToData fromInputToData = new FromInputToData();
        try {
            System.out.println("введите дату в указаном формате");
            String date = scanner.nextLine();
            MyData data;
            if (date.matches(regex)) {
                data = fromInputToData.convertInputToData(date);
            } else {
                throw new InvalidInputException();
            }
            System.out.println("введите количество часов");
            long ms = Long.parseLong(scanner.nextLine());
            toOutput(service.subtractHours(data, ms));
        } catch (InvalidInputException e) {
            System.out.println(e);
        } catch (NumberFormatException exception) {
            System.out.println("неправильный ввод часов");
            return;
        }
    }

    private void substractDays(Scanner scanner) {
        Service service = new Service();
        FromInputToData fromInputToData = new FromInputToData();
        try {
            System.out.println("введите дату в указаном формате");
            String date = scanner.nextLine();
            MyData data;
            if (date.matches(regex)) {
                data = fromInputToData.convertInputToData(date);
            } else {
                throw new InvalidInputException();
            }
            System.out.println("введите количество дней");
            long ms = Long.parseLong(scanner.nextLine());
            toOutput(service.subtractDays(data, ms));
        } catch (InvalidInputException e) {
            System.out.println(e);
        } catch (NumberFormatException exception) {
            System.out.println("неправильный ввод дней");
            return;
        }
    }

    private void substractYears(Scanner scanner) {
        Service service = new Service();
        FromInputToData fromInputToData = new FromInputToData();
        try {
            System.out.println("введите дату в указаном формате");
            String date = scanner.nextLine();
            MyData data;
            if (date.matches(regex)) {
                data = fromInputToData.convertInputToData(date);
            } else {
                throw new InvalidInputException();
            }
            System.out.println("введите количество годов");
            int ms = Integer.parseInt(scanner.nextLine());
            toOutput(service.subtractYear(data, ms));
        } catch (InvalidInputException e) {
            System.out.println(e);
        } catch (NumberFormatException exception) {
            System.out.println("неправильный ввод годов");
            return;
        }
    }

    private void sortDatas(Scanner scanner) {
        System.out.println("введите даты в указаном формате, для добавления каждой даты " +
                "после ее ввода нажмите enter,чтобы остановить ввод с новой строки нажмите 0");
        FromInputToData fromInputToData = new FromInputToData();
        List<MyData> datas = new ArrayList<>();
        while (true) {
            String data = scanner.nextLine();
            if (data.equals("0")) {
                break;
            } else {
                if (data.matches(regex)) {
                    try {
                        datas.add(fromInputToData.convertInputToData(data));
                    } catch (InvalidInputException e) {
                        System.out.println(e);
                        return;
                    }
                }
            }
        }
        Service service = new Service();
        MyData[] myData = datas.toArray(new MyData[0]);
        MyData[] myDataOutput = service.compareDatas(myData);
        for (int i = 0; i < myDataOutput.length; i++) {
            toOutput(myDataOutput[i]);
        }
    }

    private void toOutput(MyData data) {
        switch (dataformat) {
            case "1":
                System.out.println(FromDataToOuutput.fromDataToDDMMYYY(data));
                break;
            case "2":
                System.out.println(FromDataToOuutput.fromDataToMMDDYYY(data));
                break;
            case "3":
                System.out.println(FromDataToOuutput.fromDataToMMMMDDYYY(data));
                break;
            case "4":
                System.out.println(FromDataToOuutput.fromDataToDDMMMMYYY(data));
                break;
            case "5":
                System.out.println(FromDataToOuutput.fromDataToDDMMYYYHHMM(data));
                break;
            case "6":
                System.out.println(FromDataToOuutput.fromDataToMMDDYYYHHMM(data));
                break;
            case "7":
                System.out.println(FromDataToOuutput.fromDataToMMMMDDYYYHHMM(data));
                break;
            case "8":
                System.out.println(FromDataToOuutput.fromDataToDDMMMMYYYHHMM(data));
                break;
            case "9":
                System.out.println(FromDataToOuutput.fromDataToDDMMYYYHHMMSS(data));
                break;
            case "10":
                System.out.println(FromDataToOuutput.fromDataToMMDDYYYHHMMSS(data));
                break;
            case "11":
                System.out.println(FromDataToOuutput.fromDataToMMMMDDYYYHHMMSS(data));
                break;
            case "12":
                System.out.println(FromDataToOuutput.fromDataToDDMMMMYYYHHMMSS(data));
                break;
            case "13":
                System.out.println(FromDataToOuutput.fromDataToDDMMYYYHHMMSSMs(data));
                break;
            case "14":
                System.out.println(FromDataToOuutput.fromDataToMMDDYYYHHMMSSMs(data));
                break;
            case "15":
                System.out.println(FromDataToOuutput.fromDataToMMMMDDYYYHHMMSSMs(data));
                break;
            case "16":
                System.out.println(FromDataToOuutput.fromDataToDDMMMMYYYHHMMSSMs(data));
                break;
        }
    }

    private void differenceInMilliseconds(Scanner scanner) {
        FromInputToData fromInputToData = new FromInputToData();
        try {
            System.out.println("введите первую дату в указанном формате");
            String d1 = scanner.nextLine();
            if (!d1.matches(regex)) {
                throw new InvalidInputException();
            }
            MyData data1 = fromInputToData.convertInputToData(d1);
            System.out.println("введите вторую дату в указанном формате");
            String d2 = scanner.nextLine();
            if (!d2.matches(regex)) {
                throw new InvalidInputException();
            }
            MyData data2 = fromInputToData.convertInputToData(d2);
            System.out.println(new Service().differenceInMilliseconds(data1, data2));
        } catch (InvalidInputException e) {
            System.out.println(e);
        }
    }

    private void differenceInSeconds(Scanner scanner) {
        FromInputToData fromInputToData = new FromInputToData();
        try {
            System.out.println("введите первую дату в указанном формате");
            String d1 = scanner.nextLine();
            if (!d1.matches(regex)) {
                throw new InvalidInputException();
            }
            MyData data1 = fromInputToData.convertInputToData(d1);
            System.out.println("введите вторую дату в указанном формате");
            String d2 = scanner.nextLine();
            if (!d2.matches(regex)) {
                throw new InvalidInputException();
            }
            MyData data2 = fromInputToData.convertInputToData(d2);
            System.out.println(new Service().differenceInSeconds(data1, data2));
        } catch (InvalidInputException e) {
            System.out.println(e);
        }
    }

    private void differenceInMinutes(Scanner scanner) {
        FromInputToData fromInputToData = new FromInputToData();
        try {
            System.out.println("введите первую дату в указанном формате");
            String d1 = scanner.nextLine();
            if (!d1.matches(regex)) {
                throw new InvalidInputException();
            }
            MyData data1 = fromInputToData.convertInputToData(d1);
            System.out.println("введите вторую дату в указанном формате");
            String d2 = scanner.nextLine();
            if (!d2.matches(regex)) {
                throw new InvalidInputException();
            }
            MyData data2 = fromInputToData.convertInputToData(d2);
            System.out.println(new Service().differenceInMinutes(data1, data2));
        } catch (InvalidInputException e) {
            System.out.println(e);
        }
    }

    private void differenceInHours(Scanner scanner) {
        FromInputToData fromInputToData = new FromInputToData();
        try {
            System.out.println("введите первую дату в указанном формате");
            String d1 = scanner.nextLine();
            if (!d1.matches(regex)) {
                throw new InvalidInputException();
            }
            MyData data1 = fromInputToData.convertInputToData(d1);
            System.out.println("введите вторую дату в указанном формате");
            String d2 = scanner.nextLine();
            if (!d2.matches(regex)) {
                throw new InvalidInputException();
            }
            MyData data2 = fromInputToData.convertInputToData(d2);
            System.out.println(new Service().differenceInHours(data1, data2));
        } catch (InvalidInputException e) {
            System.out.println(e);
        }
    }

    private void differenceInDays(Scanner scanner) {
        FromInputToData fromInputToData = new FromInputToData();
        try {
            System.out.println("введите первую дату в указанном формате");
            String d1 = scanner.nextLine();
            if (!d1.matches(regex)) {
                throw new InvalidInputException();
            }
            MyData data1 = fromInputToData.convertInputToData(d1);
            System.out.println("введите вторую дату в указанном формате");
            String d2 = scanner.nextLine();
            if (!d2.matches(regex)) {
                throw new InvalidInputException();
            }
            MyData data2 = fromInputToData.convertInputToData(d2);
            System.out.println(new Service().differenceInDays(data1, data2));
        } catch (InvalidInputException e) {
            System.out.println(e);
        }
    }
}
