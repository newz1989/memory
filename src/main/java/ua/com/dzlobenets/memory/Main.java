package ua.com.dzlobenets.memory;

import javax.swing.*;
import java.awt.*;

public class Main {


    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::startApp);
    }

    private static void startApp() {

        final GridBagLayout gridBagLayout = new GridBagLayout();
        final JFrame frame = new JFrame();
        frame.setLayout(gridBagLayout);

        MainHelper.initCards(frame);
        MainHelper.initMainFrame(frame);

    }

}




