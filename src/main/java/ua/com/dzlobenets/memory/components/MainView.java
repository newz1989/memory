package ua.com.dzlobenets.memory.components;

import javax.swing.*;

public class MainView extends JFrame {
    private static final String TITLE = "Memory Game";

    public MainView() {
        super(TITLE);
        setSize(500, 500);
        setResizable(false);
        setLocation(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
