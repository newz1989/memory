package ua.com.dzlobenets.memory.controller;

import ua.com.dzlobenets.memory.components.CardView;
import ua.com.dzlobenets.memory.components.MainView;
import ua.com.dzlobenets.memory.entities.Card;
import ua.com.dzlobenets.memory.entities.State;
import ua.com.dzlobenets.memory.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public class MainController implements IController {

    private Game currentGame;
    private final MainView mainFrame;
    private final int players = 2;

    public MainController(MainView mainFrame) {
        this.mainFrame = mainFrame;
    }

    private void createGame() {
        currentGame = new Game(players);
    }

    public void startApp() {
        createGame();
        initArea();
        show();
    }

    private void initArea() {
        final List<JPanel> panelsByCards = createPanelsByCards(currentGame.getCards());
        applyListeners(panelsByCards);
        mainFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelsByCards.forEach(mainFrame::add);
    }

    private void applyListeners(List<JPanel> panelsByCards) {
        final MouseAdapter mouseListener = createMouseListener();
        panelsByCards.forEach(panel -> this.applyListener(panel, mouseListener));
    }

    private void applyListener(JPanel jPanel, MouseAdapter mouseListener) {
        jPanel.addMouseListener(mouseListener);
    }

    private List<JPanel> createPanelsByCards(List<Card<String>> cards) {
        return cards.stream()
                .map(CardView::new)
                .collect(Collectors.toList());
    }

    private void show() {
        mainFrame.setVisible(true);
    }

    private long countOfShowingCard() {
        return getAllCards()
                .filter(CardView::isShowed)
                .count();
    }

    private Stream<CardView> getAllCards() {
        Component[] components = mainFrame.getContentPane().getComponents();
        return Stream.of(components)
                .map(CardView.class::cast);
    }


    private MouseAdapter createMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CardView openingCardView = (CardView) e.getComponent();

                if (countOfShowingCard() < 2) {
                    openingCardView.changeState(State.SHOW);
                }


                if (countOfShowingCard() == 2) {
                    if (isGuesed()) {
                        JDialog jDialog = new JDialog(mainFrame);
                        jDialog.setTitle("You win");
                        jDialog.add(new JLabel("You are win"));
                        jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        jDialog.setVisible(true);


                    }
                }


            }
        };
    }

    private boolean isGuesed() {
        int size = getAllCards()
                .filter(CardView::isShowed)
                .map(CardView::getValue)
                .collect(toSet()).size();
        return size == 1;
    }
}
