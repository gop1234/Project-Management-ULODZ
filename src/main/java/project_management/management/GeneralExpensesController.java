package project_management.management;

import Data.DataController;
import Data.GeneralExpenses;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class GeneralExpensesController {
    private ArrayList<GeneralExpenses> expenses;
    private Parent parent;
    private Stage stage;
    private Scene scene;
    private String usingCur;
    @FXML
    private TableView<GeneralExpenses> table;
    @FXML
    private TableColumn<GeneralExpenses,String> name;
    @FXML
    private TableColumn<GeneralExpenses,String> type;
    @FXML
    private TableColumn<GeneralExpenses,String> amount;
    @FXML
    private TableColumn<GeneralExpenses, String> date;
    @FXML
    private ComboBox<String> currency;
    @FXML
    private Label total;

    public void loadWindow(){
        this.expenses= DataController.getInstance().getGeneralExpenses();
        this.currency.getItems().addAll("EUR","USD","PLN");

        name.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        type.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getType()));
        amount.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAmountS()));
        date.setCellValueFactory(data ->{
            LocalDate localDate = data.getValue().getDate();
            return new javafx.beans.property.SimpleStringProperty(localDate.toString());
        });
        table.getItems().setAll(expenses);
        if(!expenses.isEmpty()) {
            currency.getSelectionModel().select(expenses.get(0).getCurrency());
            this.usingCur=expenses.get(0).getCurrency();
            double total = 0;
            for(GeneralExpenses ge : expenses){
                total+=ge.getAmount();
            }
            this.total.setText(String.format("%.2f",total)+" "+usingCur);
        }
    }

    @FXML
    public void onReturnButtonCLick(ActionEvent e) throws IOException {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("hello-view.fxml"));
        parent=loader.load();
        HelloController controller = loader.getController();
        controller.loadWindow();
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onCurrencyComboBoxClicked(){
        for(GeneralExpenses ge :expenses){
            ge.setAmount(DataController.getInstance().changeCurrency(ge.getAmount(),usingCur,currency.getSelectionModel().getSelectedItem()));
        }
        usingCur=currency.getSelectionModel().getSelectedItem();
        updateTable();
    }
    @FXML
    public void onAddButtonClicked(){
        VBox expensesDetails = new VBox(10);

        expensesDetails.setStyle("-fx-border-color: black; -fx-padding: 10px; -fx-pref-width: 250px; -fx-pref-height: 300px;");

        Stage stage = new Stage();
        Scene scene = new Scene(expensesDetails);

        Label name = new Label("Name:");
        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        Label type = new Label("Type:");
        TextField typeFiled = new TextField();
        typeFiled.setPromptText("type");

        Label dateLabel = new Label("Date:");
        DatePicker datePicker = new DatePicker(LocalDate.now());
        datePicker.setEditable(false);

        Label money = new Label("Amount ( "+this.usingCur+" ):");
        TextField moneyField = new TextField();
        moneyField.setPromptText("Amount");
        HBox buttonBox = new HBox(12);
        Button close = new Button("Close");
        close.setOnAction(event->{
            stage.close();
        });
        Button save = new Button("Save");
        save.setOnAction(event ->{
            if(nameField.getText().isEmpty() || typeFiled.getText().isEmpty() || moneyField.getText().isEmpty()) return;
            expenses.add(new GeneralExpenses(nameField.getText(),typeFiled.getText(),Float.parseFloat(moneyField.getText()),usingCur,datePicker.getValue()));
            updateTable();
            stage.close();
        });
        buttonBox.getChildren().addAll(close,save);
        expensesDetails.getChildren().addAll(name,nameField,type,typeFiled,dateLabel,datePicker,money,moneyField,buttonBox);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onDeleteButtonClicked(){
        expenses.remove(table.getSelectionModel().getSelectedItem());
        updateTable();
    }

    private void updateTable(){
        float t=0;
        for(GeneralExpenses ge:expenses){
            t+=ge.getAmount();
        }
        total.setText(String.format("%.2f",t)+" "+usingCur);
        table.getItems().setAll(expenses);;
    }

}
