// I think @FXML stuff goes here
//I think that these controllers are supposed to have "initialize methods fixme initialize methods for all controllers
//Yes, it turns out that they are supposed to have initialize methods, which is an override from initializable

package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    //configure Parts table
    @FXML private TableView<Part> partsTable;
    @FXML private TableColumn<Part, String> partIDCol;
    @FXML private TableColumn<Part, String> partNameCol;
    @FXML private TableColumn<Part, String> partInvCol;
    @FXML private TableColumn<Part, String> partCostCol;

    //configure products table
    @FXML private TableView<Part> productsTable;
    @FXML private TableColumn<Part, String> prodIDCol;
    @FXML private TableColumn<Part, String> prodNameCol;
    @FXML private TableColumn<Part, String> prodInvCol;
    @FXML private TableColumn<Part, String> prodCostCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //part table
        partIDCol.setCellValueFactory(new PropertyValueFactory<Part, String>("Part ID"));
        partIDCol.setCellValueFactory(new PropertyValueFactory<Part, String>("Part Name"));
        partIDCol.setCellValueFactory(new PropertyValueFactory<Part, String>("Part Inventory Level"));
        partIDCol.setCellValueFactory(new PropertyValueFactory<Part, String>("Part Cost"));

    }

    /**
     * Method to change Scene to addPart
     * fixme note to self, these methods show up in scenebuilder even without @FXML annotation
     */
    @FXML
    public void toAddPartScreen(ActionEvent event) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        Scene addPartScene = new Scene(addPartParent);

        //Get Stage info
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(addPartScene);
        window.show();
    }

    @FXML
    public void toModifyPartScreen(ActionEvent event) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("ModifyPart.fxml"));
        Scene addPartScene = new Scene(addPartParent);

        //Get Stage info
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(addPartScene);
        window.show();
    }

    @FXML
    public void toAddProductScreen(ActionEvent event) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        Scene addPartScene = new Scene(addPartParent);

        //Get Stage info
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(addPartScene);
        window.show();
    }

    @FXML
    public void toModifyProductScreen(ActionEvent event) throws IOException {
        //
        Parent addPartParent = FXMLLoader.load(getClass().getResource("ModifyProduct.fxml"));
        Scene addPartScene = new Scene(addPartParent);

        //Get Stage info
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(addPartScene);
        window.show();
    }

    //@FXML


}
