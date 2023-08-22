package org.example.Utils;

public class SquareMatrix {
    static MatrixUtilities mu = new MatrixUtilities();
    public int[][] diagonal(int[][] arr, SqaureOpsTypes type){
        int[][] dia = new int[arr.length][arr[0].length];
        for (int i = 0; i< arr.length; i++){
            for (int j = 0; j < arr[0].length ; j++){
                dia[i][j] = arr[i][j];
                if(ss(i, j, type)){
                   dia[i][j] = 0;
                }
            }
        }
        return dia;
    }

    private boolean ss(int row, int col, SqaureOpsTypes type) {
        switch (type){
            case DIAGONAL:
                return row !=col;
            case UPPER:
                return row>col;
            case LOWER:
                return row<col;
            default: return false;
        }
    }
//    public int[][] upper(int[][] arr){
//        int[][] upp = mu.copyMat(arr);
//        for (int i = 0; i< arr.length ; i++){
//            for (int j = 0; j < arr[0].length ; j++){
//                if(i < j){
//                    upp[i][j] = 0;
//                }
//            }
//        }
//        return upp;
//    }
//    public int[][] lower(int[][]arr){
//        int[][] low = mu.copyMat(arr);
//        for (int i = 0; i<arr.length; i++){
//            for (int j = 0; j <arr[0].length; j++){
//                if (i > j){
//                    low[i][j] = 0;
//                }
//            }
//        }
//        return low;
//    }

}
