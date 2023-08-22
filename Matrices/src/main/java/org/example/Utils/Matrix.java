package org.example.Utils;

public class Matrix {

    private int rows;
    private int columns;
    private int[][] elements;


    public Matrix(int[][] elements) {
        validateElements(elements);
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


    private void validateElements(int[][] elements) {
        if(elements == null){
            throw new IllegalArgumentException();
        }
        validateRows(elements);
    }

    private void validateRows(int[][] elements) {
        for (int i = 0; i< elements.length; i++){
            if(elements[i] == null){
                throw new IllegalArgumentException();
            }
        }
    }

    private void sameSize(Matrix mat2){
        if(this.rows != mat2.getRows() && this.columns != mat2.getColumns()){
            throw new IllegalArgumentException("Matrix sizes do not match i.e. cannot perform matrix addition");
        }
    }

}
