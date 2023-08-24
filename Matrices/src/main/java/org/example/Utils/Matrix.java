package org.example.Utils;
//TODO remove un-used imports
import java.util.Arrays;

public class Matrix {
    private int rows;
    private int columns;
    private int[][] elements;

    public Matrix(int[][] elements) {
        validateElements(elements);
        checkColumns(elements); // TODO you can move this method inside validateElements, to collect all validation in one place
        this.elements = elements; // TODO get copy of element to avoid update over origin matrix, same thing when getElements()
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

    public int[][] dotProduct(Matrix mat2) throws Exception {
        // TODO no need to create variables (cols, and rows), used only once, and we can use global variables (this.this.rows and this.columns)
        int cols = this.getColumns();
        int rows = mat2.getRows();
        checkMultiplySizes(cols, rows);
        int[][] mux = new int[this.getRows()][mat2.getColumns()];

        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < mat2.getColumns(); j++) {
                int tot = 0;
                for (int k = 0; k < mat2.getRows(); k++) {
                    tot += this.getVal(i, k) * mat2.getVal(k, j);
                }
                mux[i][j] = tot;
            }
        }
        return mux;
    }
    public Matrix transposeMat(){
        // TODO  we can use global variables (this.rows and this.columns), (save memory space)
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

    public Matrix squareMatOps(SqaureOpsTypes needed) throws Exception {
        // TODO you not use the main idea of overriding (no need to create multiple instances at same time we only need one instance as "needed" OpsType)
        Diagonal diagonal = new Diagonal();
        Upper upper = new Upper();
        Lower lower = new Lower();
        int [][] matrix = this.getElements(); // TODO no need for this variable (save memory space)
        if(!this.checkSquare()) {
            throw new Exception("Cannot perform square matrix operations as Matrix is not square");
        }
        return switch (needed) {
            case DIAGONAL -> diagonal.doOperation(matrix);
            case UPPER -> upper.doOperation(matrix);
            case LOWER -> lower.doOperation(matrix);
            // TODO no need to handle this exception, cause we use enum type (needed argument only use values from OpsType we can't pass any other value(compilation error))
            default -> throw new Exception("Choose valid entry for operation needed \n" +
                    " enter 1 for diagonal matrix \n" +
                    " enter 2 for upper triangle \n" +
                    " enter 3 for lower triangle");
        };
    }

    public Matrix subMatrix(int numOfRowsToCancel, int[] indexesToCancel) throws Exception {
        int numOfColumnsToCancel = indexesToCancel.length - numOfRowsToCancel;
        int[] rowToCancel = new int[numOfRowsToCancel];
        int[] columnToCancel = new int[numOfColumnsToCancel];

        rowsAndColsToCancel(numOfRowsToCancel, indexesToCancel, rowToCancel, columnToCancel);
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
        /***
         * TODO try to sum below Matrices
         * mat1 ==> [{1,2,3},
         *           {4,5,6}]
         * mat2 ==> [{1,2},
         *           {3,4}]
         */
        if(this.rows != mat2.getRows() && this.columns != mat2.getColumns()){
            throw new IllegalArgumentException("Matrix sizes do not match i.e. cannot perform matrix addition"); //TODO i.e --> what it means
        }
    }

    private void checkMultiplySizes(int cols, int rows) throws Exception {
        if (cols != rows) {
            throw new Exception("Cannot perform matrix multiplication because the number of columns of the left hand matrix does not equal the number of rows of the right hand matrix");
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
        /*** TODO for counter variable you can use it inside FOR LOOP definition (always counter increment by constant value) like below
                for (int i = 0, counter = 0; i < toCancel.length; i++, counter++) {
         ***/
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

    // TODO remove un-used and not needed argument, numOfRowsToCancel you can know this value from rowToCancel size, same thing for numOfColumnsToCancel so no need to pass it as argument
    private void exceptionsSubmatrix(int numOfRowsToCancel, int[] indexesToCancel, int[] rowToCancel, int[] columnToCancel, int numOfColumnsToCancel) throws Exception {
        checkHowMuchDeleted(numOfRowsToCancel, numOfColumnsToCancel);
        checkRowOrColumnExceedMax(numOfRowsToCancel, rowToCancel, columnToCancel, numOfColumnsToCancel); // TODO no need to pass all these arguments
    }

    private void checkHowMuchDeleted(int numOfRows, int numOfColumns) {
        if(this.getRows() == numOfRows || this.getColumns() == numOfColumns){
            throw new IllegalArgumentException("Deleted the whole matrix");
        }
    }

    private void checkRowOrColumnExceedMax(int numOfRows, int[] rowToCancel, int[] columnToCancel, int numOfColumns) throws Exception {
        // TODO you can split rows validation and columns validation for two methods to be more readable
        /***
         * as I understood from this code, you validate number of deleted rows and deleted columns not be more than Matrix rows and columns, right? and that's good, you can do it with 2 lines I think (XD HAHAHA)
         * but you missed to validate important thing, check below example and find it (XP)
         * matrix ==> [{1,2,3}
         *             {4,5,6}
         *             {7,8,9}]
         * matrix.subMatrix(2, [2, 5, 3])
         */
        int maxRow = -1;
        int maxCol = -1;
        if(numOfRows > 1){ // TODO no need to check if rowToCancel array have elements or not (FOR LOOP by default will work if it have elements and skip it if no element exist)
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

    private boolean contains(int[] arr, int key){
        for (int i : arr){
            if (i == key){
                return true;
            }
        }
        return false;
    }

}
