package project_management.management;

import Data.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class TaskManagerController {

    Project project;


    @FXML
    private FlowPane cardsContainer; // Contenedor de cartas
    private ArrayList<CardList> cardLists = new ArrayList<CardList>();


    public void loadWindow(Project p){
        this.project=p;
        for(String s : project.getCategories()){
            addCardList(s);
        }
    }

    // Método para añadir una lista con título
    @FXML
    private void addCardList() {

        CardList newCardList = new CardList("New List");

        newCardList.getVcard().setStyle("-fx-border-color: black; -fx-padding: 5px; -fx-pref-width: 150px; -fx-pref-height: 200px;");

        TextField titleField = new TextField();
        titleField.setPromptText(newCardList.getName());
        titleField.setStyle("-fx-font-size: 10px; -fx-pref-width: 100px;");

        // Añadir un ChangeListener al TextField para actualizar el nombre de la CardList en tiempo real
        titleField.textProperty().addListener((observable, oldValue, newValue) -> {
            newCardList.setName(newValue);  // Cambiar el nombre de la CardList mientras el usuario escribe
            //System.out.println("Nuevo nombre: " + newCardList.getName());
        });

        // Crear los botones dentro de un HBox (horizontal)
        HBox buttonsBox = new HBox(12); // Espaciado entre los botones
        buttonsBox.setStyle("-fx-alignment: center;"); // Alineación centrada de los botones

        newCardList.getAddCardButton().setStyle("-fx-font-size: 10px;");
        newCardList.getAddCardButton().setOnAction(e -> addCard(newCardList.getVcard(), newCardList));

        // Creamos el botón de borrado
        newCardList.getDeleteButton().setStyle("-fx-font-size: 10px;");
        newCardList.getDeleteButton().setOnAction(e -> {
            if (newCardList.getCardNumber() > 0) return;
            cardsContainer.getChildren().remove(newCardList.getVcard());
            this.cardLists.remove(newCardList);
        });

        // Añadir ambos botones al HBox
        buttonsBox.getChildren().addAll(newCardList.getAddCardButton(), newCardList.getDeleteButton());

        newCardList.getVcard().getChildren().addAll(titleField, buttonsBox);
        cardsContainer.getChildren().add(newCardList.getVcard());
        cardLists.add(newCardList);
    }

    // Método para añadir una tarjeta dentro de la carta
    private void addCard(VBox card, CardList cardList) {
        // Crear una nueva tarjeta (objeto Card)
        Card newCard = new Card("New Task");

        System.out.println("CardList: " + cardList.getName());
        cardList.setCardNumber( cardList.getCardNumber()+1 );

        // Crear un botón para la tarjeta
        Button taskButton = newCard.getButton();
        //taskButton.setStyle("-fx-font-size: 9px;");
        taskButton.setStyle("-fx-pref-width: 200px; -fx-pref-height: 20px; -fx-font-size: 10px;");

        // Acción al hacer doble clic sobre la tarjeta
        taskButton.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                openCardDetails(newCard, card, cardList);
            }
        });

        // Añadir la tarjeta al contenedor de la carta
        card.getChildren().add(taskButton);
        cardList.getCards().add(newCard);
    }

    // Método para abrir los detalles de la tarjeta

    private void openCardDetails(Card card, VBox cards, CardList cardList) {
        // Crear un VBox para los detalles de la tarjeta
        VBox detailsBox = new VBox(10);

        detailsBox.setStyle("-fx-border-color: black; -fx-padding: 10px; -fx-pref-width: 300px; -fx-pref-height: 375px;");

        Stage stage = new Stage();
        Scene scene = new Scene(detailsBox);

        // Mostrar los detalles de la tarjeta
        TextField nameField = new TextField(card.getName());
        nameField.setPromptText("Task Name");

        TextArea descriptionArea = new TextArea(card.getDescription());
        descriptionArea.setPromptText("Description");

        //DatePicker creationDatePicker = new DatePicker(card.getCreationDate());
        //creationDatePicker.setEditable(false);
        //creationDatePicker.setMouseTransparent(true);  // Esto hace que el DatePicker no sea interactivo con el ratón

        Label creationDateLabel = new Label("Start Date: ");
        creationDateLabel.setStyle("-fx-font-size: 12px;");
        DatePicker startDatePicker = new DatePicker(card.getCreationDate());
        startDatePicker.setEditable(false);
        startDatePicker.setOnAction(e -> {
                card.setCreationDate(startDatePicker.getValue());
                //card.setDuration(java.time.temporal.ChronoUnit.DAYS.between(card.getCreationDate(), card.getExpirationDate()));
        });
        Label endDateLabel = new Label("End Date: " + card.getExpirationDate().toString());
        endDateLabel.setStyle("-fx-font-size: 12px;");

        Label durationLabel = new Label("Duration: ");
        TextField durationN= new TextField(String.valueOf(card.getDuration()));
        durationN.setMaxSize(50,20);
        Label durationLabel2 = new Label("days");
        HBox durationBox = new HBox(12);
        durationBox.getChildren().addAll(durationLabel,durationN,durationLabel2);


        Label priceLabel = new Label("Total Price:");
        TextField priceField = new TextField(String.valueOf(card.getPrice()));
        HBox priceBox = new HBox(12);
        priceBox.getChildren().addAll(priceLabel,priceField);
        ComboBox<String> cur = new ComboBox<>();
        cur.getItems().addAll("EUR","PLN","USD");

        HBox buttonsBox = new HBox(12); // Espaciado entre los botones
        buttonsBox.setStyle("-fx-alignment: center;"); // Alineación centrada de los botones

        //Button saveButton = new Button("Save");
        card.getButtonSave().setOnAction(e -> {
            card.setName(nameField.getText());
            card.setDescription(descriptionArea.getText());
            card.setDuration(Long.parseLong(durationN.getText()));
            card.setPrice(Float.parseFloat(priceField.getText()));
            card.setExpirationDate(card.getCreationDate().plusDays(card.getDuration()));
            card.setCurrency(cur.getSelectionModel().getSelectedItem());
            stage.close();
        });

        // Crear el botón de borrado
        card.getButtonDelete().setStyle("-fx-font-size: 10px;");
        card.getButtonDelete().setOnAction(e -> {
            // Cerrar la ventana emergente
            stage.close();

            // Eliminar la tarjeta de la CardList
            cardList.getCards().remove(card);
            cards.getChildren().remove(card.getButton()); // Eliminar el botón de la tarjeta
            cardList.setCardNumber(cardList.getCardNumber() - 1); // Actualizar el número de tarjetas
            //System.out.println("Tarjeta eliminada, número actual de tarjetas: " + cardList.getCardNumber());
        });

        // Añadir ambos botones al HBox
        buttonsBox.getChildren().addAll(card.getButtonSave(), card.getButtonDelete());


        // Añadir los elementos al VBox
        detailsBox.getChildren().addAll(nameField, descriptionArea, creationDateLabel,startDatePicker,endDateLabel, durationBox,priceBox,cur, buttonsBox);

        // Mostrar la ventana emergente
        stage.setScene(scene);
        stage.show();
    }
    private void addCardList(String name) {

        CardList newCardList = new CardList(name);

        newCardList.getVcard().setStyle("-fx-border-color: black; -fx-padding: 5px; -fx-pref-width: 150px; -fx-pref-height: 200px;");

        TextField titleField = new TextField();
        titleField.setText(newCardList.getName());
        titleField.setStyle("-fx-font-size: 10px; -fx-pref-width: 100px;");

        // Añadir un ChangeListener al TextField para actualizar el nombre de la CardList en tiempo real
        titleField.textProperty().addListener((observable, oldValue, newValue) -> {
            newCardList.setName(newValue);  // Cambiar el nombre de la CardList mientras el usuario escribe
            //System.out.println("Nuevo nombre: " + newCardList.getName());
        });

        // Crear los botones dentro de un HBox (horizontal)
        HBox buttonsBox = new HBox(12); // Espaciado entre los botones
        buttonsBox.setStyle("-fx-alignment: center;"); // Alineación centrada de los botones

        newCardList.getAddCardButton().setStyle("-fx-font-size: 10px;");
        newCardList.getAddCardButton().setOnAction(e -> addCard(newCardList.getVcard(), newCardList));

        // Creamos el botón de borrado
        newCardList.getDeleteButton().setStyle("-fx-font-size: 10px;");
        newCardList.getDeleteButton().setOnAction(e -> {
            if (newCardList.getCardNumber() > 0) return;
            cardsContainer.getChildren().remove(newCardList.getVcard());
        });

        // Añadir ambos botones al HBox
        buttonsBox.getChildren().addAll(newCardList.getAddCardButton(), newCardList.getDeleteButton());

        newCardList.getVcard().getChildren().addAll(titleField, buttonsBox);
        //Add the already existing tasks
        for(Task t : project.getTasks()){
            if(t.getGroup().equals(name)){
                addCard(newCardList.getVcard(),newCardList,t);
            }
        }
        cardsContainer.getChildren().add(newCardList.getVcard());
        cardLists.add(newCardList);
    }

    // Método para añadir una tarjeta dentro de la carta
    private void addCard(VBox card, CardList cardList, Task t) {
        // Crear una nueva tarjeta (objeto Card)
        Card newCard = new Card(t.getName());
        newCard.setPrice(t.getPrice());
        newCard.setCreationDate(t.getStartDate());
        newCard.setDescription(t.getDescription());
        newCard.setDuration(t.getDuration());
        newCard.setExpirationDate(t.getEndDate());

        cardList.setCardNumber( cardList.getCardNumber()+1 );

        // Crear un botón para la tarjeta
        Button taskButton = newCard.getButton();
        //taskButton.setStyle("-fx-font-size: 9px;");
        taskButton.setStyle("-fx-pref-width: 200px; -fx-pref-height: 20px; -fx-font-size: 10px;");

        // Acción al hacer doble clic sobre la tarjeta
        taskButton.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                openCardDetails(newCard, card, cardList);
            }
        });

        // Añadir la tarjeta al contenedor de la carta
        card.getChildren().add(taskButton);
        cardList.getCards().add(newCard);
    }

    @FXML
    public void onSaveChangesButtonClicked(){
        ArrayList<Task> temp = new ArrayList<Task>();
        ArrayList<String> stemp = new ArrayList<String>();
        for(CardList cl : cardLists){
            String category = cl.getName();
            stemp.add(cl.getName());
            for(Card c : cl.getCards()){
                Task t = new Task(Status.Todo,c.getName());
                t.setGroup(category);
                t.setDescription(c.getDescription());
                t.setDuration(c.getDuration());
                t.setStartDate(c.getCreationDate());
                t.setEndDate(c.getExpirationDate());
                t.setPrice(c.getPrice());
                t.setCurrency(c.getCurrency());
                temp.add(t);
            }
        }
        project.updateCategory(stemp);
        project.updateTasks(temp);
    }





}