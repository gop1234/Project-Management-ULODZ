package project_management.management;

import Data.DataController;
import Data.IncomeExpense;
import Data.Project;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ProjectTabController {
    Project project;
    @FXML
    private Label nameLabel;
    @FXML
    private Label total;
    @FXML
    private Label income;
    @FXML
    private Label expenses;
    @FXML
    private ComboBox<String> currency;
    @FXML
    private TextArea desciptionField;
    private int tabId;

    public void setTabContent(int Id, Project p){
        this.project=p;
        this.tabId= Id;
        currency.getItems().addAll("EUR","USD","PLN");
        currency.getSelectionModel().select(0);
        nameLabel.setText("Project name: " + p.getName());
        total.setText("total"+ p.getPrice());
        desciptionField.setText(p.getDescription());
        float temp=0;
        for(IncomeExpense i:p.getIncomeExpenses()){
            if(i.getType().equals("Income")) temp+= DataController.getInstance().changeCurrency(i.getAmount(),i.getCurrency(),currency.getSelectionModel().getSelectedItem());
        }
        income.setText("Income:"+temp);
        temp=0;
        for(IncomeExpense i:p.getIncomeExpenses()){
            if(i.getType().equals("Expense")) temp+= DataController.getInstance().changeCurrency(i.getAmount(),i.getCurrency(),currency.getSelectionModel().getSelectedItem());
        }
        expenses.setText("Expenses:"+temp);
    }

    public void updateTab(){
        nameLabel.setText(project.getName());
        total.setText(String.valueOf(project.getPrice()));
        desciptionField.setText(project.getDescription());
    }

    @FXML
    public void onSaveButtonClick(){
        project.setDescription(desciptionField.getText());
    }


}
