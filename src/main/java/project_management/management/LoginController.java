package project_management.management;

import Data.AES;
import Data.DataController;
import Data.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    private Parent parent;
    private Scene scene;
    private Stage stage;

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    public void onAdminButtonClicked(ActionEvent e) throws IOException {
        User admin = new User("Project Manger",0);
        DataController.getInstance().setLogedUser(admin);
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("hello-view.fxml"));
        parent=loader.load();
        HelloController controller = loader.getController();
        controller.loadWindow();
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onLoginButtonClicked(ActionEvent e) throws IOException{

        if(username.getText().isEmpty() || password.getText().isEmpty())return;
        User u = new User(username.getText(),0);
        DataController.getInstance().setLogedUser(u);
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("hello-view.fxml"));
        parent=loader.load();
        HelloController controller = loader.getController();
        controller.loadWindow();
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }



}
