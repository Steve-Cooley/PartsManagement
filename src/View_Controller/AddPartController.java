package View_Controller;

import Model.InHouse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPartController implements Initializable {

    @FXML private Button saveButton;
    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField invField;
    @FXML private TextField costField;
    @FXML private TextField mutableField;
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
        //set inHouse selected by default
        this.inHouse.setSelected(true);
        //set mutable label default
        mutableLabel.setText("Company Name");

    }

    @FXML
    private void onSaveButton() {
        System.out.println("add button pushed");

        if (inHouse.isSelected()) {
            System.out.println("inHouse is selected");
            //
        }
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
