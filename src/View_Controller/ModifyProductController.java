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

public class ModifyProductController implements Initializable {

    private static Product prd;
    @FXML private Button cancelButton;
    @FXML private Button saveButton;
    @FXML private Button addButton;
    @FXML private Button delButton;
    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField invField;
    @FXML private TextField priceField;
    @FXML private TextField maxField;
    @FXML private TextField minField;
    @FXML private Button searchButton;
    @FXML private TextField searchField;
    @FXML private TableView<Part> topTableV;
    @FXML private TableColumn<TableView<Part>, Part> topIDCol;
    @FXML private TableColumn<TableView<Part>, Part> topNameCol;
    @FXML private TableColumn<TableView<Part>, Part> topInvCol;
    @FXML private TableColumn<TableView<Part>, Part> topPriceCol;
    @FXML private TableView<Part> botTableV;
    @FXML private TableColumn<TableView<Part>, Part> botIDCol;
    @FXML private TableColumn<TableView<Part>, Part> botNameCol;
    @FXML private TableColumn<TableView<Part>, Part> botInvCol;
    @FXML private TableColumn<TableView<Part>, Part> botPriceCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //use "the pass" to fill TableViews
        //set up allParts table (top)
        topTableV.setItems(Inventory.getAllParts());
        topIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        topNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        topInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        topPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        //set component parts table (bottom)
        botTableV.setItems(prd.getAssociatedParts());
        botIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        botNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        botInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        botPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        //set ID field
        idField.setText(Integer.toString(prd.getID()));
        // use "the pass" to fill all fields
        nameField.setText(prd.getName());
        invField.setText(Integer.toString(prd.getStock()));
        priceField.setText(Double.toString(prd.getPrice()));
        maxField.setText(Integer.toString(prd.getMax()));
        minField.setText(Integer.toString(prd.getMin()));
    }

    @FXML
    private void onDelButton() {
        prd.deleteAssociatedPart(botTableV.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void onSearchButton() {
        //test
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
        for (Part p : prd.getAllAssociatedParts()) {
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
            //save changes
            prd.setName(nameField.getText());
            prd.setStock(Integer.parseInt(invField.getText()));
            prd.setPrice(Double.parseDouble(priceField.getText()));
            prd.setMax(Integer.parseInt(maxField.getText()));
            prd.setMin(Integer.parseInt(minField.getText()));
            Inventory.modProduct(prd);
            //return to main screen
            cancelButton.fire();
        }
    }

    @FXML void onAddButton() {
        prd.addAssociatedPart(topTableV.getSelectionModel().getSelectedItem());
    }

    //receive pass
    public static void setPrd(Product prd) {
        ModifyProductController.prd = prd;
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
