package Data;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class CardList {

    private String name;
    private int cardNumber;
    private ArrayList<Card> cards;
    private Button addCardButton;  // El botón asociado a la tarjeta
    private Button deleteButton;
    private VBox vcard;

    public CardList(String nombre){
        this.name = nombre;
        this.cardNumber = 0;
        this.addCardButton = new Button("Add Task");  // El nombre de la tarjeta es el texto del botón
        this.deleteButton = new Button("Delete list");
        this.vcard = new VBox(10);
        this.cards=new ArrayList<Card>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setAddCardButton(Button addCardButton) {
        this.addCardButton = addCardButton;
    }

    public void setDeleteButton(Button deleteButton) {
        this.deleteButton = deleteButton;
    }

    public void setVcard(VBox vcard) {
        this.vcard = vcard;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public String getName() {
        return name;
    }

    public Button getAddCardButton() {
        return addCardButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public VBox getVcard() {
        return vcard;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
}
