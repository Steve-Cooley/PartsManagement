package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage mainStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        mainStage.setTitle("Test");                                                      //fixme re-title?
        mainStage.setScene(new Scene(root, 1250,736));
        mainStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
