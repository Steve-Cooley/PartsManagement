package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
    @FXML private Label mutableLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        //group radio buttons
        inOutSourceToggleGroup = new ToggleGroup();  //definitely need this line
        this.inHouse.setToggleGroup(inOutSourceToggleGroup);
        this.outSourced.setToggleGroup(inOutSourceToggleGroup);
        //set default
        this.inHouse.setSelected(true);

        //mutableLabel
        //mutableLabel = new Label();  //Don't need to use new on @FXML objects??? fixme ask teacher  This was causing problems before I removed it.
        mutableLabel.setText("Company Name");  ///This doesn't seem to work if the above line is present

    }

    public void toMainScene(ActionEvent event) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene addPartScene = new Scene(addPartParent);

        //Get Stage info
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(addPartScene);
        window.show();
    }

    public void radioButtonChange() {
        if(this.inOutSourceToggleGroup.getSelectedToggle().equals(this.inHouse)) {
            mutableLabel.setText("Company Name");
        }
        if(this.inOutSourceToggleGroup.getSelectedToggle().equals(this.outSourced)) {
            mutableLabel.setText("Machine ID");
        }
    }

}
