import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    Button shuffleButton;
    //Button continueButton;
    
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        mainStage.setTitle("Custom Card Game");

        //User inputs
        shuffleButton = new Button();
        shuffleButton.setText("Shuffle");
        //continueButton.setText("ENTER");

        //Content
        StackPane layout = new StackPane();
        layout.getChildren().add(shuffleButton);

        //Scene
        Scene scene =  new Scene(layout, 1280, 720);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
