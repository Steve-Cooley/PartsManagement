package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPartController implements Initializable {

    @FXML private RadioButton inHouse;
    @FXML private RadioButton outSourced;
    @FXML private ToggleGroup inOutSourceToggleGroup;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        inOutSourceToggleGroup = new ToggleGroup();
        this.inHouse.setToggleGroup(inOutSourceToggleGroup);
        this.outSourced.setToggleGroup(inOutSourceToggleGroup);

    }

    public void toMainScene(ActionEvent event) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene addPartScene = new Scene(addPartParent);

        //Get Stage info
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(addPartScene);
        window.show();
    }

    inOutToggleGroup =
}
