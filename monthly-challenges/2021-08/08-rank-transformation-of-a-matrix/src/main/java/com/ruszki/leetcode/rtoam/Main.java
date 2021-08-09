package com.ruszki.leetcode.rtoam;

import com.ruszki.leetcode.rtoam.slowsolution.SlowSolution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static int[][] run(int[][] matrix) {
        Solution s = new Solution();
        return s.matrixRankTransform(matrix);
    }

    public static int[][] runSlow(int[][] matrix) {
        SlowSolution s = new SlowSolution();
        return s.matrixRankTransform(matrix);
    }

    public static void main(String[] args) {
        int[][] matrix = readMatrix("matrix.txt");

        int[][] resultMatrix = run(matrix);

        printMatrix(resultMatrix);
    }

    private static int[][] readMatrix(String fileName) {
        List<int[]> matrixList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String currentLine = reader.readLine();

            while (currentLine != null) {
                String[] rowValues = currentLine.split(" ");

                int[] row = new int[rowValues.length];

                for (int columnIndex = 0; columnIndex < rowValues.length; columnIndex++) {
                    row[columnIndex] = Integer.parseInt(rowValues[columnIndex]);
                }

                matrixList.add(row);

                currentLine = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[][] matrix = matrixList.toArray(new int[0][]);

        printMatrix(matrix);

        return matrix;
    }

    private static void printMatrix(int[][] matrix) {
        System.out.println(Arrays.deepToString(matrix));
    }
}
