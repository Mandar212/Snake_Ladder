package com.example.snakeladder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player {
    private Circle coin;

    private  String name;
    private int coinPosition;

    private static Board gameBoard=new Board();

    public Player(int tilesize, Color coinColor,String playerName){
        coinPosition=1;
        name=playerName;
        coin=new Circle(tilesize/2);
        coin.setFill(coinColor);
        coin.setTranslateX(20);
        coin.setTranslateY(380);
    }

    public Circle getCoin() {
        return coin;
    }

    public int getCoinPosition() {
        return coinPosition;
    }

    public String getName() {
        return name;
    }
}
