package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static final int NUMROWS = 5;
    public static final int NUMCOLS = 5;
    private Location currentLoc = null;
    private AnimationTimer animationTimer;
    int moveCount = 1;
    boolean step = false;
    boolean isRunning = false;

    //data structure
    HashMap<Location, ArrayList<Location>> exhaustedList; //Maps a location with the visited locations
    Stack<Location> stack; //My history of moves
    int board[][]; //the number board that shows each move in sequence

    @Override
    public void start(Stage stage) throws IOException {
        KnightsTourVC knightsTourVC = new KnightsTourVC(this);
        scene = new Scene(knightsTourVC.getPane(), 1024, 768);
        stage.setTitle("Knight's Tour");
        stage.setScene(scene);

        //initialize data structures
        exhaustedList = new HashMap<>();
        stack = new Stack<>();
        board = new int[NUMROWS][NUMCOLS];


        animationTimer = new AnimationTimer() {
            //500_000_000 = update twice per second
            //1_000_000_000 = update one per second
            private long timerDelay = 1_000_000_000;
            private long lastUpdate = timerDelay;
            @Override
            public void handle(long now) {
                if(isRunning || step){
                if(now - lastUpdate >= timerDelay) {
                    //get valid move locations
                    ArrayList<Location> locs = getPossibleMoves(currentLoc);

                    //choose a possible move 
                    Location nextLoc = chooseNextMove(locs);

                    //move to that location and update all the data structure
                    if(nextLoc != null) {
                        board[nextLoc.getRow()][nextLoc.getCol()] = moveCount;
                        moveCount++;//adds one to the move count
                        currentLoc = nextLoc;//sets the current to the next loc
                    }
                    lastUpdate = now;
                    step = false;
                }
                }
                knightsTourVC.draw(currentLoc, getPossibleMoves(currentLoc), board);
            }  
        };

        animationTimer.start();

        knightsTourVC.draw(currentLoc, getPossibleMoves(currentLoc), board);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public Location getCurrentLoc() {
        return currentLoc;
    }

    public void setCurrentLoc(Location currentLoc) {
        this.currentLoc = currentLoc;
    }

    public boolean inExhaustedList(Location loc) {
        Set<Location> keys = exhaustedList.keySet(); 

        for(Location current : keys) {
            if(current.equals(loc)){
                return true;
            }
        }

        return false;
    }

    public void addToExhausted(Location from, Location to) {
        System.out.println("HI! Welcome to the addToExhausted method");
        System.out.println();
        
        //if location from is not in the exhausted list
        if(!(exhaustedList.containsKey(from))) {
            //add it to the list
            exhaustedList.put(from, new ArrayList<Location>());
            //add path to the list
            exhaustedList.get(from).add(to);

            System.out.println("added to exhausted list");
        }

        //if location is already in the whatever 
        else {
            System.out.println(from + " is already in exhausted list");

            exhaustedList.get(from).add(to);

            System.out.println(to + " has know been added");
        }
        System.out.println();
    }

    public void deleteFromExhausted(Location from) {
        System.out.println(from + " is about to be deleted");
        exhaustedList.remove(from);
        System.out.println("The deed has been done. Take care now, ...");
        System.out.println();
    }

    public Location chooseNextMove(ArrayList<Location> locs) {
        if(locs == null) return null;
        if(locs.size() == 0) return null;

        //returns first possible location
        return locs.get(0);
    }

    //returns an arraylist of all the possible moves; removes out of bounds moves and those moves that have already been moved to
    public ArrayList<Location> getPossibleMoves(Location loc) {
        
        //int of number of checks done, can only go up to 7
        int check = 0;
        ArrayList<Location> moves = new ArrayList<>();

        //return empty if loc is null
        if(loc == null) {return moves;}

        //location is top left most 
        Location temp = new Location(loc.getRow() - 2, loc.getCol() - 1);
        //checks all 8 possible location
        while(check < 8) {
            if(isValid(temp)) {
                moves.add(temp);
            }
            if(check == 0) {
                //top left middlemost
                temp = new Location(temp.getRow() + 1, temp.getCol() - 1);
            }
            if(check == 1) {
                //top right middlemost
                temp = new Location(temp.getRow() + 2, temp.getCol());
            }
            if(check == 2) {
                //top right most
                temp = new Location(temp.getRow() + 1, temp.getCol() + 1);
            }
            if(check == 3) {
                //bottom right most
                temp = new Location(temp.getRow(), temp.getCol() + 2);
            }
            if(check == 4) {
                //bottom right middlemost
                temp = new Location(temp.getRow() - 1, temp.getCol() + 1);
            }
            if(check == 5) {
                //bottom left middle most
                temp = new Location(temp.getRow() - 2, temp.getCol());
            }
            if(check == 6) {
                //bottom left most
                temp = new Location(temp.getRow() - 1, temp.getCol() - 1);
            }
            check++;
        }

        //returns arraylist
        return moves;
    }

    //checks if a move is real
    private boolean isValid(Location loc) {
        //if out of bounds
        if(((loc.getRow() < 0 || loc.getRow() >= NUMROWS) || (loc.getCol() < 0 || loc.getCol() >= NUMCOLS))) {
            return false;
        }
        //if the spot is greater than 0
        if(board[loc.getRow()][loc.getCol()] != 0) {
            return false;
        }
        return true;
    }

    //returns the value at a point
    public int getBoardValue(int row, int col) {
        return board[row][col];
    }

    public void setStartLoc(int row, int col) {
        board[row][col] = 1;
        moveCount++;
    }

    public boolean isStep() {
        return step;
    }

    public void setStep(boolean step) {
        this.step = step;
    }

    public boolean isIsRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
}