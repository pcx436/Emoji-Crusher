package com.emojiCrusher;

public class Controller {
    private final EmojiSelect emojiSelect;
    private final GameInterface gameInterface;
    private final ScoreBoard scoreBoard;
    private final MainMenu mainMenu;

    public Controller() {
        emojiSelect = new EmojiSelect();
        gameInterface = new GameInterface();
        scoreBoard = new ScoreBoard();
        mainMenu = new MainMenu();

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
        });
        mainMenu.getQuitButton().addActionListener(actionEvent -> System.exit(0));
    }

}
