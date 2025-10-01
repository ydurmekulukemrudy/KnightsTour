package com.example;

public class Location implements Comparable<Location>{
    //private int variables
    private int row;
    private int col;
    
    //constructor
    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    //getters and setters
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    //prints [row, col]
    @Override
    public String toString() {
        return "[" + row + ", " + col + "]";
    }

    public boolean equals(int row, int col) {
        return this.row == row && this.col == col;
    }

    public boolean equals(Location other) {
        return this.row == other.row && this.col == other.col;
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof  Location) {
            return equals((Location)other);
        }
        else {
            return false;
        }
    }

    @Override
    public int compareTo(Location o) {
        if(this.equals(o)) {
            return 0;
        }
        return this.row - o.row;
    }  

    @Override
    public int hashCode() {
        return row * 10 + col;
    }

    
}
