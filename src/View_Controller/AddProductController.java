package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {

    @FXML private Button saveButton;
    @FXML private Button cancelButton;
    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField invField;
    @FXML private TextField priceField;
    @FXML private TextField maxField;
    @FXML private Label minLabel;
    @FXML private TextField minField;
    @FXML private Button searchButton;
    @FXML private TextField searchBar;
    @FXML private TableColumn topIDTableCol;
    @FXML private TableColumn topPartNTableCol;
    @FXML private TableColumn topInvTableCOl;
    @FXML private TableColumn topPriceTableCol;
    @FXML private TableColumn botPartIDTableCol;
    @FXML private TableColumn botNameTableCol;
    @FXML private TableColumn botInvTableCol;
    @FXML private TableColumn botPriceCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
        System.out.println("Initialize is running!");
        //set up all parts table (top)

        //set component parts table (bottom)
    }

    public void toMainScene(ActionEvent event) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene addPartScene = new Scene(addPartParent);

        //Get Stage info
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(addPartScene);
        window.show();
    }
}
