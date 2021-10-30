package ua.com.alevel.layer3;

import ua.com.alevel.Module1Main;

import java.util.Random;
import java.util.Scanner;

public class GameOfLife {
    private static int width, height, numbersOfLive;
    private static int board[][];
    private static int live;

    public static void run(Scanner scanner) {
        try {
            System.out.println("enter the width");
            width = Integer.parseInt(scanner.nextLine());
            System.out.println("enter height");
            height = Integer.parseInt(scanner.nextLine());
            System.out.println("enter numbers of live");
            numbersOfLive = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("invalid data format");
        }
        board = new int[width][height];
        int step = 0;
        Random percent = new Random();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (percent.nextInt(100) < numbersOfLive && step < numbersOfLive+1) {
                    board[i][j] = 1;
                    step++;
                }
            }
        }
        draw(board);
        game(board);
    }

    private static void draw(int board[][]) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print((char)(board[i][j]+42) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int liveNeighbours(int board[][],int b,int c,int w,int h) {
        int live = 0;
        for (int i = Math.max(w - 1, 0); i <= Math.min(w + 1, board.length - 1); i++) {
            for (int j = Math.max(h - 1, 0); j <= Math.min(h + 1, board[0].length - 1); j++) {
                live += board[i][j] & 1;
            }
        }
        live = live-(board[w][h] & 1);
        return live;
    }

    private static void game(int board[][]) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int lives = liveNeighbours(board, board.length, board[0].length, i, j);
                if (board[i][j] == 1 && lives >= 2 && lives <= 3) {
                    board[i][j] = 3;
                }
                if (board[i][j] == 0 && lives == 3) {
                    board[i][j] = 2;
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = board[i][j]>>1;
            }
        }
    }
}
