package project_management.management;

import Data.Project;
import Data.Status; // Asegúrate de importar correctamente
import Data.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import java.time.LocalDate;
import javafx.scene.control.*;
import Data.Card;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class TaskManagerController {

    Project project;
    @FXML
    private Button add;
    @FXML
    private Button delete;
    @FXML
    private Button edit;
    @FXML
    private FlowPane cardsContainer; // Contenedor de cartas


    // Método para añadir una carta con título
    @FXML
    private void addCard() {
        VBox card = new VBox(10);
        card.setStyle("-fx-border-color: black; -fx-padding: 5px; -fx-pref-width: 150px; -fx-pref-height: 200px;");

        TextField titleField = new TextField();
        titleField.setPromptText("Introduce el título de la carta");
        titleField.setStyle("-fx-font-size: 10px; -fx-pref-width: 100px;");


        Button addTaskButton = new Button("Añadir Tarjeta");
        addTaskButton.setStyle("-fx-font-size: 6px;");
        addTaskButton.setOnAction(e -> addTask(card));

        card.getChildren().addAll(titleField, addTaskButton);
        cardsContainer.getChildren().add(card);
    }

    // Método para añadir una tarjeta dentro de la carta
    private void addTask(VBox card) {
        // Crear una nueva tarjeta (objeto Card)
        Card newCard = new Card("Nueva Tarjeta");

        // Crear un botón para la tarjeta
        Button taskButton = newCard.getButton();
        taskButton.setStyle("-fx-font-size: 9px;");

        // Acción al hacer doble clic sobre la tarjeta
        taskButton.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                openCardDetails(newCard);
            }
        });

        // Añadir la tarjeta al contenedor de la carta
        card.getChildren().add(taskButton);
    }

    // Método para abrir los detalles de la tarjeta
    private void openCardDetails(Card card) {
        // Crear un VBox para los detalles de la tarjeta
        VBox detailsBox = new VBox(10);
        detailsBox.setStyle("-fx-border-color: black; -fx-padding: 10px; -fx-pref-width: 250px; -fx-pref-height: 300px;");

        // Mostrar los detalles de la tarjeta
        TextField nameField = new TextField(card.getName());
        nameField.setPromptText("Nombre de la tarjeta");

        TextArea descriptionArea = new TextArea(card.getDescription());
        descriptionArea.setPromptText("Descripción");

        //DatePicker creationDatePicker = new DatePicker(card.getCreationDate());
        //creationDatePicker.setEditable(false);
        //creationDatePicker.setMouseTransparent(true);  // Esto hace que el DatePicker no sea interactivo con el ratón

        Label creationDateLabel = new Label("Fecha de creación: " + card.getCreationDate().toString());
        creationDateLabel.setStyle("-fx-font-size: 12px;");

        DatePicker expirationDatePicker = new DatePicker(card.getExpirationDate());
        expirationDatePicker.setOnAction(e -> {
            card.setExpirationDate(expirationDatePicker.getValue());
            card.setDuration(java.time.temporal.ChronoUnit.DAYS.between(card.getCreationDate(), card.getExpirationDate()));
        });

        Label durationLabel = new Label("Duración: " + card.getDuration() + " días");

        Button saveButton = new Button("Guardar");
        saveButton.setOnAction(e -> {
            card.setName(nameField.getText());
            card.setDescription(descriptionArea.getText());

        });

        // Añadir los elementos al VBox
        detailsBox.getChildren().addAll(nameField, descriptionArea, creationDateLabel, expirationDatePicker, durationLabel, saveButton);

        // Mostrar la ventana emergente
        Stage stage = new Stage();
        Scene scene = new Scene(detailsBox);
        stage.setScene(scene);
        stage.show();
    }



    /*
    // Método para añadir una carta con título
    @FXML
    private void addCard() {
        // Crear un VBox para la carta
        VBox card = new VBox(10);
        card.setStyle("-fx-border-color: black; -fx-padding: 5px; -fx-pref-width: 150px; -fx-pref-height: 200px;");

        // Crear un TextField para que el usuario ingrese el título de la carta
        TextField titleField = new TextField();
        titleField.setPromptText("Introduce el título de la carta");
        titleField.setStyle("-fx-font-size: 10px; -fx-pref-width: 100px;");

        // Botón para añadir tarjetas dentro de la carta
        Button addTaskButton = new Button("Añadir Tarjeta");
        addTaskButton.setStyle("-fx-font-size: 8px;");
        addTaskButton.setOnAction(e -> addTask(card));

        // Añadir el campo de texto y el botón a la carta
        card.getChildren().addAll(titleField, addTaskButton);

        // Añadir la carta al contenedor de cartas
        cardsContainer.getChildren().add(card);
    }

    // Método para añadir una tarjeta dentro de la carta
    private void addTask(VBox card) {
        // Crear un nuevo TextField para la tarjeta
        TextField taskField = new TextField();
        taskField.setPromptText("Nombre de la tarjeta");
        taskField.setStyle("-fx-font-size: 8px; -fx-pref-width: 80px;");

        // Añadir la tarjeta a la carta
        card.getChildren().add(taskField);
    }
    */




}