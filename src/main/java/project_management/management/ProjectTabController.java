package project_management.management;

import Data.Project;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ProjectTabController {
    Project project;
    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private TextArea desciptionField;
    private int tabId;

    public void setTabContent(int Id, Project p){
        this.project=p;
        this.tabId= Id;
        nameLabel.setText("Project name: " + p.getName());
        priceLabel.setText("Price"+ p.getPrice());
        desciptionField.setText(p.getDescription());
    }

    public void updateTab(){
        nameLabel.setText(project.getName());
        priceLabel.setText(String.valueOf(project.getPrice()));
        desciptionField.setText(project.getDescription());
    }

    @FXML
    public void onSaveButtonClick(){
        project.setDescription(desciptionField.getText());
    }


}
