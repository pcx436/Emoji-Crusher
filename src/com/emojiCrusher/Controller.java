package com.emojiCrusher;

import javax.swing.*;
import java.util.List;

public class Controller {
    private final EmojiSelect emojiSelect;
    private final GameInterface gameInterface;
    private final ScoreBoard scoreBoard;
    private final MainMenu mainMenu;
    private Model model;

    public Controller() {
        emojiSelect = new EmojiSelect();
        gameInterface = new GameInterface();
        scoreBoard = new ScoreBoard();
        mainMenu = new MainMenu();
        model = new Model(5, 10);

        quitBehavior();

        initMain();

        mainMenu.getFrame().setVisible(true);
    }

    private void quitBehavior() {
        emojiSelect.getQuitButton().addActionListener(actionEvent -> {
            emojiSelect.getFrame().setVisible(false);
            mainMenu.getFrame().setVisible(true);
        });

        gameInterface.getQuitButton().addActionListener(actionEvent -> {
            gameInterface.getFrame().setVisible(false);
            mainMenu.getFrame().setVisible(true);
        });

        scoreBoard.getQuitButton().addActionListener(actionEvent -> {
            scoreBoard.getFrame().setVisible(false);
            mainMenu.getFrame().setVisible(true);
        });
    }

    private void initMain() {
        mainMenu.getEmojiPickerButton().addActionListener(actionEvent -> {
            emojiSelect.getFrame().setVisible(true);
            mainMenu.getFrame().setVisible(false);
        });
        mainMenu.getPlayGameButton().addActionListener(actionEvent -> {
            gameInterface.getFrame().setVisible(true);
            mainMenu.getFrame().setVisible(false);
        });
        mainMenu.getScoreboardButton().addActionListener(actionEvent -> {
            scoreBoard.getFrame().setVisible(true);
            mainMenu.getFrame().setVisible(false);
            List<List<String>> res = model.getScoreTable();

            // wipe list
            for (int i = scoreBoard.getTableModel().getRowCount() - 1; i >= 0; i--)
                scoreBoard.getTableModel().removeRow(i);

            // add the current items to the array
            for (List<String> l: res)
                scoreBoard.getTableModel().addRow(l.toArray());

        });
        mainMenu.getQuitButton().addActionListener(actionEvent -> System.exit(0));
    }

}
