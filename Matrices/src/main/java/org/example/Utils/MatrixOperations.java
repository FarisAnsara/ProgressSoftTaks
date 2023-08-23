package org.example.Utils;


public class MatrixOperations {

    //4 Matrix Multiplication
    public int[][] matMux(Matrix mat1, Matrix mat2) throws Exception {

        int cols = mat1.getColumns();
        int rows = mat2.getRows();
        checkMultiplySizes(cols, rows);
        int[][] mux = new int[mat1.getRows()][mat2.getColumns()];

        for(int i = 0; i < mat1.getRows(); i++){
            for(int j =0; j < mat2.getColumns(); j++){
                int tot = 0;
                for(int k = 0; k < mat2.getRows(); k++){
                    tot += mat1.getVal(i,k) * mat2.getVal(k,j);
                }
                mux[i][j] = tot;
            }
        }
        return mux;
    }

    //5 Submatrix
    // use "-1" in order to not cancel anything

//    6 SquareMatOps
    public static Matrix squareMatOps(Matrix inputMatrix, SqaureOpsTypes needed) throws Exception {
        Diagonal diagonal = new Diagonal();
        Upper upper = new Upper();
        Lower lower = new Lower();
        int [][] matrix = inputMatrix.getElements();
        if(!inputMatrix.checkSquare()) {
            throw new Exception("Cannot perform square matrix operations as Matrix is not square");
        }
        return switch (needed) {
            case DIAGONAL -> diagonal.doOperation(matrix);
            case UPPER -> upper.doOperation(matrix);
            case LOWER -> lower.doOperation(matrix);
            default -> throw new Exception("Choose valid entry for operation needed \n" +
                    " enter 1 for diagonal matrix \n" +
                    " enter 2 for upper triangle \n" +
                    " enter 3 for lower triangle");
        };
    }

    // Exceptions
    private static void checkMultiplySizes(int cols, int rows) throws Exception {
        if (cols != rows) {
            throw new Exception("Cannot perform matrix multiplication because the number of columns of the left hand matrix does not equal the number of rows of the right hand matrix");
        }
    }


}

