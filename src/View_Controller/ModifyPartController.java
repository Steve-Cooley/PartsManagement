package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
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

public class ModifyPartController implements Initializable {

    private static Part prt;
    @FXML private Button cancelButton;
    @FXML private TextField maxField;
    @FXML private TextField minField;
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
        inOutSourceToggleGroup = new ToggleGroup();
        this.inHouse.setToggleGroup(inOutSourceToggleGroup);
        this.outSourced.setToggleGroup(inOutSourceToggleGroup);
        //set default
        this.inHouse.setSelected(true);

        //mutableLabel
        mutableLabel.setText("Machine ID");

        //set all fields based on the prt
        System.out.println("The object passed in is: " + prt);
        idField.setText(Integer.toString(prt.getID()));
        nameField.setText(prt.getName());
        invField.setText(Integer.toString(prt.getStock()));
        costField.setText(Double.toString(prt.getPrice()));
        System.out.println("class is: " + prt.getClass());
        maxField.setText(Integer.toString(prt.getMax()));
        minField.setText(Integer.toString(prt.getMin()));
        if (prt instanceof InHouse) {
            mutableField.setText(Integer.toString(((InHouse) prt).getMachineID()));
            mutableLabel.setText("Machine ID");
            inHouse.setSelected(true);
        } else if(prt instanceof Outsourced) {
            mutableField.setText(((Outsourced) prt).getCompanyName());
            mutableLabel.setText("Company Name");
            outSourced.setSelected(true);
        } else {
            System.out.println("Couldn't Identify class of part"); //fixme this should never execute. delete before sub.
        }

    }

    @FXML
    private void onSaveButton() {
        int id;
        String partN;
        double cost;
        int inv;
        int min;
        int max;
        int machineID;
        String compN;
        //send error if min>max
        min = Integer.parseInt(minField.getText());
        max = Integer.parseInt(maxField.getText());
        if (min > max) {
            //
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog (minMax)");
            alert.setContentText("min must not be greater than max");
            alert.showAndWait();
        } else {
            if (inHouse.isSelected()) {
                id = Integer.parseInt(idField.getText());
                partN = nameField.getText();
                cost = Double.parseDouble(costField.getText());
                inv = Integer.parseInt(invField.getText());
                min = Integer.parseInt(minField.getText());
                max = Integer.parseInt(maxField.getText());
                machineID = Integer.parseInt(mutableField.getText());

                Inventory.modPart(new InHouse(id, partN, cost, inv, min, max, machineID));

            } else {
                id = Integer.parseInt(idField.getText());
                partN = nameField.getText();
                cost = Double.parseDouble(costField.getText());
                inv = Integer.parseInt(invField.getText());
                min = Integer.parseInt(minField.getText());
                max = Integer.parseInt(maxField.getText());
                compN = mutableField.getText();
                Inventory.modPart(new Outsourced(id, partN, cost, inv, min, max, compN));
                System.out.println("in onsavebutton. compn should have changed. it is: " + compN);
            }
            cancelButton.fire();   //returns to main after finishing add
        }
    }

    //This just receives "the pass"
    public static void setPrt(Part prt) {
        ModifyPartController.prt = prt;
    }



    public void toMainScene(ActionEvent event) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene addPartScene = new Scene(addPartParent);

        //Get Stage info
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

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
