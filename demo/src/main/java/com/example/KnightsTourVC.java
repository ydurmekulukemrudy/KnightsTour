package com.example;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class KnightsTourVC {
    private App app;
    private AnchorPane anchorPane;
    private Button startButton;//button to start the program
    private Button stepButton;//button to get next step
    private Label rowLabel; //text
    private Label colLabel; //text
    private TextField rowTextField; //an area where the user can input text
    private TextField colTextField; 
    private Canvas canvas; //a place to drwa graphics to
    private GraphicsContext gc; //use a GC, to draw to things

    //constructor
    public KnightsTourVC(App app) {
        this.app = app;
        anchorPane = new AnchorPane();

        createGui();
        attachListeners();
    }

    private void attachListeners() {
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                handleButtonClicks(arg0);
            }
        });

        stepButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                handleButtonClicks(arg0);
            }
        });
    }

    /*
     * creates a single square
     * @param x x-coordinate
     * @param y y-coordinate
     * @param size size of the square in pixels
     * @param stroke size in pixels of the outline
     * @param color the color of the square
     */
    private void drawSingleSquare(int x, int y, int size, int stroke, Paint color)  {
        //first draw a black rectangle
        gc.setFill(Color.BLACK);
        gc.fillRect(x, y, size, size);

        //draw a slightly smaller, colored rectangle
        gc.setFill(color);
        gc.fillRect(x+stroke, y+stroke, size - (stroke*2), size - (stroke*2));
    }

    public void drawMoves(ArrayList <Location> locs, int xOffset, int yOffset) {
        for(Location current : locs) {
            drawSingleSquare(current.getCol()*50 + xOffset, current.getRow()*50 + yOffset, 50, 2, Color.RED);
        }
    }

    //draws the chessboard
    public void draw(Location current, ArrayList<Location> locs, int board[][]) {
        int xOffset = 20;
        int yOffset = 20;
        for(int row = 0; row < App.NUMROWS; row++) {
            for(int col = 0; col < App.NUMCOLS; col++) {
                if((app.getCurrentLoc() != null) && (app.getCurrentLoc().equals(new Location(row, col)))){
                    drawSingleSquare(col*50 + yOffset, row*50 + xOffset, 50, 2, Color.CORNFLOWERBLUE);
                }
                
                else if((row+col) % 2 == 0) {
                    drawSingleSquare(col*50 + yOffset, row*50 + xOffset, 50, 2, Color.BISQUE);
                }
                else {
                    drawSingleSquare(col*50 + yOffset, row*50 + xOffset, 50, 2, Color.GRAY);
                }
            }
        }
        drawMoves(locs, xOffset, yOffset);
    }

    private void handleButtonClicks(ActionEvent actionEvent) {
        if(actionEvent.getSource() == startButton) {
            String buttonText = startButton.getText();

            //if thetour is not currently running
            if(buttonText.equals("Start")) {
                startButton.setText("Pause");
                //if this is the first click, behin a brand new tour
                if(app.getCurrentLoc() == null) {
                    try {
                        int row = Integer.parseInt(rowTextField.getText());
                        int col = Integer.parseInt(colTextField.getText());
                        Location loc = new Location(row, col);
                        app.setCurrentLoc(loc);
                    } catch(Exception e) {
                        rowTextField.setText("");
                        colTextField.setText("");
                        startButton.setText("Start");
                    }
                }
            }
            else if (buttonText.equals("Pause")){
                startButton.setText("Continue");
            }
            else {
                startButton.setText("Pause");
            }
        }
    }

    private void createGui() {
        //create the GUI element
        startButton = new Button("Start");
        startButton.setPrefWidth(100);
        //anchor the element to any of the four sides
        AnchorPane.setTopAnchor(startButton, 170.0);
        AnchorPane.setRightAnchor(startButton, 140.0);
        //add the element to the pane
        anchorPane.getChildren().add(startButton);

        //create the GUI element
        stepButton = new Button("Step");
        stepButton.setPrefWidth(100);
        //anchor the element to any of the four sides
        AnchorPane.setTopAnchor(stepButton, 210.0);
        AnchorPane.setRightAnchor(stepButton, 140.0);
        //add the element to the pane
        anchorPane.getChildren().add(stepButton);

        //create the GUI element
        rowLabel = new Label("row");
        //anchor the element to any of the four sides
        AnchorPane.setTopAnchor(rowLabel, 100.0);
        AnchorPane.setRightAnchor(rowLabel, 200.0);
        //add the element to the pane
        anchorPane.getChildren().add(rowLabel);

        //create the GUI element
        colLabel = new Label("col");
        //anchor the element to any of the four sides
        AnchorPane.setTopAnchor(colLabel, 130.0);
        AnchorPane.setRightAnchor(colLabel, 200.0);
        //add the element to the pane
        anchorPane.getChildren().add(colLabel);
        
        rowTextField = new TextField();
        rowTextField.setPrefWidth(50);
        AnchorPane.setTopAnchor(rowTextField, 97.0);
        AnchorPane.setRightAnchor(rowTextField, 135.0);
        anchorPane.getChildren().add(rowTextField);

        colTextField = new TextField();
        colTextField.setPrefWidth(50);
        AnchorPane.setTopAnchor(colTextField, 127.0);
        AnchorPane.setRightAnchor(colTextField, 135.0);
        anchorPane.getChildren().add(colTextField);



        canvas = new Canvas(600, 500); //a canvas that is 600x500
        gc = canvas.getGraphicsContext2D(); //draw 2d things on the canvas
        gc.setFill(Color.RED); //choose red as the color
        gc.fillRect(0, 0, 600, 500); //fill the entire area 600x500, with a red rectangle
        AnchorPane.setLeftAnchor(canvas, 50.0);
        AnchorPane.setTopAnchor(canvas, 100.0);
        anchorPane.getChildren().add(canvas); //add the canvas to the pane


    }

    public AnchorPane getPane() {
        return anchorPane;
    }
}
