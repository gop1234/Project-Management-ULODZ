package project_management.management;

import Data.Issue;
import Data.Project;
import Data.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class TaskProgressController {

    private Project project;
    private Task selectedTask;
    private Issue selectedIssue;
    @FXML
    private ListView<Task> toDo;
    @FXML
    private ListView<Task> onGoing;
    @FXML
    private ListView<Task> finished;
    @FXML
    private Label selectTask;
    @FXML
    private Label numberIssues;
    @FXML
    private TextField issueName;
    @FXML
    private TextArea issueDesciption;
    @FXML
    private ComboBox<Issue> issues;


    public void update(){
        if(selectedTask == null) {
            System.out.println("Es null ");
            return;
        }
        this.numberIssues.setText("Issues:" + selectedTask.getnIssues());
        if(selectedIssue == null){
            this.issueName.setText("");
            this.issueDesciption.setText("");

        }
        selectTask.setText("Selected task: " + selectedTask.getName());
        /*if(!issues.getItems().isEmpty()){
            issues.getItems().removeAll(issues.getItems());
        }*/
        for(Issue i : selectedTask.getIssues()){
            issues.getItems().add(i);
        }

        toDo.getItems().removeAll(project.getTasks());
        onGoing.getItems().removeAll(project.getTasks());
        finished.getItems().removeAll(project.getTasks());
        for(Task t : project.getTasks()){
            switch((t.getStatus())){
                case Todo -> toDo.getItems().add(t);
                case Ongoing ->  onGoing.getItems().add(t);
                case Finished -> finished.getItems().add(t);

            }
        }

    }

    @FXML
    public void onToDoClicked(){
        Task t = toDo.getSelectionModel().getSelectedItem();
        if(t == null) return;
        selectedTask=t;
        toDo.getSelectionModel().clearSelection();
        update();
    }

    @FXML
    public void onOnGoingClicked(){
        Task t = onGoing.getSelectionModel().getSelectedItem();
        if(t == null) return;
        selectedTask=t;
        onGoing.getSelectionModel().clearSelection();
        update();
    }

    @FXML
    public void onFinishedClicked(){
        Task t = finished.getSelectionModel().getSelectedItem();
        if(t == null) return;
        selectedTask=t;
        finished.getSelectionModel().clearSelection();
        update();
    }

    @FXML
    public void onAdvanceButtonClicked(){
        if(selectedTask!=null) selectedTask.nextStatus();
        update();
    }

    @FXML
    public void onPreviusButtonClicked(){
        if(selectedTask!=null) selectedTask.previusStatus();
        update();
    }

    @FXML
    public void onNewIssueButtonClicked(){
        issueDesciption.setText("");
        issueName.setText("");
    }

    @FXML
    public void oncloseIssueButtonClicked(){
        if(selectedIssue!=null){
            selectedTask.deleteIssue(selectedIssue);
            selectedIssue=null;
            issues.getSelectionModel().clearSelection();
            update();
        }
    }

    @FXML
    public void onSaveButtonClicked(){
        if(issueName.getText().isEmpty() || issueDesciption.getText().isEmpty()) return;
        Issue i = new Issue(issueName.getText());
        i.setDescription(issueDesciption.getText());
        selectedTask.addIssues(i);
        update();
    }
    @FXML
    public void onIssuesclicked(){
        selectedIssue=issues.getSelectionModel().getSelectedItem();
        issueName.setText(selectedIssue.getName());
        issueDesciption.setText(selectedIssue.getDescription());
        issues.getSelectionModel().clearSelection();
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


    }

}
