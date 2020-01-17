// @FXML stuff here?

package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AddPartController {

        public void toAddPartScreen(ActionEvent event) throws IOException {
        //
        Parent addPartParent = FXMLLoader.load(getClass().getResource("addPart.fxml"));
        Scene addPartScene = new Scene(addPartParent);

        //Get Stage info
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();  //witchery to me fixme

        window.setScene(addPartScene);
        window.show();
    }


}
