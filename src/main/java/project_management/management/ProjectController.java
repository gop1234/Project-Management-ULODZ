package project_management.management;

import Data.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ProjectController {

    private Project project;
    private Parent parent;
    private Stage stage;
    private  Scene scene;

    @FXML
    private Label projectName;

    @FXML
    protected void onGoBackButtonCLick(ActionEvent e) throws IOException {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("hello-view.fxml"));
        parent=loader.load();
        HelloController controller = loader.getController();
        controller.loadWindow();
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public void setProject(Project p) {
        this.project = p;
        projectName.setText(project.getName());
    }
}
