package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
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

    @FXML private Button cancelButton;
    @FXML private TextField maxField;
    @FXML private TextField minField;
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
        mutableLabel.setText("Machine ID");

    }

    @FXML
    private void onSaveButton() {
        //System.out.println("add button pushed");
        int id;
        String partN;
        double cost;
        int inv;
        int min;
        int max;
        int machineID;
        String compN;

        if (inHouse.isSelected()) {
            id = Integer.parseInt(idField.getText());
            partN = nameField.getText();
            cost = Double.parseDouble(costField.getText());
            inv = Integer.parseInt(invField.getText());
            min = Integer.parseInt(minField.getText());
            max = Integer.parseInt(maxField.getText());
            machineID = Integer.parseInt(mutableField.getText());

            Inventory.addPart(new InHouse(id, partN, cost, inv, min, max, machineID));

        } else {
            id = Integer.parseInt(idField.getText());
            partN = nameField.getText();
            cost = Double.parseDouble(costField.getText());
            inv = Integer.parseInt(invField.getText());
            min = Integer.parseInt(minField.getText());
            max = Integer.parseInt(maxField.getText());
            compN = idField.getText();
            Inventory.addPart(new Outsourced(id,partN,cost,inv,min,max,compN));
        }
        cancelButton.fire();   //returns to main after finishing add
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
            mutableLabel.setText("Machine ID");
        }
        if(this.inOutSourceToggleGroup.getSelectedToggle().equals(this.outSourced)) {
            mutableLabel.setText("Company Name");
        }
    }

}
