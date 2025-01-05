package project_management.management;

import Data.Project;
import Data.Task;
import Data.Status;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.ObjectInputFilter;

public class TaskProgressController {

    private Project project;
    @FXML
    private ListView<Task> toDo;
    @FXML
    private ListView<Task> onGoing;
    @FXML
    private ListView<Task> finished;
    public void loadWindow(Project p){
        this.project = p;
        for(Task t : project.getTasks()){
            switch((t.getStatus())){
                case Todo -> toDo.getItems().add(t);
                case Ongoing ->  onGoing.getItems().add(t);

                case Finished -> finished.getItems().add(t);

            }
        }
    }

}
