package com.Medium;

import java.util.*;

public class SlidingPuzzle {

    static int[][] board = new int[4][4];
    static int emptyRow = 3;
    static int emptyCol = 3;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        initializeBoard();
        shuffleBoard();

        while (true) {

            printBoard();

            if (isSolved()) {
                System.out.println("🎉 Puzzle Solved!");
                break;
            }

            System.out.println("Move (W=Up, S=Down, A=Left, D=Right): ");
            char move = sc.next().toUpperCase().charAt(0);

            if (!makeMove(move)) {
                System.out.println("❌ Invalid Move!");
            }
        }

        sc.close();
    }

    // Initialize solved board
    static void initializeBoard() {
        int num = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = num;
                num++;
            }
        }
        board[3][3] = 0; // empty
    }

    // Shuffle board
    static void shuffleBoard() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            char[] moves = {'W','A','S','D'};
            makeMove(moves[random.nextInt(4)]);
        }
    }

    // Print board
    static void printBoard() {
        System.out.println("\n-----------------");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0)
                    System.out.print("   ");
                else
                    System.out.printf("%2d ", board[i][j]);
            }
            System.out.println();
        }
    }

    // Move logic
    static boolean makeMove(char move) {

        int newRow = emptyRow;
        int newCol = emptyCol;

        if (move == 'W') newRow++;
        else if (move == 'S') newRow--;
        else if (move == 'A') newCol++;
        else if (move == 'D') newCol--;

        if (newRow >= 0 && newRow < 4 && newCol >= 0 && newCol < 4) {

            board[emptyRow][emptyCol] = board[newRow][newCol];
            board[newRow][newCol] = 0;

            emptyRow = newRow;
            emptyCol = newCol;

            return true;
        }

        return false;
    }

    // Check solved
    static boolean isSolved() {
        int num = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 3 && j == 3) return board[i][j] == 0;
                if (board[i][j] != num++) return false;
            }
        }
        return true;
    }
}