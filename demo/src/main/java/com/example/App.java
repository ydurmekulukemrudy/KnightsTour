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

        addToExhausted(new Location(0, 0), new Location(1, 0), true);
        addToExhausted(new Location(0, 0), new Location(1, 3), true);


        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long arg0) {
               knightsTourVC.draw();
            }  
        };

        animationTimer.start();

        knightsTourVC.draw();
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

    public void addToExhausted(Location from, Location to, boolean debug) {
        if(debug) {
            System.out.println("method addToExhausted");
            System.out.println("___________________________________________");
        }
        //is from in the list
        if(!inExhaustedList(from)) {
            if(debug) {
                System.out.println("Location " + from + " not in exhausted list.");
                System.out.println("Exhausted list is " + to);
            }
            ArrayList<Location> temp = new ArrayList<>();
            temp.add(to);
            exhaustedList.put(from, temp);
        }
        else {
            if(debug) {
                System.out.println("Location " + from + " already in exhausted list");
            }
            ArrayList<Location> temp = exhaustedList.get(from);
            temp.add(to);
            if(debug) {
                System.out.println("Maps current values: " + exhaustedList.get(from));
            }
        }
    }

    
}