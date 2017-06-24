package Controller;

import Model.LoginModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.sql.SQLException;

/**
 * Created by Boss on 6/24/2017.
 */

public class RemoveAccountController {

    @FXML
    private Button acceptRemoveButton;
    @FXML
    private TextField removeAccountField;

    LoginModel loginModel = new LoginModel();

    public void acceptRemoveAccount(){
        String username = removeAccountField.getText();
        try {
            if(loginModel.removeCustomer(username) == false){

                //alerting the user that the account is not in the database
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Account " + username + " can not be found");
                alert.showAndWait();
                //resetting database
                loginModel.resetConnection();

            }else {

                //alerting the user that the account has been deleted
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Account " + username + " has been deleted");
                alert.showAndWait();
                //resetting database
                loginModel.resetConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
