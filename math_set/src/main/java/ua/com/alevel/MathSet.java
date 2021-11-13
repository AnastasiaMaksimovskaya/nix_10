package ua.com.alevel;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

public class MathSet {
    private Number mathSet[];
    private final int DEFAULT_SIZE = 10;
    private int capacity;

    MathSet() {
        this.mathSet = new Number[DEFAULT_SIZE];
    }

    MathSet(int capacity) {
        this.capacity = capacity;
        this.mathSet = new Number[capacity];
    }

    MathSet(Number[] numbers) {
        this.mathSet = fromArrayToSet(numbers);
    }

    MathSet(Number[]... numbers) {
        this.mathSet = fromArrayToSet(mergeArrays(numbers));
    }

    MathSet(MathSet numbers) {
        this.mathSet = numbers.getMathSet();
    }

    MathSet(MathSet... numbers) {
        this.mathSet = fromArrayToSet(mergeMathSets(numbers));
    }

    public Number[] getMathSet() {
        return mathSet;
    }

    public void setMathSet(Number[] mathSet) {
        this.mathSet = mathSet;
    }

    void add(Number n) {
        if (!isPresent(n) && !isFull(mathSet)) {
            for (int i = 0; i < mathSet.length; i++) {
                if (mathSet[i] == null) {
                    mathSet[i] = n;
                    break;
                }
            }
        } else if (!isPresent(n) && isFull(mathSet) && capacity == mathSet.length) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (!isPresent(n) && isFull(mathSet) && capacity != mathSet.length) {
            changeSize();
            add(n);
        }
    }

    void add(Number... n) {
        for (Number num : n) {
            add(num);
        }
    }

    void join(MathSet ms) {
        mathSet = fromArrayToSet(mergeArrays(ms.getMathSet(), mathSet));
    }

    void join(MathSet... ms) {
        for (MathSet set : ms) {
            mathSet = fromArrayToSet(mergeArrays(set.getMathSet(), mathSet));
        }
    }

    void intersection(MathSet ms) {
        int numberOfCommon = 0;
        for (int i = 0; i < ms.getMathSet().length; i++) {
            if (isPresent(ms.getMathSet()[i])) {
                numberOfCommon++;
            }
        }
        int count = 0;
        Number newMathSet[] = new Number[numberOfCommon];
        for (int i = 0; i < ms.getMathSet().length; i++) {
            if (isPresent(ms.getMathSet()[i])) {
                newMathSet[count] = ms.getMathSet()[i];
                count++;
            }
        }
        mathSet = newMathSet;
    }

    void intersection(MathSet... ms) {
        for (MathSet set : ms) {
            intersection(set);
        }
    }

    void sortDesc() {
        sortDesc(mathSet);
    }

    void sortDesc(int firstIndex, int lastIndex) {
        Number[] sortedPart = new Number[lastIndex - firstIndex + 1];
        for (int i = firstIndex; i < lastIndex + 1; i++) {
            sortedPart[i - firstIndex] = mathSet[i];
        }
        sortDesc(sortedPart);
        for (int i = firstIndex; i < lastIndex + 1; i++) {
            mathSet[i] = sortedPart[i - firstIndex];
        }
    }

    void sortDesc(Number value) {
        sortDesc(getIndex(value), mathSet.length - 1);
    }

    void sortAsc() {
        sort(mathSet);
    }

    void sortAsc(int firstIndex, int lastIndex) {
        Number[] sortedPart = new Number[lastIndex - firstIndex + 1];
        for (int i = firstIndex; i < lastIndex + 1; i++) {
            sortedPart[i - firstIndex] = mathSet[i];
        }
        sort(sortedPart);
        for (int i = firstIndex; i < lastIndex + 1; i++) {
            mathSet[i] = sortedPart[i - firstIndex];
        }
    }

    void sortAsc(Number value) {
        sortAsc(getIndex(value), mathSet.length - 1);
    }

    Number get(int index) {
        if (mathSet[index] != null) {
            return mathSet[index];
        } else throw new ArrayIndexOutOfBoundsException();
    }

    Number getMax() {
        Number max = Long.MIN_VALUE;
        for (int i = 0; i < mathSet.length; i++) {
            if (mathSet[i] != null) {
                if (compareTo(mathSet[i], max) == 1) {
                    max = mathSet[i];
                }
            }
        }
        return max;
    }

    Number getMin() {
        Number min = Long.MAX_VALUE;
        for (int i = 0; i < mathSet.length; i++) {
            if (mathSet[i] != null) {
                if (compareTo(mathSet[i], min) == -1) {
                    min = mathSet[i];
                }
            }
        }
        return min;
    }

    Number getAverage() {
        Number sum = 0;
        int numberOfNotNullElements = 0;
        for (int i = 0; i < mathSet.length; i++) {
            if (mathSet[i] != null) {
                sum = sum(sum, mathSet[i]);
                numberOfNotNullElements++;
            }
        }
        return sum.doubleValue() / numberOfNotNullElements;
    }

    Number getMedian() {
        MathSet mset = new MathSet(mathSet);
        Number[] ms = mset.getMathSet();
        sort(ms);
        if (ms.length % 2 == 1) {
            return ms[ms.length / 2];
        } else return (sum(ms[ms.length / 2 - 1], ms[ms.length / 2]).doubleValue() / 2);
    }

    Number[] toArray() {
        return this.getMathSet();
    }

    Number[] toArray(int firstIndex, int lastIndex) {
        Number[] part = new Number[lastIndex - firstIndex + 1];
        for (int i = firstIndex; i < lastIndex + 1; i++) {
            part[i] = mathSet[i];
        }
        return part;
    }

    MathSet cut(int firstIndex, int lastIndex) {

        for (int i = lastIndex + 1; i < mathSet.length; i++) {
            mathSet[i - (lastIndex - firstIndex + 1)] = mathSet[i];
        }
        for (int i = mathSet.length - (lastIndex - firstIndex + 1); i < mathSet.length; i++) {
            mathSet[i] = null;
        }
        MathSet ms = new MathSet();
        ms.setMathSet(mathSet);
        return ms;
    }

    void clear() {
        for (int i = 0; i < mathSet.length; i++) {
            mathSet[i] = null;
        }
    }

    void clear(Number[] numbers) {
        for (int j = 0; j < numbers.length; j++) {
            for (int i = 0; i < mathSet.length; i++) {
                if (mathSet[i] != null && compareTo(mathSet[i], numbers[j]) == 0) {
                    mathSet[i] = null;
                }
            }
        }
    }

    private Number[] mergeArrays(Number[]... numbers) {
        int length = 0;
        int count = 0;
        for (Number[] n : numbers) {
            length += n.length;
        }
        Number all[] = new Number[length];
        for (Number[] n : numbers) {
            for (Number num : n) {
                all[count] = num;
                count++;
            }
        }
        return all;
    }

    public Number[] fromArrayToSet(Number[] numbers) {
        int uniqueNumbers = 0;

        for (int j = 0; j < numbers.length; j++) {
            Number current = numbers[j];
            boolean isRepeate = false;
            for (int i = 0; i < j; i++) {
                if (current != null) {
                    if (current.equals(numbers[i])) {
                        isRepeate = true;
                    }
                }
            }
            if (!isRepeate) {
                uniqueNumbers++;
            }
        }
        Number set[] = new Number[uniqueNumbers];
        int count = 0;
        for (int i = 0; i < numbers.length; i++) {
            boolean isPresent = false;
            Number current = numbers[i];
            for (int j = 0; j < uniqueNumbers; j++) {
                if (current != null && current.equals(set[j])) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                set[count] = current;
                count++;
            }
        }
        return set;
    }

    private Number[] mergeMathSets(MathSet... numbers) {
        int length = 0;
        int count = 0;
        for (MathSet n : numbers) {
            length += n.getMathSet().length;
        }
        Number all[] = new Number[length];
        for (MathSet n : numbers) {
            for (Number num : n.getMathSet()) {
                all[count] = num;
                count++;
            }
        }
        return all;
    }

    private boolean isFull(Number[] numbers) {
        int numberOfNotNullElements = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] != null) {
                numberOfNotNullElements++;
            }
        }
        return numberOfNotNullElements == numbers.length;
    }

    private boolean isPresent(Number n) {
        for (int i = 0; i < mathSet.length; i++) {
            if (n.equals(mathSet[i])) {
                return true;
            }
        }
        return false;
    }

    private void changeSize() {
        Number newSet[] = new Number[mathSet.length * 2];
        for (int i = 0; i < mathSet.length; i++) {
            newSet[i] = mathSet[i];
        }
        mathSet = newSet;
    }

    public int compareTo(Number n1, Number n2) {
        BigDecimal b1 = new BigDecimal(n1.doubleValue());
        BigDecimal b2 = new BigDecimal(n2.doubleValue());
        return b1.compareTo(b2);
    }

    public Number sum(Number n1, Number n2) {
        BigDecimal b1 = new BigDecimal(n1.doubleValue());
        BigDecimal b2 = new BigDecimal(n2.doubleValue());
        return b1.add(b2);
    }


    private void sort(Number[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            Number min = numbers[i];
            int minMathSet = i;
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[j] != null) {
                    if (compareTo(numbers[j], min) == -1) {
                        min = numbers[j];
                        minMathSet = j;
                    }
                }
            }
            if (compareTo(i, minMathSet) != 0) {
                Number tmp = numbers[i];
                numbers[i] = numbers[minMathSet];
                numbers[minMathSet] = tmp;
            }
        }
    }

    private void sortDesc(Number[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            Number max = numbers[i];
            int maxMathSet = i;
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[j] != null) {
                    if (compareTo(numbers[j], max) == 1) {
                        max = numbers[j];
                        maxMathSet = j;
                    }
                }
            }
            if (compareTo(i, maxMathSet) != 0) {
                Number tmp = numbers[i];
                numbers[i] = numbers[maxMathSet];
                numbers[maxMathSet] = tmp;
            }
        }
    }

    private int getIndex(Number number) {
        for (int i = 0; i < mathSet.length; i++) {
            if (mathSet[i] != null && compareTo(mathSet[i], number) == 0) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < mathSet.length; i++) {
            if (mathSet[i] != null) {
                stringBuilder.append(mathSet[i]).append(", ");
            }
        }
        if (stringBuilder.length() > 2) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length() - 1);
        }
        return "MathSet{" +
                "mathSet= [" +
                stringBuilder +
                "]}";
    }
}