package View_Controller;

import Model.Part;
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

public class ModifyPartController implements Initializable {

    private static Part prt;

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

        System.out.println("The object passed in is: " + prt);


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
