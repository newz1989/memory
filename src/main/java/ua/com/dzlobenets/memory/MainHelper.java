package ua.com.dzlobenets.memory;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class MainHelper {
    public static final String GAME_TITLE = "Memory Game";
    static final String firstDefaultValue = "test";
    static final String secondDefaultValue = "test2";

    public static final int DEFAULT_MAIN_FRAME_WIDTH = 500;
    public static final int DEFAULT_MAIN_FRAME_HEIGHT = 550;

    public static final int DEFAULT_MAIN_FRAME_X_LOCATION = 20;
    public static final int DEFAULT_MAIN_FRAME_Y_LOCATION = 50;
    private static final int DEFAULT_CARD_HEIGHT = 200;
    private static final int DEFAULT_CARD_WIDTH = 125;

    static void initMainFrame(JFrame frame) {
        frame.setTitle(GAME_TITLE);
        frame.setSize(DEFAULT_MAIN_FRAME_WIDTH, DEFAULT_MAIN_FRAME_HEIGHT);
        frame.setLocation(DEFAULT_MAIN_FRAME_X_LOCATION, DEFAULT_MAIN_FRAME_Y_LOCATION);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

     static void initCards(JFrame mainFrame) {
        final Dimension cardDimension = new Dimension(DEFAULT_CARD_WIDTH, DEFAULT_CARD_HEIGHT);
        final Border border = BorderFactory.createLineBorder(Color.BLACK);

        final JPanel card1 = new JPanel();
        final JPanel card2 = new JPanel();
        final JPanel card3 = new JPanel();
        final JPanel card4 = new JPanel();


         final JLabel comp1 = new JLabel(firstDefaultValue);
         final JLabel comp2 = new JLabel(firstDefaultValue);

         final JLabel comp3 = new JLabel(secondDefaultValue);
         final JLabel comp4 = new JLabel(secondDefaultValue);

         comp1.setVisible(false);
         comp2.setVisible(false);

         comp3.setVisible(false);
         comp4.setVisible(false);

         card1.add(comp1);
         card2.add(comp2);
         card3.add(comp3);
         card4.add(comp4);

         card1.setBorder(border);
         card2.setBorder(border);

         card3.setBorder(border);
         card4.setBorder(border);

         card1.setSize(cardDimension);
         card2.setSize(cardDimension);
         card3.setSize(cardDimension);
         card4.setSize(cardDimension);

        final MouseAdapter mouseAdapter = createMouseListener(mainFrame);

         card1.addMouseListener(mouseAdapter);
         card2.addMouseListener(mouseAdapter);

         card3.addMouseListener(mouseAdapter);
         card4.addMouseListener(mouseAdapter);

         mainFrame.add(card1);
         mainFrame.add(card2);

         mainFrame.add(card3);
         mainFrame.add(card4);
    }

    private static MouseAdapter createMouseListener(JFrame mainFrame) {

        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final JPanel component = (JPanel) e.getComponent();
                Stream.of(component.getComponents()).forEach(c -> c.setVisible(true));

                if (selectedPair(mainFrame) && isPairTheSameType(mainFrame)) {
                    removeFindedCards(mainFrame);
                }


                if (countOfOpenedCards(mainFrame) == 2) {
                    hideOpenedCards(mainFrame);
                    return;
                }

                if (isGameFinished(mainFrame)) {
                    showWinnerDialog(mainFrame);
                    return;
                }
            }
        };
    }

    private static void hideOpenedCards(JFrame mainFrame) {
        getOpenedComponents(mainFrame).forEach(MainHelper::hideLable);
    }

    private static void hideLable(JLabel label) {
        label.setVisible(false);
    }

    private static void removeFindedCards(JFrame mainFrame) {
        List<JLabel> openedComponents = getOpenedComponents(mainFrame);
        openedComponents.forEach(c -> mainFrame.remove(c.getParent()));
    }

    private static boolean isPairTheSameType(JFrame frame) {
        List<JLabel> collect = getOpenedComponents(frame);

        return Objects.equals(collect.get(0).getText(), collect.get(1).getText());
    }

    private static List<JLabel> getOpenedComponents(JFrame frame) {
        return getComponentStream(frame)
                .map(JLabel.class::cast)
                .filter(Component::isVisible)
                .collect(Collectors.toList());
    }

    private static void showWinnerDialog(JFrame frame) {
        final JDialog youWInDialog = new JDialog(frame, "YOu are win", true);
        youWInDialog.setBounds(100, 100, 200, 200);
        youWInDialog.setVisible(true);
        youWInDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        System.exit(1);
    }

    private static boolean isGameFinished(JFrame frame) {
        return getComponentStream(frame).count() == 0;
    }

    private static Stream<Component> getComponentStream(JFrame frame) {
        return Stream.of(frame.getContentPane().getComponents())
                .map(JPanel.class::cast)
                .flatMap(MainHelper::toStreamOfComponents);
    }

    private static Stream<Component> toStreamOfComponents(JPanel jPanel) {
        return Stream.of(jPanel.getComponents());
    }

    private static boolean selectedPair(JFrame mainFrame) {
        return countOfOpenedCards(mainFrame) == 2;
    }

    private static long countOfOpenedCards(JFrame mainFrame) {
        return getComponentStream(mainFrame).filter(Component::isVisible).count();
    }

    private MainHelper() {
    }
}
