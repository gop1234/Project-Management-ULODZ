package project_management.management;

import Data.DataController;
import Data.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
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
    private TabPane generalTab;
    int tabCounter=1;

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


    /**
     *Function that must run when loading the window*/
    public void loadWindow(Project p) throws IOException{
        this.project = p;
        projectName.setText(project.getName());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProjectTab-view.fxml"));
        AnchorPane tabContent = loader.load();
        ProjectTabController tabController = loader.getController();
        tabController.setTabContent(tabCounter,p);
        Tab newTab = new Tab("Resume",tabContent);
        generalTab.getTabs().add(newTab);


        loader = new FXMLLoader(getClass().getResource("TaskManager-view.fxml"));
        tabContent = loader.load();
        TaskManagerController taskManagerController = loader.getController();
        taskManagerController.loadWindow(project);
        newTab = new Tab("Task Manager",tabContent);
        generalTab.getTabs().add(newTab);

        loader = new FXMLLoader(getClass().getResource("TaskProgress-view.fxml"));
        tabContent = loader.load();
        TaskProgressController TPController = loader.getController();
        TPController.loadWindow(p);
        newTab = new Tab("Task Traking",tabContent);
        generalTab.getTabs().add(newTab);

        loader = new FXMLLoader(getClass().getResource("ProjectDetails-view.fxml"));
        tabContent = loader.load();
        ProjectDetailsController controller = loader.getController();
        controller.loadWindow(p);
        newTab = new Tab("Income/Expenses",tabContent);
        generalTab.getTabs().add(newTab);
    }
}
