package org.example.Utils;

public enum SqaureOpsTypes {
    DIAGONAL,
    UPPER,
    LOWER;
    boolean func (int i, int j){
        switch (this){
            case DIAGONAL : return i !=j ;
            case UPPER: return i > j;
            case LOWER: return i < j;
            default: return false;
        }
    }
}

