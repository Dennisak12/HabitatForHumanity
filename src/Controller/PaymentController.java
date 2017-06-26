package Controller;

import Model.LoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Boss on 6/26/2017.
 */
public class PaymentController {

    @FXML
    private TextField countryField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField creditCardNumberField;
    @FXML
    private TextField expirationDateField;
    @FXML
    private TextField cscField;
    @FXML
    private TextField billingAddressField;
    @FXML
    private TextField stateField;
    @FXML
    private TextField teleNumberField;
    @FXML
    private Label itemNameLabel;
    @FXML
    private Label itemPriceLabel;
    @FXML
    private Button purchaseItemButton;
    @FXML
    private Button exitButton;

    LoginModel loginModel = new LoginModel();

    public void exitToItemSearch(ActionEvent event)throws IOException {
        Node node=(Node) event.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("../View/ItemSearchView.fxml"));
        Scene scene = new Scene(root, 700, 573);
        Stage stage=(Stage) node.getScene().getWindow();
        stage.setTitle("Purchase An Item");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
