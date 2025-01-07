package project_management.management;

import Data.DataController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import javafx.scene.Node;
import Data.Project;

import java.io.IOException;




/**
 * Dashboard . Was autogenerated and I didn’t change the name.
 */

public class HelloController {

    private Scene scene; // for the change windows
    private Stage stage; // for the change windows
    private Parent parent; // for the change windows

    @FXML
    private Label user;
    @FXML
    private TabPane projectTabPane; // refers to the tabPane on the window

    private int tabCounter = 0; // how many tabs are

    @FXML
    private TextField projectName; // refers to the project name field in the window

    /**
     * Action when clicking New.
     * Here we load the template of ProjectTabController where we can
     * put all the information required to appear in the frontpage.
     */
    @FXML
    protected void onNewButtonClick() throws IOException {
        if (projectName.getText().isEmpty()) return;
        Project p = new Project(projectName.getText());
        DataController.getInstance().getProjetcs().add(p);
        tabCounter++;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProjectTab-view.fxml"));
        AnchorPane tabContent = loader.load();
        ProjectTabController tabController = loader.getController();

        tabController.setTabContent(tabCounter, p);
        Tab newTab = new Tab(projectName.getText(), tabContent);
        projectTabPane.getTabs().add(newTab);
        projectName.setText("");
        System.out.println("New project added: " + p.getName());
    }

    /**
     * Delete the current project and corresponding tab.
     */
    @FXML
    protected void onDeleteButtonClick() {
        if (projectTabPane.getTabs().isEmpty()) return;
        int index = projectTabPane.getSelectionModel().getSelectedIndex();
        DataController.getInstance().getProjetcs().remove(index);
        projectTabPane.getTabs().remove(index);
        System.out.println("Project deleted at index: " + index);
    }

    /**
     * Open a new window with the information of the project.
     */
    @FXML
    protected void onOpenButtonClick(ActionEvent e) throws IOException {
        if (projectTabPane.getSelectionModel().getSelectedIndex() < 0) {
            System.out.println("No project selected");
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Project-view.fxml"));
        parent = loader.load();
        ProjectController controller = loader.getController();
        controller.loadWindow(DataController.getInstance().getProjetcs().get(projectTabPane.getSelectionModel().getSelectedIndex()));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void onlogoutButtonCliked(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login-view.fxml"));
        parent = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
        System.out.println("Logged out.");
    }

    /**
     * Function that must be used every time we load the window.
     * Information is stored in DataController, so must be loaded into the window.
     */
    public void loadWindow() throws IOException {
        user.setText("Welcome: " + DataController.getInstance().getLogedUser().getName());
        for (Project p : DataController.getInstance().getProjetcs()) {

            tabCounter++;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProjectTab-view.fxml"));
            AnchorPane tabContent = loader.load();
            ProjectTabController tabController = loader.getController();

            tabController.setTabContent(tabCounter, p);
            Tab newTab = new Tab(p.getName(), tabContent);
            projectTabPane.getTabs().add(newTab);
        }
        System.out.println("Loaded projects: " + DataController.getInstance().getProjetcs().size());
    }
}

