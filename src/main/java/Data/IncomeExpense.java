package Data;

public class IncomeExpense {
    private String type; // "Income" or "Expense"
    private float amount;
    private String currency;
    private String invoiceNumber; // Номер счета
    private String documentLink = "N/A"; // Ссылка на документ

    public IncomeExpense(String type, float amount, String currency, String invoiceNumber, String documentLink) {
        this.type = type;
        this.amount = amount;
        this.currency = currency;
        this.invoiceNumber = invoiceNumber;
        this.documentLink = (documentLink == null || documentLink.trim().isEmpty()) ? "N/A" : documentLink;
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

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getDocumentLink() {
        return documentLink;
    }

    public void setDocumentLink(String documentLink) {
        this.documentLink = documentLink;
    }


}
