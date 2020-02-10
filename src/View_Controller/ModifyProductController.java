package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ModifyProductController {

    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField invField;
    @FXML private TextField priceField;
    @FXML private TextField maxField;
    @FXML private TextField minField;
    @FXML private Button searchButton;
    @FXML private TextField searchField;
    @FXML private TableView topTableV;
    @FXML private TableColumn topIDCol;
    @FXML private TableColumn topNameCol;
    @FXML private TableColumn topInvCol;
    @FXML private TableColumn topPriceCol;
    @FXML private TableView botTableV;
    @FXML private TableColumn botIDCol;
    @FXML private TableColumn botNameCol;
    @FXML private TableColumn botInvCol;
    @FXML private TableColumn botPriceCol;

    public void toMainScene(ActionEvent event) throws IOException {
        //
        Parent addPartParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene addPartScene = new Scene(addPartParent);

        //Get Stage info
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(addPartScene);
        window.show();
    }
}
