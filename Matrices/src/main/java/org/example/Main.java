package org.example;

import org.example.Utils.Matrix;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main{
    public static void main(String[] args) throws Exception {
        Matrix matrix1 = new Matrix(new int[][] {{1,3,5},{4,3,9},{5,2,5}});
        int [][] mat2 = new int[][] {{2,3,4},{4,2,7},{3,5,2}};
        Matrix matrix2 = new Matrix(mat2);
        System.out.println(matrix2.determinant());
    }
}

// Static method mostly operates on arguments, almost all static method accepts arguments, perform some calculation and return value
// If the function of the method will remain static across class hierarchy (you can not override static methods in Java)


// TODO handle exception
// TODO naming
