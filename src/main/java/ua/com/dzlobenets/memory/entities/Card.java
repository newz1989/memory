package ua.com.dzlobenets.memory.entities;

public class Card<T> implements ICard {

    private T value;
    private State currentState = State.HIDE;


    public Card(T value) {
        this.value = value;
    }

    public State currentState() {
        return currentState;
    }

    public ICard changeState(State state) {
        this.currentState = state;
        return this;
    }

    public T getValue() {
        return value;
    }
}
