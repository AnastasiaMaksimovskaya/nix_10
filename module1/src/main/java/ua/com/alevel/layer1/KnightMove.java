package ua.com.alevel.layer1;

public class KnightMove {
    static String board[][] = new String[8][8];
    static int positionHor1 = 0;
    static int positionVer1 = 0;

    private static boolean isAble(int positionHor2, int positionVer2) {
        boolean variant1 = Math.abs(positionHor1 - positionHor2) == 2 && Math.abs(positionVer1 - positionVer2) == 1;
        boolean variant2 = Math.abs(positionHor1 - positionHor2) == 1 && Math.abs(positionVer1 - positionVer2) == 2;
        boolean check = (positionVer2 < 9 && positionVer2 > -1) &&
                (positionVer1 < 9 && positionVer1 > -1) &&
                (positionHor1 < 9 && positionHor1 > -1) &&
                (positionHor1 < 2 && positionHor2 > -1);
        if ((variant1 || variant2) && check) {
            return true;
        }
        return false;
    }

    private static void draw(int positionHor, int positionVer) {
        if ((positionVer < 8 && positionVer > -1) &&
                (positionHor < 9 && positionHor > -1)) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (7 - i == positionVer && j == positionHor) {
                        System.out.print("k ");
                    } else if ((i + j) % 2 == 0) {
                        System.out.print("o ");
                    } else System.out.print("+ ");
                }
                System.out.println("");
            }
            System.out.println("\n \n");
        }
    }


    public static void move(int positionHor2, int positionVer2) {
        if (isAble(positionHor2, positionVer2)) {
            draw(positionHor2, positionVer2);
            positionHor1=positionHor2;
            positionVer1=positionVer2;
        } else {
            System.out.println("invalid move");
        }
    }
}
