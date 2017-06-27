package Controller;

import Model.LoginModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Boss on 6/25/2017.
 */

public class ItemSearchController implements Initializable{

    @FXML
    private Button getResultsButton;
    @FXML
    private Button makePaymentButton;
    @FXML
    private ComboBox resultsComboBox;
    @FXML
    private CheckBox doorSelector;
    @FXML
    private CheckBox interiorTrimSelector;
    @FXML
    private CheckBox woodFloorSelector;
    @FXML
    private CheckBox paintSelector;
    @FXML
    private CheckBox powerToolsSelector;
    @FXML
    private CheckBox handToolsSelector;
    @FXML
    private CheckBox appliancesSelector;
    @FXML
    private CheckBox miscSelector;
    @FXML
    private ImageView imageViewResults;
    @FXML
    private Button exitButton;
    @FXML
    private Label priceLabel;

    LoginModel loginModel = new LoginModel();
    String image = null;

    public void makeImageView(ActionEvent event)throws IOException{
        String name = null;
        String price = loginModel.getItemPrice(name);
        priceLabel.setText("$"+price);
        loginModel.resetConnection();

        Image imageNew = new Image("file:///" + loginModel.getImageUrl(name));

        System.out.println(imageNew);
        imageViewResults.setImage(imageNew);
        imageViewResults.setPreserveRatio(true);
        imageViewResults.setSmooth(true);
        imageViewResults.setCache(true);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginModel.resetConnection();

        ArrayList<String> array = new ArrayList<>();
        array = loginModel.getItemsFromDatabase();

        loginModel.resetConnection();
        ObservableList<String> list = FXCollections.observableArrayList();

        loginModel.resetConnection();
        for(int i = 0; i < array.size(); i++){
            loginModel.resetConnection();
            list.add(array.get(i));
        }
        resultsComboBox.getItems().addAll(list);
        loginModel.resetConnection();
    }



    public void setComboBox(ActionEvent event) throws IOException{
        loginModel.resetConnection();
        String username = null;
        ObservableList<String> list = FXCollections.observableArrayList(
                loginModel.selectUsername(username));
        loginModel.resetConnection();
        resultsComboBox.getItems().add(loginModel.getItemsFromDatabase());
        loginModel.resetConnection();
    }

    public void jumpToRegularUser(ActionEvent event) throws IOException{
        Node node=(Node) event.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("../View/RegularUserLogin.fxml"));
        Scene scene = new Scene(root, 600, 500);
        Stage stage=(Stage) node.getScene().getWindow();
        stage.setTitle("Welcome to Habitat For Humanity");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void jumpToPayment(ActionEvent event) throws IOException{
        Node node=(Node) event.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("../View/CreditCardView.fxml"));
        Scene scene = new Scene(root, 600, 400);
        Stage stage=(Stage) node.getScene().getWindow();
        stage.setTitle("Make A Payment");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
