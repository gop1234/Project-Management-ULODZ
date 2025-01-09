package Data;

import java.time.LocalDate;

public class Payment {
    private String name="";
    private LocalDate date;
    private float amount;
    private String currency;
    private String status; // "Paid" or "Unpaid"

    public Payment(String date, float amount, String currency, String status) {
        this.date = LocalDate.parse(date);
        this.amount = amount;
        this.currency = currency;
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public float getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
