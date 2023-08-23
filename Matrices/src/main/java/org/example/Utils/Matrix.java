package org.example.Utils;

import java.util.Arrays;

public class Matrix {
    private int rows;
    private int columns;
    private int[][] elements;

    public Matrix(int[][] elements) {
        validateElements(elements);
        checkColumns(elements);
        this.elements = elements;
        this.rows = elements.length;
        this.columns = elements[0].length;
    }

    public int getVal(int row, int col){
       return elements[row][col];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int[][] getElements() {
        return elements;
    }

    public Matrix matSummation(Matrix mat2){
        sameSize(mat2);
        int[][] matNew = new int[this.rows][this.columns];
        for (int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.columns; j++){
                matNew[i][j] = this.getVal(i,j) + mat2.getVal(i,j);
            }
        }
        return new Matrix(matNew);
    }

    public Matrix scalarMultiplication(int scalar){
        int[][] matNew = new int[this.rows][this.columns];
        for (int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.columns; j++){
                matNew[i][j] = scalar*this.getVal(i,j);
            }
        }
        return new Matrix(matNew);
    }

    public Matrix transposeMat(){
        int rows = this.getRows();
        int cols = this.getColumns();
        int[][] transpose = new int[cols][rows];

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++) {
                transpose[j][i] = this.getVal(i,j);
            }
        }
        return new Matrix(transpose);
    }

    public Matrix subMatrix(int numOfRowsToCancel, int[] indexesToCancel) throws Exception {
        int numOfColumnsToCancel = indexesToCancel.length - numOfRowsToCancel;
        int[] rowToCancel = new int[numOfRowsToCancel];
        int[] columnToCancel = new int[numOfColumnsToCancel];

        exceptionsSubmatrix(numOfRowsToCancel, indexesToCancel, rowToCancel, columnToCancel, numOfColumnsToCancel);

        int newRow = this.getRows() - numOfRowsToCancel;
        int newColumn = this.getColumns() - numOfColumnsToCancel;

        int[][] subMat = new int[newRow][newColumn];
        int rowInd = 0;
        for(int i = 0; i < this.getRows(); i++){
            if(!contains(rowToCancel, i)){
                int colInd = 0;
                for(int j = 0; j < this.getColumns(); j++){
                    if(!contains(columnToCancel,j)){
                        subMat[rowInd][colInd] = this.getVal(i,j);
                        colInd++;
                    }
                }
                rowInd++;
            }
        }
        return new Matrix(subMat);
    }

    public int determinant() throws Exception {
        if (!this.checkSquare()) {
            throw new Exception("Matrix is not square, so cannot calculate determinant.");
        }
        int det = 0;
        if (this.getRows() == 2) {
            det += (this.getVal(0,0) * this.getVal(1,1)) - (this.getVal(0,1) * this.getVal(1,0));
        } else {
            for (int i = 0; i < this.getRows(); i++) {
                Matrix sub = this.subMatrix(1,new int[] {0,i});
                int coef = this.getVal(0,i);
                det += (int) ((coef * Math.pow(-1, i)) * sub.determinant());
            }
        }
        return det;
    }

    public boolean checkSquare(){
        return this.getRows() == this.getColumns();
    }

    private void validateElements(int[][] elements) {
        if(elements == null){
            throw new IllegalArgumentException();
        }
        validateRows(elements);
    }

    private void validateRows(int[][] elements) {
        for (int[] element : elements) {
            if (element == null) {
                throw new IllegalArgumentException("Cannot have null value as a row in the matrix");
            }
        }
    }

    private void sameSize(Matrix mat2){
        if(this.rows != mat2.getRows() && this.columns != mat2.getColumns()){
            throw new IllegalArgumentException("Matrix sizes do not match i.e. cannot perform matrix addition");
        }
    }

    private void checkColumns(int[][] elements) {
        for(int i = 0; i < elements.length; i++){
            if(elements[i].length != elements[0].length){
                throw new IllegalArgumentException("Check number of columns in all rows of the matrix is the same.");
            }
        }
    }

    private void rowsAndColsToCancel(int numOfRows, int[] toCancel, int[] rowToCancel, int[] columnToCancel) {
        int counter = 1;
        int colInd = 0;
        for (int i = 0; i < toCancel.length; i++) {
            if (counter <= numOfRows) {
                rowToCancel[i] = toCancel[i];
            } else {
                columnToCancel[colInd] = toCancel[i];
                colInd++;
            }
            counter++;
        }
    }

    private void exceptionsSubmatrix(int numOfRowsToCancel, int[] indexesToCancel, int[] rowToCancel, int[] columnToCancel, int numOfColumnsToCancel) throws Exception {
        rowsAndColsToCancel(numOfRowsToCancel, indexesToCancel, rowToCancel, columnToCancel);
        checkHowMuchDeleted(numOfRowsToCancel, numOfColumnsToCancel);
        checkRowOrColumnExceedMax(numOfRowsToCancel, rowToCancel, columnToCancel, numOfColumnsToCancel);
    }

    private void checkHowMuchDeleted(int numOfRows, int numOfColumns) {
        if(this.getRows() == numOfRows || this.getColumns() == numOfColumns){
            throw new IllegalArgumentException("Deleted the whole matrix");
        }
    }

    private void checkRowOrColumnExceedMax(int numOfRows, int[] rowToCancel, int[] columnToCancel, int numOfColumns) throws Exception {
        int maxRow = -1;
        int maxCol = -1;
        if(numOfRows > 1){
            for(int i : rowToCancel){
                if(maxRow < i){
                    maxRow = i;
                }
            }
        }
        if(numOfColumns > 1){
            for(int i : columnToCancel){
                if(maxCol < i){
                    maxCol = i;
                }
            }
        }
        if (maxRow+1 > this.getRows()){
            throw new Exception("Cannot delete row as the index exceeds the number of rows in the Matrix");
        }
        if (maxCol+1 > this.getColumns()){
            throw new Exception("Cannot delete column as the index exceeds the number of columns in the Matrix");
        }
    }

    private static boolean contains(int[] arr, int key){
        for (int i : arr){
            if (i == key){
                return true;
            }
        }
        return false;
    }

}
