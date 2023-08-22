package org.example.Utils;

public class MatrixUtilities {

    public static int[][] copyMat(int[][] arr) {
        int[][] copy = new int[arr.length][arr[0].length];
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length; j++){
                copy[i][j] = arr[i][j];
            }
        }

        return copy;
    }

    public static boolean contains(int[] arr, int key){
        for (int i : arr){
            if (i == key){
                return true;
            }
        }
        return false;
    }

    public static boolean checkSquare(int[][] arr){
        if(arr.length == arr[0].length){
            return true;
        }
        return false;
    }
}
