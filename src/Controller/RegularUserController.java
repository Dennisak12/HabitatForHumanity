package Controller;

import Model.LoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Boss on 6/25/2017.
 */
public class RegularUserController {
    @FXML
    private Button shopButton;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Button viewHistoryButton;
    @FXML
    private Button editProfileButton;
    @FXML
    private Button aboutButton;
    @FXML
    private Button helpButton;
    @FXML
    private Button logoutButton;

    LoginModel loginModel = new LoginModel();

    public void habitatForHumanityMessage(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Habitat For Humanity");
        alert.setHeaderText("About Habitat For Humanity");
        alert.setContentText("Habitat for Humanity partners with people in your community, and all over the world, to help them build or improve a place they " +
                "can call home. Habitat homeowners help build their own homes alongside volunteers and pay an affordable mortgage. With your support, Habitat homeowners " +
                "achieve the strength, stability and independence they need to build a better life for themselves" +
                " and for their families. Through our 2020 Strategic Plan, Habitat for Humanity will serve more people than ever before through decent and affordable housing.");
        alert.showAndWait();
    }

    public void helpMessage(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("Help");
        alert.setContentText("How to qualify for a Habitat home\n\n" +
                "Habitat homeowners must be active participants in building a better home and future for themselves and their families. Every Habitat home is an investment. For us, it is one answer to a critical need, and we believe that stronger homes will create stronger communities.\n" +
                "\n" +
                "Prospective Habitat homeowners must demonstrate a need for safe, affordable housing. Need will vary from community to community.\n" +
                "Once selected, Habitat homeowners must partner with us throughout the process. This partnership includes performing “sweat equity,” or helping to build their own home or the homes of others in our homeownership program. Sweat equity can also include taking homeownership classes or performing volunteer work in a Habitat ReStore.\n" +
                "Homeowners must also be able and willing to pay an affordable mortgage. Mortgage payments are cycled back into the community to help build additional Habitat houses.\n" +
                "\nHow to apply for a Habitat home\n\n" +
                "Habitat’s homeowner selection is managed at the local level, through our hundreds of local Habitat for Humanity locations all over the U.S. and around the world. For more information and to learn how you can participate or apply, please contact your local Habitat, or call 1-800-HABITAT (1-800-422-4828).\n" +
                "\n" +
                "Can anyone apply to be a Habitat homeowner?\n" +
                "\n" +
                "Yes. Habitat follows a nondiscriminatory policy of homebuyer selection. Neither race nor religion is a factor.");
        alert.showAndWait();
    }

    public void jumpToBuyItem(ActionEvent event) throws IOException {
        Node node=(Node) event.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("../View/ItemSearchView.fxml"));
        Scene scene = new Scene(root, 700, 573);
        Stage stage=(Stage) node.getScene().getWindow();
        stage.setTitle("Purchase An Item");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void jumpToLoginScreen(ActionEvent event) throws IOException {
        Node node=(Node) event.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("../View/LoginView2.fxml"));
        Scene scene = new Scene(root, 700, 600);
        Stage stage=(Stage) node.getScene().getWindow();
        stage.setTitle("Welcome To Habitat For Humanity");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void jumpToMainScreen(ActionEvent event) throws IOException {
        //confirmation for the user that the account has been created
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Logging Out");
        alert.setContentText("Successfully Logged Out");
        alert.showAndWait();

        Node node=(Node) event.getSource();
        Parent root = FXMLLoader.load(getClass().getResource("../View/LoginView2.fxml"));
        Scene scene = new Scene(root, 700, 600);
        Stage stage=(Stage) node.getScene().getWindow();
        stage.setTitle("Welcome To Habitat For Humanity");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
