package org.example.Utils;


public class MatrixOperations {
    static MatrixUtilities mu = new MatrixUtilities();

    //2 Scalar Multiplication
    public int[][] scalarMultiplication(int[][] mat1, int scalar){
        int[][] matNew = mu.copyMat(mat1);
        for (int i = 0; i < mat1.length; i++){
            System.out.println("TEst");
            for(int j = 0; j < mat1[0].length; j++){
                matNew[i][j] = scalar*mat1[i][j];
            }
        }
        return matNew;
    }

    //3 Transpose
    public int[][] transposeMat(int[][] arr){
        int rows = arr.length;
        int cols = arr[0].length;

        int[][] transpose = new int[cols][rows];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++) {
                transpose[j][i] = arr[i][j];
            }
        }
        return transpose;
    }

    //4 Matrix Multiplication
    public int[][] matMux(int[][] mat1, int[][]mat2) throws Exception {

        int cols = mat1[0].length;
        int rows = mat2.length;
        if (cols != rows) {
            throw new Exception("Cannot perform matrix multiplication because the number of columns of the left hand matrix does not equal the number of rows of the right hand matrix");
        }
        int[][] mux = new int[mat1.length][mat2[0].length];
        int[][] copy1 = mu.copyMat(mat1);
        int[][] copy2 = mu.copyMat(mat2);

        int[][] mat2Trans = transposeMat(copy2);

        for(int i = 0; i < mat1.length; i++){
            for(int j =0; j < mat2[0].length; j++){
                int tot = 0;
                for(int k = 0; k < mat2.length; k++){
                    tot += mat1[i][k] * mat2[k][j];
                }
                mux[i][j] = tot;
            }
        }
        return mux;
    }

    //5 Submatrix
    // use "-1" in order to not cancel anything
    public int[][] subMat (int[][] arr ,int[] delRow, int[] delCol) throws Exception{
        int[][] copy = mu.copyMat(arr);

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

        if(mu.contains(delRow,-1)){
            newRow = arr.length;
        }
        if (mu.contains(delCol, -1)){
            newCol = arr[0].length;
        }

        int[][] subMat = new int[newRow][newCol];
        int rowInd = 0;

        for(int i = 0; i < copy.length; i++){
            if(!mu.contains(delRow, i)){
                int colInd = 0;
                for(int j = 0; j < copy[0].length; j++){
                    if(!mu.contains(delCol,j)){
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
        if(!mu.checkSquare(arr))
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
        if (!mu.checkSquare(arr)) {
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



}

