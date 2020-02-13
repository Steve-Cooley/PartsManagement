package Main;

import Model.Inventory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage mainStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../View_Controller/MainScreen.fxml"));
        mainStage.setTitle("Main Window");
        mainStage.setScene(new Scene(root, 1250,736));
        mainStage.show();
    }


    public static void main(String[] args) {
        Inventory.loadSampleData();
        launch(args);
    }
}
