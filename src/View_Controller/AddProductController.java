package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
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

public class AddProductController {
    public Button saveButton;
    public Button cancelButton;
    public TextField idField;
    public TextField nameField;
    public TextField invField;
    public TextField priceField;
    public TextField maxField;
    public Label minLabel;
    public TextField minField;
    public Button searchButton;
    public TextField searchBar;
    public TableColumn topIDTableCol;
    public TableColumn topPartNTableCol;
    public TableColumn topInvTableCOl;
    public TableColumn topPriceTableCol;
    public TableColumn botPartIDTableCol;
    public TableColumn botNameTableCol;
    public TableColumn botInvTableCol;
    public TableColumn botPriceCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //

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
