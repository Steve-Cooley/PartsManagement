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

    private Product newProd = new Product(Inventory.genProductID(), "temp", 1.3, 1,2,3);
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
        //Product newProd = new Product();
        System.out.println("Initialize is running!");
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
        //test
        System.out.println("Del button pressed");
        // This is shorter than the version in main scene fixme
        newProd.deleteAssociatedPart(botTableV.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void onSearchButton() {
        //test
        System.out.println("The search button has been pressed");
        String s = searchField.getText();
        ObservableList filtered = FXCollections.observableArrayList();
        if (!s.equals("")) {
            for (Part p : Inventory.getAllParts()) {
                if ( Integer.toString(p.getID()).contains(s)) {
                    Part prt = p;
                    filtered.add(prt);
                } else if (p.getName().contains(s)) {
                    Part prt = p;
                    filtered.add(prt);
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
        System.out.println("save button pushed");
        //test if prices make sense
        double prodPrice = Double.parseDouble(priceField.getText());
        double partsPrice = addAllPartPrices();
        System.out.println("the total cost of parts is: " +
                partsPrice);  //fixme debugging code, remove
        System.out.println("the cost of the product is: " + prodPrice);
        if (prodPrice < partsPrice) {
            System.out.println("cost of parts exceeds cost of product");
            Alert costAlert = new Alert(Alert.AlertType.ERROR);
            costAlert.setTitle("Cost Error");
            costAlert.setContentText("Cost of product must be equal to or greater than cost of associated parts");
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
