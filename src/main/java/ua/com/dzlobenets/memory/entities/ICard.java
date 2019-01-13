package ua.com.dzlobenets.memory.entities;

public interface ICard {
    State currentState();

    ICard changeState(State state);
}
