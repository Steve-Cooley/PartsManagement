// I think @FXML stuff goes here
//I think that these controllers are supposed to have "initialize methods fixme initialize methods for all controllers
//Yes, it turns out that they are supposed to have initialize methods, which is an override from initializable

package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    @FXML TextField prodSearchField;
    @FXML Button prodSearchButton;
    @FXML private Button partsSearchButton;
    @FXML private TextField partsSearchField;
    @FXML private Button delButton;
    @FXML private Button exitButton;
    @FXML private TableView<Part> partsTable;
    @FXML private TableColumn<TableView<Part>, Part> partIDCol;
    @FXML private TableColumn<TableView<Part>, Part> partNameCol;
    @FXML private TableColumn<TableView<Part>, Part> partInvCol;
    @FXML private TableColumn<TableView<Part>, Part> partCostCol;
    @FXML private TableView<Product> productsTable;     //typing may cause trouble. Pay attention!
    @FXML private TableColumn<TableView<Part>, Part> prodIDCol;
    @FXML private TableColumn<TableView<Part>, Part> prodNameCol;
    @FXML private TableColumn<TableView<Part>, Part> prodInvCol;
    @FXML private TableColumn<TableView<Part>, Part> prodCostCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set up Parts table
        partsTable.setItems(Inventory.getAllParts());
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productsTable.setItems(Inventory.getAllProducts());
        prodIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        prodCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

        @FXML
    private void productsSearch() {
        System.out.println("the products search button was executed");
        String s = prodSearchField.getText();
        ObservableList filtered = FXCollections.observableArrayList();
        System.out.println(s);
        //First test if search string is empty, if so table should be full. if not, fill with filtered array
        if (!s.equals("")) {
            for (Product p : Inventory.getAllProducts()) {
                //get ID number of Product, convert to String, see if it contains search string, assign to TableView
                if (Integer.toString(p.getID()).contains(s)) {
                    Product prt = p;
                    filtered.add(prt);
                } else if (p.getName().contains(s)) {
                    Product prt = p;
                    filtered.add(prt);
                }
            }
            productsTable.setItems(filtered);
        } else {
            productsTable.setItems(Inventory.getAllProducts());
        }
    }

    /**
     * This method seems to work best with Strings
     * PartsTable takes an ObservableArrayList as a parameter
     */
    @FXML
    private void partsSearch() {
        System.out.println("the parts search button was executed");  //todo remove
        String s = partsSearchField.getText();
        ObservableList filtered = FXCollections.observableArrayList();
        System.out.println(s);
        //First test if search string is empty, if so table should be full. if not, fill with filtered array
        if (!s.equals("")) {
            for (Part p : Inventory.getAllParts()) {
                //get ID number of Part, convert to String, see if it contains search string, assign to TableView
                if (Integer.toString(p.getID()).contains(s)) {
                    Part prt = p;
                    //prt = p;
                    filtered.add(prt);
                } else if (p.getName().contains(s)) {
                    Part prt = p;
                    //prt = p;
                    filtered.add(prt);
                }
            }
            partsTable.setItems(filtered);
        } else {
            partsTable.setItems(Inventory.getAllParts());
        }
    }

    @FXML
    private void delProductsButtonPressed() {
        Product p = productsTable.getSelectionModel().getSelectedItem();
        Inventory.deleteProduct(p);
    }

    @FXML
    private void delPartsButtonPressed() {
        Part p = partsTable.getSelectionModel().getSelectedItem();
        Inventory.deletePart(p);
    }

    @FXML
    private void closeButtonAction() {
        Platform.exit();  // Guaranteed to kill the whole program
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
        //send in Part object
        ModifyPartController.setPrt(partsTable.getSelectionModel().getSelectedItem());

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
        //send in Part object  //fixme alter to fit product instead of part
        ModifyProductController.setPrd(productsTable.getSelectionModel().getSelectedItem());
        //
        Parent addPartParent = FXMLLoader.load(getClass().getResource("ModifyProduct.fxml"));
        Scene addPartScene = new Scene(addPartParent);

        //Get Stage info
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(addPartScene);
        window.show();
    }




}
