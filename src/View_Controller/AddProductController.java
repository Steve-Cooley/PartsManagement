package View_Controller;

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

    private Product newProd = new Product(Inventory.genProductID(), "temp", 1.3, 1,2,3);
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
    @FXML private TextField searchField;
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
        //set up allParts table (top)
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
        //set ID field
        idField.setText(Integer.toString(newProd.getID()));
    }

    @FXML
    private void onDelButton() {
        newProd.deleteAssociatedPart(botTableV.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void onSearchButton() {
        String s = searchField.getText();
        ObservableList<Part> filtered = FXCollections.observableArrayList();
        if (!s.equals("")) {
            for (Part p : Inventory.getAllParts()) {
                if ( Integer.toString(p.getID()).contains(s)) {
                    filtered.add(p);
                } else if (p.getName().contains(s)) {
                    filtered.add(p);
                }
            }
            topTableV.setItems(filtered);
        } else {
            topTableV.setItems(Inventory.getAllParts());
        }
    }

    private double addAllPartPrices() {
        double outp = 0;
        for (Part p : newProd.getAllAssociatedParts()) {
            outp += p.getPrice();
        }
        return outp;
    }

    @FXML
    private void onSaveButton() {
        //test if prices make sense
        double prodPrice = Double.parseDouble(priceField.getText());
        double partsPrice = addAllPartPrices();
        if (prodPrice < partsPrice) {
            Alert costAlert = new Alert(Alert.AlertType.ERROR);
            costAlert.setTitle("Cost Error");
            costAlert.setContentText("Cost of Parts exceeds cost of Product!");
            costAlert.showAndWait();
            return;
        }
        //send error if min>max
        int min = Integer.parseInt(minField.getText());
        int max = Integer.parseInt(maxField.getText());
        if (min > max) {
            //
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog (minMax)");
            alert.setContentText("min must not be greater than max");
            alert.showAndWait();
        } else {
            newProd.setId(Inventory.genProductID());
            newProd.setName(nameField.getText());
            newProd.setStock(Integer.parseInt(invField.getText()));
            newProd.setPrice(Double.parseDouble(priceField.getText())); //could use price variable
            newProd.setMax(Integer.parseInt(maxField.getText()));
            newProd.setMin(Integer.parseInt(minField.getText()));
            Inventory.addProduct(newProd);
            cancelButton.fire();
        }
    }

    @FXML
    private void onAddButton() {
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
