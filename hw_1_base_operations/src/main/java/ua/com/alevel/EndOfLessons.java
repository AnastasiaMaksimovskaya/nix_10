package ua.com.alevel;

import java.util.Scanner;

public class EndOfLessons {

    private static final int MIN_OF_BREAK1 = 15;
    private static final int MIN_OF_BREAK2 = 5;
    private static final int LESSON_DURATION = 45;
    private static final int MIN_IN_HOUR = 60;
    private static final int START_HOURS = 9;

    public static void getTimeOfEnd() {
        System.out.println("Enter the number of lesson");
        Scanner scanner = new Scanner(System.in);
        int numberOfLessons = scanner.nextInt();
        int durationOfLessons, hours, min;
        durationOfLessons = numberOfLessons * LESSON_DURATION + ((numberOfLessons - 1) / 2)
                * MIN_OF_BREAK1 + (numberOfLessons / 2) * MIN_OF_BREAK2;
        hours = START_HOURS + durationOfLessons / MIN_IN_HOUR;
        min = durationOfLessons % MIN_IN_HOUR;
        if (min < 10) {
            System.out.println("time = " + hours + " 0" + min);
        } else System.out.println("time = " + hours + " " + min);
    }
}