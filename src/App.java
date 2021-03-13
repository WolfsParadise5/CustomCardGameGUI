import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

class StoreNextPage {

    Label player1Label;
    Label player2Label;
    Label player3Label;

    Label round2Player1 = new Label();
    Label round2Player2 = new Label();

    Button nextRound;
    Button exit;

    public void labelThreePlayers(String player1name, String player2name, String player3name,int[] score) {
        
        //Score distribution
        int scorePlayer1 = score[0];
        int scorePlayer2 = score[1];
        int scorePlayer3 = score[2];
        
        //Label
        player1Label = new Label(player1name +": " + scorePlayer1);
        player2Label = new Label(player2name +": " + scorePlayer2);
        player3Label = new Label(player3name +": " + scorePlayer3);
    }

    StoreNextPage(String player1name, String player2name, String player3name) {
        
        
        Stage scorePage = new Stage();
        scorePage.setTitle("Score Panel");

        GridPane setScore = new GridPane();
        setScore.setVgap(10);  //Vertical gap
        setScore.setHgap(10); //Horizontal gap
        setScore.setPadding(new Insets(10, 50, 10, 10));

        player1Label = new Label(player1name +": " + 0);
        player2Label = new Label(player2name +": " + 0);
        player3Label = new Label(player3name +": " + 0);

        ArrayList<Object> scores = Game.mainCommandPrompt(player1name, player2name, player3name);
        int[] scoreint = (int[]) scores.get(0);
        Play[] players = (Play[]) scores.get(1);

        labelThreePlayers(player1name, player2name, player3name, scoreint);
        Play player1Player = players[0];
        Play player2Player = players[1];
        Play player3Player = players[2];
        nextRound = new Button("Proceed to Round 2");

        //Button action
        EventHandler<ActionEvent> onClick = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            {  
                //Platform.exit();
                String[] toBeInserted = round2(player1Player, player2Player, player3Player, scoreint);
                round2Player1.setText(toBeInserted[0]);
                round2Player2.setText(toBeInserted[1]);
                setScore.getChildren().remove(nextRound);
                

            } 
        };

        EventHandler<ActionEvent> exitAction = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            {  
                Platform.exit();
                System.exit(0);

            } 
        };
        nextRound.setOnAction(onClick);
        exit = new Button("Exit");
        exit.setOnAction(exitAction);

        //Set the score
        setScore.add(new Label("Round 1: "),0,0);
        setScore.add(player1Label,0,1);
        setScore.add(player2Label,0,2);
        setScore.add(player3Label,0,3);
        setScore.add(nextRound,1,4);

        setScore.add(new Label("Round 2: "),0,5);
        setScore.add(round2Player1,0,8);
        setScore.add(round2Player2,0,9);
        setScore.add(exit,4,12);
        

        Scene scene = new Scene(setScore, 450,600);
        scorePage.setScene(scene);
        scorePage.show();
        

    }

    public String[] round2(Play player1Player,Play player2Player,Play player3Player, int[] scoreint) {

        //Determine the two players proceeding to the next stage
        Play[] twoPlayer = {};

        if (scoreint[0] > scoreint[2]){
            if(scoreint[1] > scoreint[2]){
                System.out.println("***** " + player1Player.playerName + " and " + player2Player.playerName + " proceed to 2-Player phase *****");
                twoPlayer = Game.phase2(player1Player, player2Player);
            }
            else if(scoreint[2] > scoreint[1]){
                System.out.println("***** " + player1Player.playerName + " and " + player3Player.playerName + " proceed to 2-Player phase *****");
                twoPlayer = Game.phase2(player1Player,player3Player);
            }
        }

        else if (scoreint[1] > scoreint[0]){
            if(scoreint[2] > scoreint[0]){
                System.out.println("***** " + player2Player.playerName + " and " + player3Player.playerName + " proceed to 2-Player phase *****");
                twoPlayer = Game.phase2(player2Player, player3Player);
            }
            else if(scoreint[2] < scoreint[0]){
                System.out.println("***** " + player2Player.playerName + " and " + player3Player.playerName + " proceed to 2-Player phase *****");
                twoPlayer = Game.phase2(player2Player, player3Player);
            }
        }

        else if (scoreint[0] > scoreint[1]){
            if(scoreint[2] > scoreint[1]){
                System.out.println("***** " + player1Player.playerName + " and " + player3Player.playerName + " proceed to 2-Player phase *****");
                twoPlayer = Game.phase2(player1Player, player3Player);
            }
            else if(scoreint[1] > scoreint[2]){
                System.out.println("***** " + player1Player.playerName + " and " + player2Player.playerName + " proceed to 2-Player phase *****");
                twoPlayer = Game.phase2(player1Player, player2Player);
            }
        }

        //Round 2 Label
        String playerOneLabel = new String(twoPlayer[0].playerName +": " + twoPlayer[0].getScore());
        String playerTwoLabel = new String(twoPlayer[1].playerName +": " + twoPlayer[1].getScore());
        
        return new String[]{playerOneLabel,playerTwoLabel};

    }
}


public class App extends Application {

    Button continueButton;
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    
    @Override
    public void start(Stage mainStage) throws Exception {
        mainStage.setTitle("Custom Card Game");

        //User inputs

        //Label
        Label player1 = new Label("Enter player 1: ");
        Label player2 = new Label("Enter player 2: ");
        Label player3 = new Label("Enter player 3: ");

        //GridPane Inititalization
        GridPane setPlayer = new GridPane();
        setPlayer.setVgap(10);  //Vertical gap
        setPlayer.setHgap(10); //Horizontal gap
        setPlayer.setPadding(new Insets(20, 150, 10, 10));

        //Textfields
        TextField player1Detail = new TextField();
        player1Detail.setPromptText("Player 1 ");
        
        TextField player2Detail = new TextField();
        player2Detail.setPromptText("Player 2 ");
        
        TextField player3Detail = new TextField();
        player3Detail.setPromptText("Player 3 ");

        //Button
        continueButton = new Button();
        continueButton.setText("Continue");

        //Button action
        //EventHandler<ActionEvent> onClick = new EventHandler<ActionEvent>() { 
        //    public void handle(ActionEvent e) 
        //    {  
        //        Platform.exit();
        //        Game.mainCommandPrompt(player1Detail.getText(), player2Detail.getText(), player3Detail.getText());
        //    } 
        //};

        continueButton.setOnAction(eve-> new StoreNextPage(player1Detail.getText(), player2Detail.getText(), player3Detail.getText()));

        //Add to grid
        setPlayer.add(player1, 0, 0);
        setPlayer.add(player1Detail, 1, 0);

        setPlayer.add(player2, 0, 1);
        setPlayer.add(player2Detail, 1, 1);

        setPlayer.add(player3, 0, 2);
        setPlayer.add(player3Detail, 1, 2);

        setPlayer.add(continueButton, 4, 6);
        

        //Scene
        Scene scene =  new Scene(setPlayer, 640, 480);
        mainStage.setScene(scene);
        mainStage.show();
    }
}