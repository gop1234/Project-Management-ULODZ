package project_management.management;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProjectController {

    private Parent parent;
    private Stage stage;
    private  Scene scene;

    @FXML
    protected void onGoBackButtonCLick(ActionEvent e) throws IOException {
        parent = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
}
