package ua.com.dzlobenets.memory;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

final class MainHelper {
    static final String DEFAULT_VALUE = "test";

     static void initMainFrame(JFrame frame) {
        frame.setSize(500, 500);
        frame.setTitle("Memory Game");
        frame.setLocation(20, 50);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

     static void initCards(JFrame mainFrame) {
        Dimension cardDimension = new Dimension(200, 250);
        Border border = BorderFactory.createLineBorder(Color.BLACK);

        JPanel card1 = new JPanel();
        JPanel card2 = new JPanel();


         JLabel comp1 = new JLabel(DEFAULT_VALUE);
         JLabel comp2 = new JLabel(DEFAULT_VALUE);

         comp1.setVisible(false);
         comp2.setVisible(false);

         card1.add(comp1);
         card2.add(comp2);

        card1.setBorder(border);
        card2.setBorder(border);

        card1.setSize(cardDimension);
        card2.setSize(cardDimension);


        MouseAdapter mouseAdapter = getMouseAdapter();

        card1.addMouseListener(mouseAdapter);
        card2.addMouseListener(mouseAdapter);

        mainFrame.add(card1);
        mainFrame.add(card2);
    }

    private static MouseAdapter getMouseAdapter() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel component = (JPanel) e.getComponent();
                Component component1 = component.getComponent(0);
                component1.setVisible(true);

            }
        };
    }

    private MainHelper() {
    }
}
