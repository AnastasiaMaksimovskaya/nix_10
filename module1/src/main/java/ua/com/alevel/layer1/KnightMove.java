package ua.com.alevel.layer1;

import java.util.Scanner;

public class KnightMove {
    static String board[][] = new String[8][8];

    static int positionHor1 = 0;
    static int positionVer1 = 0;

    private static boolean isAble(int positionHor2, int positionVer2) {
        boolean variant1 = Math.abs(positionHor1 - positionHor2) == 2 && Math.abs(positionVer1 - positionVer2) == 1;
        boolean variant2 = Math.abs(positionHor1 - positionHor2) == 1 && Math.abs(positionVer1 - positionVer2) == 2;
        boolean check = (positionVer2 < 8 && positionVer2 > -1) &&
                (positionVer1 < 8 && positionVer1 > -1) &&
                (positionHor1 < 8 && positionHor1 > -1) &&
                (positionHor2 < 8 && positionHor2 > -1);
        if ((variant1 || variant2) && check) {
            return true;
        }
        return false;
    }

    private static void draw(int positionHor, int positionVer) {
        if ((positionVer < 8 && positionVer > -1) &&
                (positionHor < 9 && positionHor > -1)) {
            for (int i = 0; i < board.length; i++) {
                System.out.print(8 - i + "  ");
                for (int j = 0; j < board[i].length; j++) {
                    if (8 - i == positionHor && j == positionVer) {
                        System.out.print("k ");
                    } else if ((i + j) % 2 == 0) {
                        System.out.print("o ");
                    } else System.out.print("+ ");
                }
                System.out.println("");
            }
            System.out.println("   a b c d e f g h");
        }
    }

    private static void move(int positionHor2, int positionVer2) {
        if (isAble(positionHor2, positionVer2)) {
            draw(positionHor2, positionVer2);
            positionHor1 = positionHor2;
            positionVer1 = positionVer2;
        } else {
            System.out.println("invalid move");
        }
    }

    public static void run(Scanner scanner) {
        System.out.println("if you want to stop, press S");
        for (int i = 0; i < board.length; i++) {
            System.out.print(8 - i + "  ");
            for (int j = 0; j < board[i].length; j++) {
                if ((i + j) % 2 == 0) {
                    System.out.print("o ");
                } else System.out.print("+ ");
            }
            System.out.println("");
        }
        System.out.println("   a b c d e f g h");
        int hor = 0, vert = 0;
        try {
            System.out.println("input horizontal position (1-8)");
            positionHor1 = Integer.parseInt(scanner.nextLine());
            System.out.println("input vertical position (a-h)");
            positionVer1 = (scanner.nextLine().charAt(0)) - 97;
        } catch (Exception e) {
            System.out.println("invalid data format");
        }
        if ((positionHor1 > 8 || positionHor1 < 0) || (positionVer1 > 8 || positionVer1 < 0)) {
            System.out.println("incorrect, try again");
            run(scanner);
        } else {
            draw(positionHor1, positionVer1);
        }
        while (true) {
            try {
                System.out.println("input horizontal position (1-8)");
                String h = scanner.nextLine();
                if (h.equals("S")) {
                    break;
                }
                hor = Integer.parseInt(h);
                System.out.println("input vertical position (a-h)");
                String v = scanner.nextLine();
                if (v.equals("S")) {
                    break;
                }
                vert = (v.charAt(0)) - 97;
            } catch (Exception e) {
                System.out.println("invalid data format");
            }
            move(hor, vert);
            draw(positionHor1, positionVer1);
        }
    }
}
