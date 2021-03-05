import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

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
        EventHandler<ActionEvent> onClick = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            {  
                Platform.exit();
                Game.mainCommandPrompt(player1Detail.getText(), player2Detail.getText(), player3Detail.getText());
            } 
        };

        continueButton.setOnAction(onClick);

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
