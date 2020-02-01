package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {

    private Product newProd = new Product(1, "temp", 1.3, 1,2,3);
    //ObservableList tempOL = FXCollections.observableArrayList();
    @FXML private TableView<Part> topTableV;
    @FXML private Button delButton;
    @FXML private TableView<Part> botTableV;
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
    @FXML private TableColumn<TableView<Part>, Part> topIDTableCol;
    @FXML private TableColumn<TableView<Part>, Part> topPartNTableCol;
    @FXML private TableColumn<TableView<Part>, Part> topInvTableCOl;
    @FXML private TableColumn<TableView<Part>, Part> topPriceTableCol;
    @FXML private TableColumn<TableView<Part>, Part> botPartIDTableCol;
    @FXML private TableColumn<TableView<Part>, Part> botNameTableCol;
    @FXML private TableColumn<TableView<Part>, Part> botInvTableCol;
    @FXML private TableColumn<TableView<Part>, Part> botPriceCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Product newProd = new Product();
        System.out.println("Initialize is running!");
        //set up all parts table (top)
        topTableV.setItems(Inventory.getAllParts());
        topIDTableCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        topPartNTableCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        topInvTableCOl.setCellValueFactory(new PropertyValueFactory<>("stock"));
        topPriceTableCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        //set component parts table (bottom)
        botTableV.setItems(newProd.getAssociatedParts());
        botPartIDTableCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        botNameTableCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        botInvTableCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        botPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }



    @FXML
    private void onSaveButton() {
        System.out.println("save button pushed");
        newProd.setId(Inventory.genProductID());
        newProd.setName(nameField.getText());
        newProd.setStock(Integer.parseInt(invField.getText()));
        newProd.setPrice(Double.parseDouble(priceField.getText()));
        newProd.setMax(Integer.parseInt(maxField.getText()));
        newProd.setMin(Integer.parseInt(minField.getText()));
        Inventory.addProduct(newProd);
        cancelButton.fire();
    }

    @FXML
    private void onAddButton() {
        System.out.println("add button pushed");
        System.out.println("newProd = " + newProd);
        System.out.println("printing fields of newProd" + newProd.getName() );
        newProd.addAssociatedPart(topTableV.getSelectionModel().getSelectedItem());
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
