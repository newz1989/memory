package ua.com.dzlobenets.memory;

import ua.com.dzlobenets.memory.components.MainView;
import ua.com.dzlobenets.memory.controller.MainController;

public class Main {

    public static void main(String[] args) {

        new MainController(
                new MainView()
        ).startApp();
        
    }
}




