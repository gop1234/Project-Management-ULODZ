package project_management.management;

import Data.DataController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import Data.Project;

import java.io.IOException;
import java.util.ArrayList;

public class HelloController {

    private Scene scene;
    private Stage stage;
    private Parent parent;

    @FXML
    private TabPane projectTabPane;
    private int tabCounter=0;

    @FXML
    private TextField projectName;

    @FXML
    protected void onNewButtonClick() throws IOException{
        if( projectName.getText().isEmpty()) return;
        Project p = new Project(projectName.getText());
        DataController.getInstance().getProjetcs().add(p);
        tabCounter++;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProjectTab-view.fxml"));
        AnchorPane tabContent = loader.load();
        ProjectTabController tabController = loader.getController();
        tabController.setTabContent(tabCounter,p);
        Tab newTab = new Tab(projectName.getText(),tabContent);
        projectTabPane.getTabs().add(newTab);
        projectName.setText("");
    }
    @FXML
    protected void onDeleteButtonClick(){
        if(projectTabPane.getTabs().isEmpty()) return;

        projectTabPane.getTabs().remove(projectTabPane.getSelectionModel().getSelectedItem());
    }

    @FXML
    protected void onOpenButtonClick(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Project-view.fxml"));
        parent = loader.load();
        ProjectController controller = loader.getController();
        controller.setProject(DataController.getInstance().getProjetcs().get(projectTabPane.getSelectionModel().getSelectedIndex()));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public void loadWindow() throws IOException {
        for (Project p : DataController.getInstance().getProjetcs()){
            tabCounter++;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProjectTab-view.fxml"));
            AnchorPane tabContent = loader.load();
            ProjectTabController tabController = loader.getController();
            tabController.setTabContent(tabCounter,p);
            Tab newTab = new Tab(p.getName(),tabContent);
            projectTabPane.getTabs().add(newTab);
        }
    }
}