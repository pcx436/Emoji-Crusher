package com.emojiCrusher;

public class Controller {
    private final EmojiSelect emojiSelect;
    private final GameInterface gameInterface;
    private final MainMenu mainMenu;

    public Controller() {
        emojiSelect = new EmojiSelect();
        gameInterface = new GameInterface();
        mainMenu = new MainMenu();

        emojiSelect.getQuitButton().addActionListener(actionEvent -> {
            emojiSelect.getFrame().setVisible(false);
            mainMenu.getFrame().setVisible(true);
        });

        gameInterface.getQuitGame().addActionListener(actionEvent -> {
            gameInterface.getFrame().setVisible(false);
            mainMenu.getFrame().setVisible(true);
        });

        mainMenu.getEmojiPickerButton().addActionListener(actionEvent -> {
            emojiSelect.getFrame().setVisible(true);
            mainMenu.getFrame().setVisible(false);
        });
        mainMenu.getPlayGameButton().addActionListener(actionEvent -> {
            gameInterface.getFrame().setVisible(true);
            mainMenu.getFrame().setVisible(false);
        });
        mainMenu.getExitButton().addActionListener(actionEvent -> System.exit(0));

        mainMenu.getFrame().setVisible(true);
    }

}
