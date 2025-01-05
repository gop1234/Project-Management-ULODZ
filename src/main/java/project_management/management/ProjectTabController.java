package project_management.management;

import Data.Project;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProjectTabController {
    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLabel;
    private int tabId;

    public void setTabContent(int Id, Project p){
        this.tabId= Id;
        nameLabel.setText("Project name: " + p.getName());
        priceLabel.setText("Price"+ p.getPrice());
    }
}
