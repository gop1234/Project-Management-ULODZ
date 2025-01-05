package project_management.management;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import Data.Project;

import java.io.IOException;

public class HelloController {

    private Scene scene;
    private Stage stage;
    private Parent parent;
    Project projects;

    @FXML
    protected void onNewButtonClick() {
        System.out.println("Hello");
    }
    @FXML
    protected void onDeleteButtonClick(){
        System.out.println("World");
    }

    @FXML
    protected void onOpenButtonClick(ActionEvent e) throws IOException {
        parent = FXMLLoader.load(getClass().getResource("Project-view.fxml"));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
}