// I think @FXML stuff goes here

package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class MainScreenController {

    /**
     * Method to change Scene to addPart
     */
    public void toAddPartScreen(ActionEvent event) {
        //
        Parent addPart_FXMLLoader = FXMLLoader.load(getClass().getResource("addPart.fxml"));
    }

}
