package ua.com.dzlobenets.memory.components;

import ua.com.dzlobenets.memory.entities.Card;
import ua.com.dzlobenets.memory.entities.State;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CardView extends JPanel {

    private final Card<String> card;
    private final JLabel lable;

    public CardView(Card<String> card) {
        this.card = card;
        this.lable = new JLabel(card.getValue());
        this.lable.setVisible(false);
        add(lable);
        init();
    }


    private void changeState(Card<String> card) {
        switch (card.currentState()) {
            case SHOW:
                card.changeState(State.HIDE);
                lable.setVisible(false);
                ;
            case HIDE:
                card.changeState(State.SHOW);
                lable.setVisible(true);
        }
    }

    private void init() {
        this.setBorder(createBorder());

    }

    private Border createBorder() {
        return BorderFactory.createLineBorder(Color.BLACK);
    }

    public boolean isShowed() {
        return card.currentState() == State.SHOW;
    }

    public void changeState(State newState) {
        card.changeState(newState);
        lable.setVisible(State.SHOW == newState);
    }


    public String getValue() {
        return card.getValue();
    }
}
