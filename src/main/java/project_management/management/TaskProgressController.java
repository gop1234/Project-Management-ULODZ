package project_management.management;

import Data.Project;
import Data.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

public class TaskProgressController {

    Project project;
    @FXML
    private TableColumn toDo;
    @FXML
    private TableColumn onGoing;
    @FXML
    private TableColumn finished;

    public void loadWindow(){
        for(Task t : project.getTasks()){
            switch()
        }
    }

}
