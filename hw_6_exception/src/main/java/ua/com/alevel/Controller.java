package ua.com.alevel;

import ua.com.alevel.data.MyData;
import ua.com.alevel.exeption.InvalidInputException;
import ua.com.alevel.service.Service;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Controller {
    String dataformat = "2";
    String regex;
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
                    break;
                case "9":
                    break;

            }
            navigation();
        }
    }

    private void navigation(){
        System.out.println("выберите опцию:" +
                "выбрать формат даты (по умолчанию mm/dd/yyyy)  нажмите 1\n" +
                "прибавить к дате миллисекунды нажмите 2\n" +
                "прибавить к дате секунды нажмите 3\n" +
                "прибавить к дате минуты нажмите 4\n" +
                "прибавить к дате часы нажмите 5\n" +
                "прибавить к дате дни нажмите 6\n" +
                "прибавить к дате года нажмите 7\n" +
                "отсортировать даты по хронологии нажмите 8\n" +
                "найти разницу между двумя датами 9\n");
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
                regex = "\\d{0,2}/\\d{0,2}/\\d{0,4}";
                break;
            case "2":
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
                dataformat = "5";
                regex = "\\d{0,2}/\\d{0,2}/\\d{0,4} \\d{0,2}:\\d{0,2}";
                break;
            case "6":
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
                dataformat = "9";
                regex = "\\d{0,2}/\\d{0,2}/\\d{0,4} \\d{0,2}:\\d{0,2}:\\d{0,2}";
                break;
            case "10":
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
                dataformat = "13";
                regex = "\\d{0,2}/\\d{0,2}/\\d{0,4} \\d{0,2}:\\d{0,2}:\\d{0,2}:\\d{0,3}";
                break;
            case "14":
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
            System.out.println(service.addMilliseconds(data, ms));
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
            System.out.println(service.addSeconds(data, ms));
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
            System.out.println(service.addMinutes(data, ms));
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
            System.out.println(service.addHours(data, ms));
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
            System.out.println(service.addDays(data, ms));
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
            System.out.println(service.addYear(data, ms));
        } catch (InvalidInputException e) {
            System.out.println(e);
        } catch (NumberFormatException exception) {
            System.out.println("неправильный ввод годов");
            return;
        }
    }
}
