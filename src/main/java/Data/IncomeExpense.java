package Data;

public class IncomeExpense {
    private String type; // "Income" or "Expense"
    private float amount;
    private String currency;

    public IncomeExpense(String type, float amount, String currency) {
        this.type = type;
        this.amount = amount;
        this.currency = currency;
    }

    public String getType() {
        return type;
    }

    public float getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}
