package project_management.management;

import Data.Project;
import Data.Task;
import Data.Status;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.ObjectInputFilter;
import java.net.URL;
import java.util.ResourceBundle;

public class TaskProgressController {

    private Project project;
    private Task selectedTask;
    @FXML
    private ListView<Task> toDo;
    @FXML
    private ListView<Task> onGoing;
    @FXML
    private ListView<Task> finished;
    @FXML
    private Label selectTask;

    public void update(){
        selectTask.setText("Selected task: " + selectedTask.getName());
    }

    public void loadWindow(Project p){
        this.project = p;
        for(Task t : project.getTasks()){
            switch((t.getStatus())){
                case Todo -> toDo.getItems().add(t);
                case Ongoing ->  onGoing.getItems().add(t);

                case Finished -> finished.getItems().add(t);

            }
        }

        toDo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Task>() {
            @Override
            public void changed(ObservableValue<? extends Task> observable, Task oldValue, Task newValue) {
                selectedTask=toDo.getSelectionModel().getSelectedItem();
                toDo.getSelectionModel().clearSelection();
                onGoing.getSelectionModel().clearSelection();
                finished.getSelectionModel().clearSelection();
                update();
            }
        });
        onGoing.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Task>() {
            @Override
            public void changed(ObservableValue<? extends Task> observable, Task oldValue, Task newValue) {
                selectedTask=onGoing.getSelectionModel().getSelectedItem();
                toDo.getSelectionModel().clearSelection();
                onGoing.getSelectionModel().clearSelection();
                finished.getSelectionModel().clearSelection();
                update();
            }
        });
        finished.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Task>() {
            @Override
            public void changed(ObservableValue<? extends Task> observable, Task oldValue, Task newValue) {
                selectedTask=finished.getSelectionModel().getSelectedItem();
                toDo.getSelectionModel().clearSelection();
                onGoing.getSelectionModel().clearSelection();
                finished.getSelectionModel().clearSelection();
                update();
            }
        });
    }

}
