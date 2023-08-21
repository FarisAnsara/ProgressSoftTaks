package Utils;

public class SquareMatrix {
    static MatrixUtilities mu = new MatrixUtilities();
    public int[][] diagonal(int[][] arr){
        int[][] dia = mu.copyMat(arr);
        for (int i = 0; i< arr.length; i++){
            for (int j = 0; j < arr[0].length ; j++){
                if(i != j){
                   dia[i][j] = 0;
                }
            }
        }
        return dia;
    }
    public int[][] upper(int[][] arr){
        int[][] upp = mu.copyMat(arr);
        for (int i = 0; i< arr.length ; i++){
            for (int j = 0; j < arr[0].length ; j++){
                if(i < j){
                    upp[i][j] = 0;
                }
            }
        }
        return upp;
    }
    public int[][] lower(int[][]arr){
        int[][] low = mu.copyMat(arr);
        for (int i = 0; i<arr.length; i++){
            for (int j = 0; j <arr[0].length; j++){
                if (i > j){
                    low[i][j] = 0;
                }
            }
        }
        return low;
    }

}
