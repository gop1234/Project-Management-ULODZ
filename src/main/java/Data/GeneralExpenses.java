package Data;

import java.time.LocalDate;

public class GeneralExpenses {
    private String name;
    private String type;
    private float amount;
    private String currency; // EUR, PLN, USD
    private LocalDate date;

    public GeneralExpenses(String name, String type, float amount, String currency, LocalDate date){
        this.name=name;
        this.type=type;
        this.amount=amount;
        this.currency=currency;
        this.date=date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getAmount() {
        return amount;
    }

    public String getAmountS(){
        return Double.toString(this.amount);
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
