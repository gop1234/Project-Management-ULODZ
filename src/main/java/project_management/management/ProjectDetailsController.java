package project_management.management;

import Data.IncomeExpense;
import Data.Payment;
import Data.Project;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ProjectDetailsController {

    private Project project;

    @FXML
    private TableView<IncomeExpense> incomeExpensesTable;
    @FXML
    private TableColumn<IncomeExpense, String> typeColumn;
    @FXML
    private TableColumn<IncomeExpense, Float> amountColumn;
    @FXML
    private TableColumn<IncomeExpense, String> currencyColumn;

    @FXML
    private TableView<Payment> paymentsTable;
    @FXML
    private TableColumn<Payment, String> paymentDateColumn;
    @FXML
    private TableColumn<Payment, Float> paymentAmountColumn;
    @FXML
    private TableColumn<Payment, String> paymentCurrencyColumn;
    @FXML
    private TableColumn<Payment, String> paymentStatusColumn;

    @FXML
    private Label profitLossLabel;

    public void loadWindow(Project project) {
        this.project = project;
        populateTables();
        calculateProfitLoss();
    }

    private void populateTables() {
        // Income/Expenses Table
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        currencyColumn.setCellValueFactory(new PropertyValueFactory<>("currency"));

        incomeExpensesTable.getItems().setAll(project.getIncomeExpenses());

        // Payments Table
        paymentDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        paymentAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        paymentCurrencyColumn.setCellValueFactory(new PropertyValueFactory<>("currency"));
        paymentStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        paymentsTable.getItems().setAll(project.getPayments());
    }

    private void calculateProfitLoss() {
        float totalIncome = (float) project.getIncomeExpenses().stream()
                .filter(entry -> entry.getType().equals("Income"))
                .mapToDouble(IncomeExpense::getAmount)
                .sum();

        float totalExpenses = (float) project.getIncomeExpenses().stream()
                .filter(entry -> entry.getType().equals("Expense"))
                .mapToDouble(IncomeExpense::getAmount)
                .sum();

        profitLossLabel.setText(String.format("Profit/Loss: %.2f", totalIncome - totalExpenses));
    }

    @FXML
    private void onAddIncomeExpense() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Income/Expense");
        dialog.setHeaderText("Enter details for Income/Expense:");
        dialog.setContentText("Format: Type,Amount,Currency");

        // Example: Income,1000,USD
        dialog.showAndWait().ifPresent(input -> {
            String[] details = input.split(",");
            if (details.length == 3) {
                String type = details[0];
                float amount = Float.parseFloat(details[1]);
                String currency = details[2];

                IncomeExpense newEntry = new IncomeExpense(type, amount, currency);
                project.addIncomeExpense(newEntry);
                incomeExpensesTable.getItems().add(newEntry);
                calculateProfitLoss();
            } else {
                System.out.println("Invalid input format.");
            }
        });
    }

    @FXML
    private void onAddPayment() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Payment");
        dialog.setHeaderText("Enter details for Payment:");
        dialog.setContentText("Format: Date,Amount,Currency,Status");

        // Example: 2023-01-01,500,USD,Paid
        dialog.showAndWait().ifPresent(input -> {
            String[] details = input.split(",");
            if (details.length == 4) {
                String date = details[0];
                float amount = Float.parseFloat(details[1]);
                String currency = details[2];
                String status = details[3];

                Payment newPayment = new Payment(date, amount, currency, status);
                project.addPayment(newPayment);
                paymentsTable.getItems().add(newPayment);
            } else {
                System.out.println("Invalid input format.");
            }
        });
    }

    @FXML
    private void onBackButtonClick() {
        Stage stage = (Stage) profitLossLabel.getScene().getWindow();
        stage.close();
    }
}
