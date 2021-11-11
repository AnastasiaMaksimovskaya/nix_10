package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

public class CollectionController {
    MathSet mathSet;
    MathSet ms;
    String stringArray[];
    Number numberArray[];
    String input;

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("select your option");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                go(position, reader);
                position = reader.readLine();
                if (position.equals("0")) {
                    System.exit(0);
                }
                go(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void runNavigation() {
        System.out.println();
        System.out.println("чтоб создать динамический MathSet с начальным размером 0 нажмите 1");
        System.out.println("чтоб создать MathSet с опредеоенным размером нажмите 2");
        System.out.println("чтоб создать динамический MathSet из массива нажмите 3");
        System.out.println("чтоб создать динамический MathSet из нескольких массивов нажмите 4");
        System.out.println("чтоб создать динамический MathSet из MathSet'a нажмите 5");
        System.out.println("чтоб создать динамический MathSet из MathSet'ов5 нажмите 6");
        System.out.println("чтоб добавить элемент, нажмите 7");
        System.out.println("чтоб добавить несколько элементов нажмите 8");
        System.out.println("чтоб найти объединение с другим Mathset'ом нажмите 9");
        System.out.println("чтоб найти объединение с другими Mathset'ами нажмите 10");
        System.out.println("чтоб найти пересечение с другим Mathset'ом нажмите 11");
        System.out.println("чтоб найти пересечение с другими Mathset'ами нажмите 12");
        System.out.println("чтоб отсортировать MathSet по убыванию нажмите 13");
        System.out.println("чтоб отсортировать часть MathSet'а по убыванию нажмите 14");
        System.out.println("чтоб отсортировать часть MathSet'а по убыванию, начиная с некоторого значения нажмите 15");
        System.out.println("чтоб отсортировать MathSet по возрастанию нажмите 16");
        System.out.println("чтоб отсортировать часть MathSet'а по возрастанию нажмите 17");
        System.out.println("чтоб отсортировать часть MathSet'а по возрастанию, начиная с некоторого значения нажмите 18");
        System.out.println("чтоб найти элемент по индексу, нажмите 19");
        System.out.println("чтоб найти минимальное значение, нажмите 20");
        System.out.println("чтоб найти максимальное значение, нажмите 21");
        System.out.println("чтоб найти среднее значение, нажмите 22");
        System.out.println("чтоб найти медиану выборки, нажмите 23");
        System.out.println("чтоб очистить MathSet нажмите 24");
        System.out.println("чтоб вырезать из MathSet'а часть нажмите 25");
        System.out.println("чтоб очистить из MathSet'а элементы массива нажмите 26");
        System.out.println("чтоб выйти нажмите 0");
        System.out.println();
    }

    private void go(String position, BufferedReader reader) throws Exception {
        switch (position) {
            case "1":
                createMathSet();
                break;
            case "2":
                createMathSetWithCapacity(reader);
                break;
            case "3":
                createMathSetFromArray(reader);
                break;
            case "4":
                createMathSetFromArrayVarargs(reader);
                break;
            case "5":
                System.out.println("введите элементы матсета через пробел, чтоб завершить, нажмите Enter");
                input = reader.readLine();
                mathSet = new MathSet(fromInputMathset(input));
                break;
            case "6":
                System.out.println("Введите целочисленные значения массива через пробела, чтоб начать новый массив, " +
                        "разделите массивы с помощью ';' , для конца ввода намите enter");
                input = reader.readLine();
                mathSet = new MathSet(fromInputToVarargsMathsets(input));
                break;
            case "7":
                System.out.println("введите число");
                mathSet.add(parseNumber(reader.readLine()));
                break;
            case "8":
                System.out.println("введите элементы массива через пробел, чтоб завершить, нажмите Enter");
                input = reader.readLine();
                stringArray = input.split(" ");
                numberArray = new Number[stringArray.length];
                for (int i = 0; i < numberArray.length; i++) {
                    numberArray[i] = parseNumber(stringArray[i]);
                }
                mathSet.add(numberArray);
                break;
            case "9":
                System.out.println("введите элементы нового матсета через пробел, чтоб завершить, нажмите Enter");
                input = reader.readLine();
                stringArray = input.split(" ");
                numberArray = new Number[stringArray.length];
                for (int i = 0; i < numberArray.length; i++) {
                    numberArray[i] = parseNumber(stringArray[i]);
                }
                ms = new MathSet(numberArray);
                mathSet.join(ms);
                break;
            case "10":
                System.out.println("Введите целочисленные значения массива через пробела, чтоб начать новый массив, " +
                        "разделите массивы с помощью ';' , для конца ввода намите enter");
                input = reader.readLine();
                mathSet.join(fromInputToVarargsMathsets(input));
                break;
            case "11":
                input = reader.readLine();
                stringArray = input.split(" ");
                numberArray = new Number[stringArray.length];
                for (int i = 0; i < numberArray.length; i++) {
                    numberArray[i] = parseNumber(stringArray[i]);
                }
                ms = new MathSet(numberArray);
                mathSet.intersection(ms);
                break;
            case "12":
                System.out.println("Введите численные значения массива через пробела, чтоб начать новый массив, " +
                        "разделите массивы с помощью ';' , для конца ввода намите enter");
                System.out.println("Например:2 3;9 10;1 739 -5 0.25");
                input = reader.readLine();
                mathSet.intersection(fromInputToVarargsMathsets(input));
                break;
            case "13":
                mathSet.sortDesc();
                break;
            case "14":
                int firstIndex = 0;
                int lastIndex = 0;
                try {
                    System.out.println("введите начальный индекс");
                    firstIndex = Integer.parseInt(reader.readLine());
                    System.out.println("введите конечный индекс");
                    lastIndex = Integer.parseInt(reader.readLine());
                } catch (Exception e) {
                    System.out.println("неправильный ввод данных");
                }
                mathSet.sortDesc(firstIndex, lastIndex);
                break;
            case "15":
                int value = 0;
                try {
                    System.out.println("введите значение");
                    value = Integer.parseInt(reader.readLine());
                } catch (NoSuchElementException e) {
                    System.out.println("такого элемента нет в матсете");
                } catch (NumberFormatException exception) {
                    System.out.println("неправильный вод данных");
                }
                mathSet.sortDesc(value);
                break;
            case "16":
                mathSet.sortAsc();
                break;
            case "17":
                firstIndex = 0;
                lastIndex = 0;
                try {
                    System.out.println("введите начальный индекс");
                    firstIndex = Integer.parseInt(reader.readLine());
                    System.out.println("введите конечный индекс");
                    lastIndex = Integer.parseInt(reader.readLine());
                } catch (Exception e) {
                    System.out.println("неправильный ввод данных");
                }
                mathSet.sortAsc(firstIndex, lastIndex);
                break;
            case "18":
                value = 0;
                try {
                    System.out.println("введите значение");
                    value = Integer.parseInt(reader.readLine());
                } catch (NoSuchElementException e) {
                    System.out.println("такого элемента нет в матсете");
                } catch (NumberFormatException exception) {
                    System.out.println("неправильный вод данных");
                }
                mathSet.sortAsc(value);
                break;
            case "19":
                int index = 0;
                try {
                    System.out.println("введите индекс");
                    index = Integer.parseInt(reader.readLine());
                } catch (NumberFormatException exception) {
                    System.out.println("неправильный вод данных");
                }
                try {
                    System.out.println(mathSet.get(index));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("вы вышли за пределы матсета");
                }
                break;
            case "20":
                System.out.println(mathSet.getMin());
                break;
            case "21":
                System.out.println(mathSet.getMax());
                break;
            case "22":
                System.out.println(mathSet.getAverage());
                break;
            case "23":
                System.out.println(mathSet.getMedian());
                break;
            case "24":
                mathSet.clear();
                break;
            case "25":
                firstIndex = 0;
                lastIndex = 0;
                try {
                    System.out.println("введите начальный индекс");
                    firstIndex = Integer.parseInt(reader.readLine());
                    System.out.println("введите конечный индекс");
                    lastIndex = Integer.parseInt(reader.readLine());
                } catch (Exception e) {
                    System.out.println("неправильный ввод данных");
                }
                mathSet.cut(firstIndex, lastIndex);
                break;
            case "0":
                System.exit(0);
        }
        System.out.println(mathSet);
        runNavigation();
    }

    public void createMathSet() {
        mathSet = new MathSet();
    }

    public void createMathSetWithCapacity(BufferedReader reader) {
        System.out.println("Введите численное значение capacity");
        int capacity = 0;
        try {
            capacity = Integer.parseInt(reader.readLine());
            if (capacity < 0) {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            System.out.println("capacity должно быть целочисленным и положительным");
            return;
        }
        mathSet = new MathSet(capacity);
    }

    public void createMathSetFromArray(BufferedReader reader) throws IOException {
        System.out.println("Введите целочисленные значения массива через пробела, для конца ввода намите enter");
        String input = reader.readLine();
        String stringArray[] = input.split(" ");
        Number numberArray[] = new Number[stringArray.length];
        for (int i = 0; i < numberArray.length; i++) {
            try {
                numberArray[i] = Long.parseLong(stringArray[i]);
            } catch (RuntimeException e) {
                try {
                    numberArray[i] = Double.parseDouble(stringArray[i]);
                } catch (RuntimeException exception) {
                    System.out.println("Массив должен сожержать только числа");
                }
            }
        }
        mathSet = new MathSet(numberArray);
    }

    public void createMathSetFromArrayVarargs(BufferedReader reader) throws Exception {
        System.out.println("Введите численные значения массива через пробела, чтоб начать новый массив, " +
                "разделите массивы с помощью ';' , для конца ввода намите enter");
        String input = reader.readLine();
        String stringArray[] = input.split(";");
        Number numberArray[] = null;
        for (int i = 0; i < stringArray.length; i++) {
            String subArray[] = stringArray[i].split(" ");
            numberArray = new Number[subArray[i].length()];
        }

        for (int i = 0; i < numberArray.length; i++) {
            numberArray[i] = parseNumber(stringArray[i]);
        }
        mathSet = new MathSet(numberArray);
        System.out.println(mathSet);
    }

    private Number parseNumber(String input) throws Exception {
        try {
            return Long.parseLong(input);
        } catch (Exception e) {
            try {
                return Double.parseDouble(input);
            } catch (Exception exception) {
                System.out.println("неправильно введены данные");
            }
        }
        throw new Exception("что-то пошло не так");
    }

    private MathSet[] fromInputToVarargsMathsets(String input) throws Exception {
        stringArray = input.split(";");
        MathSet[] mathSets = new MathSet[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            String[] subArray = stringArray[i].split(" ");
            numberArray = new Number[subArray.length];
            for (int j = 0; j < numberArray.length; j++) {
                numberArray[j] = parseNumber(subArray[j]);
            }
            mathSets[i] = new MathSet(numberArray);
        }
        return mathSets;
    }

    private MathSet fromInputMathset(String input) throws Exception {
        String[] subArray = input.split(" ");
        numberArray = new Number[subArray.length];
        for (int j = 0; j < numberArray.length; j++) {
            numberArray[j] = parseNumber(subArray[j]);
        }
        mathSet = new MathSet(numberArray);
        return mathSet;
    }
}
