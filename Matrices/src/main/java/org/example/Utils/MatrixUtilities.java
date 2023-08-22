package org.example.Utils;

public class MatrixUtilities {
    public boolean checkSize(Matrix arr1, Matrix arr2){
        return arr1.getRows() == arr2.getRows() && arr1.getColumns() == arr2.getColumns();
    }
    public int[][] copyMat(int[][] arr) {
        int[][] copy = new int[arr.length][arr[0].length];
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length; j++){
                copy[i][j] = arr[i][j];
            }
        }

        return copy;
    }

    public boolean contains(int[] arr, int key){
        for (int i : arr){
            if (i == key){
                return true;
            }
        }
        return false;
    }

    public boolean checkSquare(int[][] arr){
        if(arr.length == arr[0].length){
            return true;
        }
        return false;
    }
}
