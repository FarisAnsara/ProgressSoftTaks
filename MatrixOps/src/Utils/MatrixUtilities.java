package Utils;

public class MatrixUtilities {
    public boolean checkSize(int[][] arr1, int[][] arr2){
        return arr1.length == arr2.length && arr1[0].length == arr2[0].length;
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
