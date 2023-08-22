package org.example;

import org.example.Utils.Diagonal;
import org.example.Utils.Matrix;
import org.example.Utils.MatrixOperations;
import org.example.Utils.SqaureOpsTypes;

import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static int [][] mat1 = new int[][] {{1,3,5,9},null,{4,3,9,7}, {5,2,0,9}};
    static int [][] mat2 = new int[][] {{2,3,4},{4,3,7},{3,5,8}};


    public static void main(String[] args) throws Exception {

        Matrix matrix1 = new Matrix(new int[][] {{1,3,5},{4,3,9}, {5,2,0}});
        Matrix matrix2 = new Matrix(mat2);
//        System.out.println(Arrays.deepToString(matrix1.matSummation(matrix2).getElements()));

//        System.out.println(Arrays.deepToString(MatrixOperations.squareMatOps(mat2, SqaureOpsTypes.UPPER)));

        System.out.println(Arrays.deepToString(MatrixOperations.squareMatOps(mat2,SqaureOpsTypes.DIAGONAL).getElements()));


//        MatrixOperations mo = new MatrixOperations();
//        System.out.println(Arrays.deepToString(mo.subMat(mat1, new int[]{1}, new int[]{1})));


    }
}

// Static method mostly operates on arguments, almost all static method accepts arguments, perform some calculation and return value
// If the function of the method will remain static across class hierarchy (you can not override static methods in Java)
