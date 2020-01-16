package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
// This is the original Stage called sample.  I didn't like the name  :)
// In Hindsight, I still would have named MainScreen something different.
//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
//    }

    @Override
    public void start(Stage mainStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        mainStage.setTitle("Test");
        mainStage.setScene(new Scene(root, 1153,736));
        mainStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
