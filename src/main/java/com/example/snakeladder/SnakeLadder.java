package com.example.snakeladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {
    public static final int tileSize=40, height=10,width=10;
    int lowerLine=tileSize*height;
    int diceValue;
    Label rollDiceValueLabel;
    Button startGameButton;

    boolean firstPlayerTurn=true, secondPlayerTurn=false, gameStarted=false;
    Player firstPlayer=new Player(tileSize, Color.BLACK,"Player 1");
    Player secondPlayer=new Player(tileSize-10, Color.WHITE,"Player 2");
    Pane createContent(){
        Pane root=new Pane();
        root.setPrefSize(width*tileSize,height*tileSize+100);

        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                Tile tile=new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().add(tile);
            }
        }

        Image img=new Image("D:\\projects\\Snake_Ladder\\src\\main\\snake.jpg");
        ImageView boardImage=new ImageView(img);
        boardImage.setImage(img);
        boardImage.setFitWidth(tileSize*width);
        boardImage.setFitHeight(tileSize*height);

        Button playerOneButton=new Button("Player One");
        playerOneButton.setTranslateX(30);
        playerOneButton.setTranslateY(lowerLine+15);
        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(firstPlayerTurn){
                        setDiceValue();
                        firstPlayer.movePlayer(diceValue);
                        if(firstPlayer.playerWon()!=null){
                            rollDiceValueLabel.setText(firstPlayer.playerWon());
                            firstPlayerTurn=true;
                            secondPlayerTurn=false;
                            gameStarted=false;
                            startGameButton.setDisable(false);
                            startGameButton.setText("Start Game");
                        }
                        firstPlayerTurn=false;
                        secondPlayerTurn=true;
                    }
                }

            }
        });

        Button playerTwoButton=new Button("Player Two");
        playerTwoButton.setTranslateX(290);
        playerTwoButton.setTranslateY(lowerLine+15);
        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(secondPlayerTurn){
                        setDiceValue();
                        secondPlayer.movePlayer(diceValue);
                        if(secondPlayer.playerWon()!=null){
                            rollDiceValueLabel.setText(firstPlayer.playerWon());
                            firstPlayerTurn=true;
                            secondPlayerTurn=false;
                            gameStarted=false;
                            startGameButton.setText("StartGame");
                        }
                        firstPlayerTurn=true;
                        secondPlayerTurn=false;
                    }
                }

            }
        });



        startGameButton=new Button("Start");
        startGameButton.setTranslateX(165);
        startGameButton.setTranslateY(lowerLine+50);
        startGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                gameStarted=true;
                startGameButton.setText("Ongoing Game");
                startGameButton.setDisable(true);
            }
        });


        rollDiceValueLabel=new Label("Start the Game!");
        rollDiceValueLabel.setTranslateY(lowerLine+15);
        rollDiceValueLabel.setTranslateX(150);

        root.getChildren().addAll(boardImage,playerOneButton,playerTwoButton, firstPlayer.getCoin(),secondPlayer.getCoin(),rollDiceValueLabel,startGameButton);

        return  root;
    }
    private void setDiceValue(){
        diceValue=(int)(Math.random()*6+1);
        rollDiceValueLabel.setText("Dice Value :"+ diceValue);
    }

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(SnakeLadder.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder!!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}