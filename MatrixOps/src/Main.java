import Utils.MatrixOperations;

import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static int [][] mat1 = new int[][] {{1,3,5,9}, {1,3,1,7},{4,3,9,7}, {5,2,0,9}};
    static int [][] mat2 = new int[][] {{2,3,4},{4,3,7},{3,5,8},{2,3,5}};

    public static void main(String[] args) throws Exception {
        MatrixOperations mo = new MatrixOperations();
        System.out.println(Arrays.deepToString(mo.subMat(mat1, new int[]{1}, new int[]{1})));


    }
}