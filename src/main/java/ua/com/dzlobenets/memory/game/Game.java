package ua.com.dzlobenets.memory.game;

import ua.com.dzlobenets.memory.entities.Card;

import java.util.Arrays;
import java.util.List;

public class Game {

    private final List<Card<String>> cards = Arrays.asList(new Card<>("Test1"), new Card<>("Test1"));
    private int numOfPlayers;

    public Game(int players) {
    }


    public boolean isFinished() {
        return false;
    }

    public List<Card<String>> getCards() {
        return cards;
    }
}
