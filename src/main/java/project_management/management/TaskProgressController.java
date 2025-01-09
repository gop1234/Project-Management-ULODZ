package project_management.management;

import Data.Issue;
import Data.Project;
import Data.Task;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;


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
    private TextArea issueDesciption;
    @FXML
    private ComboBox<Issue> issues;


    public void update(){
        toDo.getItems().removeAll(toDo.getItems());
        onGoing.getItems().removeAll(onGoing.getItems());
        finished.getItems().removeAll(finished.getItems());
        for(Task t : project.getTasks()){
            switch((t.getStatus())){
                case Todo -> toDo.getItems().add(t);
                case Ongoing ->  onGoing.getItems().add(t);
                case Finished -> finished.getItems().add(t);

            }
        }
        if(selectedTask == null) {
            System.out.println("Es null ");
            return;
        }
        this.numberIssues.setText("Issues:" + selectedTask.getnIssues());
        if(selectedIssue == null){
            this.issueDesciption.setText("");
        }
        selectTask.setText("Selected task: " + selectedTask.getName());
        if(!issues.getItems().isEmpty()){
            issues.getItems().removeAll(issues.getItems());
        }
        for(Issue i : selectedTask.getIssues()){
            issues.getItems().add(i);
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
        if(selectedTask==null) return;
        VBox issueDetails = new VBox(10);

        issueDetails.setStyle("-fx-border-color: black; -fx-padding: 10px; -fx-pref-width: 250px; -fx-pref-height: 300px;");

        Stage stage = new Stage();
        Scene scene = new Scene(issueDetails);

        TextField name = new TextField();
        name.setPromptText("Name of Issue");
        TextArea description = new TextArea();
        description.setPromptText("Desciption");
        Label creationDate = new Label("CreationDate: "+ LocalDate.now().toString());
        HBox buttonsBox = new HBox(12);
        buttonsBox.setStyle("-fx-alignment: center;");
        Button close = new Button("Close");
        close.setOnAction(event -> {stage.close();});
        Button save = new Button("Save");
        save.setOnAction(event -> {
            Issue i = new Issue(name.getText(),description.getText(),LocalDate.now());
            selectedTask.addIssues(i);
            selectedIssue=i;
            update();
            stage.close();
        });
        buttonsBox.getChildren().addAll(close,save);
        issueDetails.getChildren().addAll(name,description,creationDate,buttonsBox);

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void onEditIssueButtonClicked(){
        if(selectedIssue==null) return;
        VBox issueDetails = new VBox(10);

        issueDetails.setStyle("-fx-border-color: black; -fx-padding: 10px; -fx-pref-width: 250px; -fx-pref-height: 300px;");

        Stage stage = new Stage();
        Scene scene = new Scene(issueDetails);

        TextField name = new TextField();
        name.setText(selectedIssue.getName());
        TextArea description = new TextArea();
        description.setPromptText(selectedIssue.getDescription());
        Label creationDate = new Label("CreationDate: "+ selectedIssue.getCreationDate().toString());
        HBox buttonsBox = new HBox(12);
        buttonsBox.setStyle("-fx-alignment: center;");
        Button close = new Button("Close");
        close.setOnAction(event -> {stage.close();});
        Button save = new Button("Save");
        save.setOnAction(event -> {
            selectedIssue.setName(name.getText());
            selectedIssue.setDescription(description.getText());
            update();
            stage.close();
        });
        buttonsBox.getChildren().addAll(close,save);
        issueDetails.getChildren().addAll(name,description,creationDate,buttonsBox);

        stage.setScene(scene);
        stage.show();
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
    public void onRefreshButtonClicked(){
        update();
    }
    @FXML
    public void onIssuesclicked(){
        selectedIssue=issues.getSelectionModel().getSelectedItem();
        issueDesciption.setText(selectedIssue.getDescription());
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
