package org.example.Utils;

public class Lower implements SquareOps {
    @Override
    public Matrix doOperation(int[][] arr) {
        int[][] dia = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                dia[i][j] = arr[i][j];
                if (i < j) {
                    dia[i][j] = 0;
                }
            }
        }
        return new Matrix(dia);
    }
}
