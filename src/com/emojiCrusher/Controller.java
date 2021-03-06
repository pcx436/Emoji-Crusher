package com.emojiCrusher;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

public class Controller {

    // attribute
    private final EmojiSelect emojiSelect;
    private final GameInterface gameInterface;
    private final ScoreBoard scoreBoard;
    private final MainMenu mainMenu;
    private final GameOver gameOver;
    private Model model;
    private Timer time;
    private Timer timeRate;

    // constructor
    public Controller(String path) {
        model = new Model(5, 10, path);
        emojiSelect = new EmojiSelect(path);
        gameInterface = new GameInterface(model.loadEmojisDB());
        scoreBoard = new ScoreBoard();
        mainMenu = new MainMenu();
        gameOver = new GameOver();

        quitBehavior();

        initMain();

        mainMenu.getFrame().setVisible(true);
    }

    // set up listener for all quit buttons
    private void quitBehavior() {
        emojiSelect.getQuitButton().addActionListener(actionEvent -> {
            emojiSelect.getFrame().setVisible(false);
            List<String> path = emojiSelect.getEmojiPaths();
            model.setEmojis(path);
            mainMenu.getFrame().setVisible(true);
        });

        gameInterface.getQuitButton().addActionListener(actionEvent -> {
            gameInterface.getFrame().setVisible(false);
            mainMenu.getFrame().setVisible(true);
            time.stop();
            timeRate.stop();
        });

        scoreBoard.getQuitButton().addActionListener(actionEvent -> {
            scoreBoard.getFrame().setVisible(false);
            mainMenu.getFrame().setVisible(true);
        });
    }

    // set up listener for all buttons in main menu that is not quit/exit
    private void initMain() {
        startTime();
        mainMenu.getEmojiPickerButton().addActionListener(actionEvent -> {
            emojiSelect.getFrame().setVisible(true);
            emojiSelect.loadPaths(model.getEmojis());
            mainMenu.getFrame().setVisible(false);
        });
        mainMenu.getPlayGameButton().addActionListener(actionEvent -> {
            gameInterface.getTimeBar().setValue(100);
            time.setDelay(1000);
            gameInterface.setTotalPoints(0);
            gameInterface.clearBoard(model.loadEmojisDB());
            gameInterface.getFrame().setVisible(true);
            mainMenu.getFrame().setVisible(false);
            time.start();
            timeRate.start();
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

        gameOver.getNameField().addActionListener(actionEvent -> {
            System.out.println("ya hit enter: " + gameOver.getNameField().getText());
            System.out.println("Points: " + gameInterface.getTotalPoints());
            model.saveScoreDB(gameInterface.getTotalPoints(), gameOver.getNameField().getText());
            gameOver.getFrame().setVisible(false);
            mainMenu.getFrame().setVisible(true);
        });
    }

    // logic for timer behavior
    public void startTime(){
        ActionListener countDown = e -> {
            gameInterface.getTimeBar().setValue(gameInterface.getTimeBar().getValue()-1);
            if(gameInterface.getTimeBar().getValue()==0){
                gameInterface.getFrame().setVisible(false);
                gameOver.getFrame().setVisible(true);
                time.stop();
                timeRate.stop();
            }
        };
        time = new Timer(1000, countDown);

        ActionListener TimeRate = e -> time.setDelay(time.getDelay()/2);
        timeRate = new Timer(30000, TimeRate);
    }

    // starts the game
    public static void main(String[] args) {
        Controller ct;
        if (args.length == 1)
            ct = new Controller(args[0]);
        else
            System.err.println("Please specify the path to the emoji folder.");
    }
}
