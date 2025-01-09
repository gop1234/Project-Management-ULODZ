package Data;

import java.time.LocalDate;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Card {
    private String name;
    private String description;
    private LocalDate creationDate;
    private LocalDate expirationDate;
    private long duration; // en días
    private float price;
    private Button button;  // El botón asociado a la tarjeta
    private Button buttonSave;
    private Button buttonDelete;

    // Constructor
    public Card(String name) {
        this.name = name;
        this.creationDate = LocalDate.now();
        this.expirationDate = creationDate.plusDays(30);
        this.duration = java.time.temporal.ChronoUnit.DAYS.between(creationDate, expirationDate);
        this.price=0;
        this.button = new Button(name);  // El nombre de la tarjeta es el texto del botón
        this.buttonSave = new Button("Save");
        this.buttonDelete = new Button("Delete");
    }

    // Métodos getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        if (button != null) {
            button.setText(name);  // Si el botón existe, actualiza el texto
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Button getButton() {
        return button;  // Devuelve el botón asociado
    }

    public Button getButtonSave() {
        return buttonSave;
    }

    public Button getButtonDelete() {
        return buttonDelete;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
