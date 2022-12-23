package com.example.snakeladder;

import javafx.scene.shape.Rectangle;

import static javafx.scene.paint.Color.*;

public class Tile extends Rectangle {
    public Tile(int size){
        setWidth(size);
        setHeight(size);
        setFill(CORNSILK);
        setStroke(BROWN);
    }

}
