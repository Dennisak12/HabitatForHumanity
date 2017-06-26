package Controller;

import Model.LoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Boss on 6/24/2017.
 */

public class RemoveAccountController {
    @FXML
    private Button exitFromRemoveButton;
    @FXML
    private Button acceptRemoveButton;
    @FXML
    private TextField removeAccountField;

    LoginModel loginModel = new LoginModel();

    public void exitFromRemove(ActionEvent event) throws IOException {
        Node node=(Node) event.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("../View/ManagerWindow.fxml"));
        Scene scene = new Scene(root, 600, 400);
        Stage stage=(Stage) node.getScene().getWindow();
        stage.setTitle("Register For An Account");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void acceptRemoveAccount(){
        String username = removeAccountField.getText();

        try {
            //selectAll is a test method to gather information on the customer

            loginModel.resetConnection();

            //searches for the username to delete the account
            if(loginModel.removeCustomer(username) == username){
                //alerting the user that the account is not in the database
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Account " + username + " has been deleted");
                alert.showAndWait();

                //resetting database
                loginModel.resetConnection();
            }else {

                //alerting the user that the account has been deleted
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Account " + username + " was not found in our database");
                alert.showAndWait();
                //resetting database
                loginModel.resetConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
