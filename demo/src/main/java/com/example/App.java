package com.example;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static final int NUMROWS = 8;
    public static final int NUMCOLS = 8;
    private Location currentLoc = null;
    private AnimationTimer animationTimer;

    @Override
    public void start(Stage stage) throws IOException {
        KnightsTourVC knightsTourVC = new KnightsTourVC(this);
        scene = new Scene(knightsTourVC.getPane(), 1024, 768);
        stage.setTitle("Knight's Tour");
        stage.setScene(scene);

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

    
}