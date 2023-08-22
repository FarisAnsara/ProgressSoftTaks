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
    public int[][] subMat (int[][] arr , int[] delCol, int... delRow) throws Exception{
        int[][] copy = MatrixUtilities.copyMat(arr);

        if (copy.length == delRow.length || copy[0].length == delCol.length){
            throw new Exception("Deleted the whole Matrix");
        }

        int maxRow = delRow[0]; int maxCol = delCol[0];

        if (delRow.length > 1 || delCol.length > 1) {
            for (int j : delRow) {
                if (maxRow < j) {
                    maxRow = j;
                }
            }
            for (int j : delCol) {
                if (maxCol < j) {
                    maxCol = j;
                }
            }
        }

        if (maxRow+1 > copy.length){
            throw new Exception("Cannot delete row as the index exceeds the number of rows in the Matrix");
        }
        if (maxCol+1 > copy[0].length){
            throw new Exception("Cannot delete column as the index exceeds the number of columns in the Matrix");
        }

        int newRow = copy.length - delRow.length;
        int newCol = copy[0].length - delCol.length;

        if(MatrixUtilities.contains(delRow,-1)){
            newRow = arr.length;
        }
        if (MatrixUtilities.contains(delCol, -1)){
            newCol = arr[0].length;
        }

        int[][] subMat = new int[newRow][newCol];
        int rowInd = 0;

        for(int i = 0; i < copy.length; i++){
            if(!MatrixUtilities.contains(delRow, i)){
                int colInd = 0;
                for(int j = 0; j < copy[0].length; j++){
                    if(!MatrixUtilities.contains(delCol,j)){
                        subMat[rowInd][colInd] = copy[i][j];
                        colInd++;
                    }
                }
                rowInd++;
            }

        }
        return subMat;
    }

    //6 SquareMatOps
    public static Matrix squareMatOps(int[][] arr, SqaureOpsTypes needed) throws Exception {
        SquareMatrix sq = new SquareMatrix();
        Diagonal diagonal = new Diagonal();
        if(!MatrixUtilities.checkSquare(arr))
            throw new Exception("Cannot perform square matrix operations as Matrix is not square");
        return switch (needed) {
            case DIAGONAL -> diagonal.doOperation(arr);
//            case UPPER -> sq.diagonal(arr, SqaureOpsTypes.UPPER);
//            case LOWER -> sq.diagonal(arr, SqaureOpsTypes.LOWER);
            default -> throw new Exception("Choose valid entry for operation needed \n" +
                    " enter 1 for diagonal matrix \n" +
                    " enter 2 for upper triangle \n" +
                    " enter 3 for lower triangle");
        };


    }

    //7 determinant
    public int determinant(int[][] arr) throws Exception {
        if (!MatrixUtilities.checkSquare(arr)) {
            throw new Exception("Matrix is not square, so cannot calculate determinant.");
        }
        int det = 0;
        if (arr.length == 2) {
            det += (arr[0][0] * arr[1][1]) - (arr[0][1] * arr[1][0]);
        } else {
            for (int i = 0; i < arr.length; i++) {
                int[][] sub = subMat(arr, new int[]{0}, new int[]{i});
                int coef = arr[0][i];
                det += (int) ((coef * Math.pow(-1, i)) * determinant(sub));
            }

        }
        return det;
    }


    // Exceptions
    private static void checkMultiplySizes(int cols, int rows) throws Exception {
        if (cols != rows) {
            throw new Exception("Cannot perform matrix multiplication because the number of columns of the left hand matrix does not equal the number of rows of the right hand matrix");
        }
    }


}

