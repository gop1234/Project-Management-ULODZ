package project_management.management;

import Data.IncomeExpense;
import Data.InvoiceUtils;
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
    private TableColumn<IncomeExpense, String> invoiceNumberColumn;

    @FXML
    private TableColumn<IncomeExpense, String> documentLinkColumn;

    @FXML
    private ChoiceBox<String> currencyChoiceBox;



    @FXML
    private Label profitLossLabel;

    public void loadWindow(Project project) {
        this.project = project;

        // Настраиваем валюты
        currencyChoiceBox.getItems().addAll("USD", "EUR", "JPY");
        currencyChoiceBox.setValue(project.getBaseCurrency());

        // Заполняем таблицы
        populateTables();

        // Рассчитываем прибыль/убытки
        calculateProfitLoss();
    }



    private void populateTables() {
        // Income/Expenses Table
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        currencyColumn.setCellValueFactory(new PropertyValueFactory<>("currency"));
        invoiceNumberColumn.setCellValueFactory(new PropertyValueFactory<>("invoiceNumber")); // Добавляем связь с invoiceNumber
        documentLinkColumn.setCellValueFactory(new PropertyValueFactory<>("documentLink")); // Добавляем связь с documentLink

        incomeExpensesTable.getItems().setAll(project.getIncomeExpenses());

        // Payments Table
        paymentDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        paymentAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        paymentCurrencyColumn.setCellValueFactory(new PropertyValueFactory<>("currency"));
        paymentStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        paymentsTable.getItems().setAll(project.getPayments());
    }

    private void updateExpensesTable() {
        if (project != null) {
            incomeExpensesTable.getItems().setAll(project.getIncomeExpenses());
            incomeExpensesTable.refresh(); // Обновляем таблицу вручную
        }
    }



    private void calculateProfitLoss() {
        String selectedCurrency = currencyChoiceBox.getValue();
        float profitLoss = project.calculateProfitLoss(selectedCurrency);
        profitLossLabel.setText(String.format("Profit/Loss: %.2f %s", profitLoss, selectedCurrency));
    }

    @FXML
    private void onAddIncomeExpense() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Income/Expense");
        dialog.setHeaderText("Enter details for Income/Expense:");
        dialog.setContentText("Format: Type,Amount,Currency,DocumentLink");

        dialog.showAndWait().ifPresent(input -> {
            try {
                String[] details = input.split(",");
                if (details.length != 4) {
                    throw new IllegalArgumentException("Invalid input format");
                }

                String type = details[0].trim();
                float amount = Float.parseFloat(details[1].trim());
                String currency = details[2].trim();
                String documentLink = details[3].trim();

                // Генерация Invoice Number
                String invoiceNumber = InvoiceUtils.generateInvoiceNumber();

                IncomeExpense newEntry = new IncomeExpense(type, amount, currency, invoiceNumber, documentLink);
                project.addIncomeExpense(newEntry);

                updateExpensesTable(); // Обновляем таблицу расходов
                calculateProfitLoss(); // Пересчитываем прибыль/убытки
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Ensure the format is correct: Type,Amount,Currency,DocumentLink");
                alert.showAndWait();
            }
        });
    }


    @FXML
    public void initialize() {
        currencyChoiceBox.getItems().addAll("USD", "EUR", "JPY", "GBP");
        currencyChoiceBox.setValue("USD");
        currencyChoiceBox.setOnAction(event -> {
            if (project != null) {
                onCurrencyChange();
            } else {
                System.err.println("Project is null, currency change ignored.");
            }
        });
    }



    @FXML
    private void onCurrencyChange() {
        if (project == null) {
            System.err.println("Project is null in onCurrencyChange");
            return; // Прерываем выполнение, если проект не установлен
        }

        String selectedCurrency = currencyChoiceBox.getValue(); // Получаем выбранную валюту
        project.setBaseCurrency(selectedCurrency); // Устанавливаем базовую валюту
        calculateProfitLoss(); // Пересчитываем прибыль/убытки
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
                calculateProfitLoss();

            } else {
                System.out.println("Invalid input format.");
            }
        });
    }

    @FXML
    private void onUpdateDocumentLink() {
        // Получаем выбранный элемент из таблицы Income/Expenses
        IncomeExpense selectedEntry = incomeExpensesTable.getSelectionModel().getSelectedItem();

        if (selectedEntry != null) {
            // Создаем диалоговое окно для ввода ссылки
            String currentLink = selectedEntry.getDocumentLink();
            if (currentLink == null || currentLink.isEmpty()) {
                currentLink = "N/A"; // Устанавливаем "N/A" по умолчанию, если ссылка отсутствует
            }

            TextInputDialog dialog = new TextInputDialog(currentLink);
            dialog.setTitle("Update Document Link");
            dialog.setHeaderText("Enter or update the document link (optional):");
            dialog.setContentText("Document Link:");

            // Если пользователь ввел ссылку, обновляем её
            dialog.showAndWait().ifPresent(link -> {
                // Проверяем, что пользователь ввел
                if (link.trim().isEmpty()) {
                    selectedEntry.setDocumentLink("N/A"); // Устанавливаем "N/A", если поле пустое
                } else {
                    selectedEntry.setDocumentLink(link.trim()); // Сохраняем введенную ссылку
                }

                incomeExpensesTable.refresh(); // Обновляем отображение таблицы
            });
        } else {
            // Если не выбран элемент, выводим ошибку
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select an entry to update the document link.");
            alert.showAndWait();
        }
    }


    @FXML
    private void onBackButtonClick() {
        Stage stage = (Stage) profitLossLabel.getScene().getWindow();
        stage.close();
    }


}
