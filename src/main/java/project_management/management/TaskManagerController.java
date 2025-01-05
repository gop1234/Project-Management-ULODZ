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
    @FXML private Button addCardButton; // Botón para agregar nuevas cartas

    private ObservableList<Task> taskList;

    /*


    // Método para agregar una nueva carta
    @FXML
    private void addNewCard() {
        // Crear una nueva carta
        VBox newCard = new VBox(10); // Espacio de 10 entre elementos
        newCard.setStyle("-fx-background-color: lightgray; -fx-padding: 10;");

        // Crear un TextField para que el usuario ingrese el nombre de la tarjeta
        TextField cardTextField = new TextField();
        cardTextField.setPromptText("Ingrese nombre de la tarjeta");

        // Crear un contenedor para las tarjetas dentro de esta carta
        VBox cardCardsContainer = new VBox(5); // Espacio de 5 entre tarjetas
        cardCardsContainer.setStyle("-fx-background-color: white; -fx-padding: 10;");

        // Crear un botón para añadir tarjetas a esta carta
        Button addCardButton = new Button("Añadir tarjeta");
        addCardButton.setOnAction(e -> addCardToCard(cardCardsContainer, cardTextField));

        // Añadir el campo de texto y el botón a la carta
        newCard.getChildren().addAll(cardTextField, addCardButton, cardCardsContainer);

        // Añadir la nueva carta al contenedor principal
        cardsContainer.getChildren().add(newCard);
    }

    // Método para añadir una tarjeta dentro de una carta
    private void addCardToCard(VBox cardCardsContainer, TextField cardTextField) {
        String cardName = cardTextField.getText();
        if (!cardName.isEmpty()) {
            // Crear una tarjeta como un Label
            Label newCard = new Label(cardName);
            newCard.setStyle("-fx-background-color: lightblue; -fx-padding: 5px;");
            // Añadir la tarjeta al contenedor de tarjetas dentro de la carta
            cardCardsContainer.getChildren().add(newCard);
            // Limpiar el campo de texto
            cardTextField.clear();
        }
    }

     */

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
}
