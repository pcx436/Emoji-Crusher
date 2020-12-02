package com.emojiCrusher;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.sql.*;
import java.util.*;
import java.util.List;

import static com.emojiCrusher.MatchDirection.*;

public class GameInterface extends ViewInterface {
    private JButton[][] buttons;
    private JTextPane scoreValue;
    private int totalPoints;
    private JPanel emojiPanel;
    private JPanel subPanel;
    private JProgressBar timeBar;
    private JLabel ScoreLabel;
    private final int numRows;
    private final int numColumns;
    private int[] firstCoords;
    private final List<ImageIcon> icons;
    private List<String> saved_EmojiPaths = new ArrayList<>();
    private Connection database;

    public GameInterface() {
        super("gameInterface");
        icons = new ArrayList<>();

        numRows = 6;
        numColumns = 6;
        totalPoints = 0;
        firstCoords = new int[]{-1,-1};
        $$$setupUI$$$();
        frame.setContentPane(mainPanel);
        postSetup();
    }
    // https://stackoverflow.com/a/40087987
    private <E> E getRandom(Collection<E> e) {
        return e.stream()
                .skip((int) (e.size() * Math.random()))
                .findFirst().get();
    }

    private void loadEmojis(){
        icons.clear();
        Statement connection = null;
        try {
            database = DriverManager.getConnection("jdbc:sqlite:test.db");
            connection = database.createStatement();
            String command = "Select * FROM EmojiPool;";
            ResultSet saved_Emoji = connection.executeQuery(command);
            while(saved_Emoji.next()){
                icons.add(new ImageIcon(saved_Emoji.getString("path")));
                System.out.println(new String(saved_Emoji.getString("path")));
            }
            System.out.println("LOADED SAVED EMOJIES FROM DATABASE");

        } catch (SQLException e) {
            System.out.println("ERROR: Couldn't load saved emojies" + e.getMessage());
            System.exit(0);
        }
    }

    private void createUIComponents() {
        emojiPanel = new JPanel();
        buttons = new JButton[numRows][numColumns];
        emojiPanel.setLayout(new GridLayout(numRows, numColumns));

        String parent = "./emoji/png/labeled/Pool/";

        // List of all files in pool
        // FIXME: Don't use this break system for limiting, migrate to using database later.
        int count = 0;
        loadEmojis();

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                buttons[i][j] = createButton();
                emojiPanel.add(buttons[i][j]);
            }
        }

        emojiPanel.setVisible(true);

        scoreValue = new JTextPane();
        StyledDocument doc = scoreValue.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        scoreValue.setBackground(new Color(0, 0, 0, 0));
    }

    public JProgressBar getTimeBar() {
        return timeBar;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    protected void $$$setupUI$$$() {
        createUIComponents();
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        subPanel = new JPanel();
        subPanel.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(subPanel, new GridConstraints(0, 1, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ScoreLabel = new JLabel();
        Font ScoreLabelFont = this.$$$getFont$$$(null, -1, 24, ScoreLabel.getFont());
        if (ScoreLabelFont != null) ScoreLabel.setFont(ScoreLabelFont);
        ScoreLabel.setText("Score");
        subPanel.add(ScoreLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        scoreValue.setEditable(false);
        Font scoreValueFont = this.$$$getFont$$$(null, -1, 20, scoreValue.getFont());
        if (scoreValueFont != null) scoreValue.setFont(scoreValueFont);
        scoreValue.setText("0");
        subPanel.add(scoreValue, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 50), null, 0, false));
        final Spacer spacer1 = new Spacer();
        subPanel.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        subPanel.add(spacer2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        quitButton = new JButton();
        quitButton.setText("Quit");
        mainPanel.add(quitButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mainPanel.add(emojiPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(572, 24), null, 0, false));
        timeBar = new JProgressBar();
        timeBar.setBackground(new Color(-4980731));
        timeBar.setIndeterminate(false);
        timeBar.setMinimum(0);
        timeBar.setString("0%");
        timeBar.setStringPainted(false);
        timeBar.setValue(100);
        mainPanel.add(timeBar, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ScoreLabel.setLabelFor(scoreValue);
    }

    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

    /**
     * find that button!!
     * @param btn
     * @return  coordinates of button or [-1, -1]
     */
    private int[] findButton(JButton btn) {
        for(int i = 0; i < numRows; i++)
            for(int j = 0; j < numColumns; j++)
                if (btn.equals(buttons[i][j]))
                    return new int[]{i, j};
        return new int[]{-1, -1};
    }

    private void firstPick(int []coords) {
        JButton btn = getButton(coords);
        if (btn.getBackground() == Color.WHITE) {
            firstCoords = coords;
            btn.setBackground(Color.GREEN);
        }
        else {
            firstCoords = new int[]{-1, -1};
            btn.setBackground(Color.WHITE);
        }
    }

    private void secondPick(int[] coords) {
        if (buttonsAdjacent(firstCoords, coords) &&
                (createsMatch(getButton(coords).getIcon(), firstCoords, false) ||
                        createsMatch(getButton(firstCoords).getIcon(), coords, false))) {
            System.out.println("Match occured!");
            getButton(firstCoords).setBackground(Color.WHITE);
            swapIcons(firstCoords, coords);
            int points = 0;

            if (checkMatch(coords, false))
                points += doMatch(coords);
            if (checkMatch(firstCoords, false))
                points += doMatch(firstCoords);
            firstCoords = new int[]{-1, -1};
            totalPoints += points;

            int currentVal = timeBar.getValue();
            if (currentVal + points > 100)
                currentVal = 100;
            else
                currentVal += points;

            int finalCurrentVal = currentVal;
            SwingUtilities.invokeLater(() -> {
                timeBar.setValue(finalCurrentVal);
                // FIXME: score getting overlayed on top of other scores...
                scoreValue.setText(String.valueOf(totalPoints));
            });
            System.out.println("Scored " + points + " points!");
        }
    }

    private boolean checkMatch(int[] coords, boolean ignorePositiveX) {
        int up = countMatches(coords, UP, Optional.empty());
        int down = countMatches(coords, DOWN, Optional.empty());
        int left = countMatches(coords, LEFT, Optional.empty());
        int right = countMatches(coords, RIGHT, Optional.empty());

        if (ignorePositiveX)
            return up >= 2 || left >= 2;
        else
            return up + down >= 2 || left + right >= 2;
    }

    private int doMatch(int[] coords) {
        int points = 0;
        int up = countMatches(coords, UP, Optional.empty());
        int down = countMatches(coords, DOWN, Optional.empty());
        int left = countMatches(coords, LEFT, Optional.empty());
        int right = countMatches(coords, RIGHT, Optional.empty());
        int horizontalMatch = left + right;
        int verticalMatch = up + down;

        if ((verticalMatch >= 2) && (horizontalMatch >= 2)){
            System.out.println("Vertical (" + verticalMatch + ") Horizontal (" + horizontalMatch + ")");
            markVertical(up, down, coords);
            markHorizontal(left, right, coords);
            clearVertical(up, down, coords);
            clearHorizontal(left, right, coords);
            points = verticalMatch + horizontalMatch;
        } else if (verticalMatch >= 2) {
            System.out.println("Vertical (" + verticalMatch + ")");
            markVertical(up, down, coords);
            clearVertical(up, down, coords);
            points = verticalMatch;
        } else if (horizontalMatch >= 2) {
            System.out.println("Horizontal (" + horizontalMatch + ")");
            markHorizontal(left, right, coords);
            clearHorizontal(left, right, coords);
            points = horizontalMatch;
        } else {
            System.out.println("No match");
            getButton(coords).setBackground(Color.WHITE);
        }

        return points;
    }

    private void markVertical(int up, int down, int[] coords){
        for (int row = coords[0] - up; row <= coords[0] + down; row++) {
            buttons[row][coords[1]].setBackground(Color.GREEN);
        }
        emojiPanel.validate();
    }

    private void markHorizontal(int left, int right, int[] coords) {
        for (int col = coords[1] - left; col <= coords[1] + right; col++) {
            buttons[coords[0]][col].setBackground(Color.GREEN);
        }
        emojiPanel.validate();
    }

    private void clearVertical(int up, int down, int[] coords) {
        for (int row = coords[0] - up; row <= coords[0] + down; row++) {
            buttons[row][coords[1]].setBackground(Color.WHITE);
        }
        emojiPanel.validate();

        shiftDown(up, down, coords);
    }

    private void clearHorizontal(int left, int right, int[] coords) {
        for (int col = coords[1] - left; col <= coords[1] + right; col++) {
            buttons[coords[0]][col].setBackground(Color.WHITE);
            shiftDown(0, 0, new int[]{coords[0], col});
        }
        emojiPanel.validate();
    }

    public void actionPerformed(ActionEvent actionEvent) {
        JButton btn = (JButton)actionEvent.getSource();
        int[] coords = findButton(btn);

        if (firstCoords[0] == -1 || (firstCoords[0] == coords[0] &&
                firstCoords[1] == coords[1])) {
            firstPick(coords);
        }
        else {
            secondPick(coords);
        }
    }

    public void clearBoard() {
        loadEmojis();
            for (int col = 0; col < numColumns; col++) {
                int finalRow = row;
                int finalCol = col;
                SwingUtilities.invokeLater(() -> {
                    JButton current = buttons[finalRow][finalCol];
                    Icon i;
                    do {
                        i = getRandom(icons);
                    } while (countMatches(new int[]{finalRow, finalCol}, UP, Optional.of(i)) >= 2 ||
                            countMatches(new int[]{finalRow, finalCol}, LEFT, Optional.of(i)) >= 2);
                    current.setIcon(i);
                });
            }
        }
    }

    private boolean createsMatch(Icon icon, int[] coords, boolean ignorePositiveX) {
        Icon backup = buttons[coords[0]][coords[1]].getIcon();
        boolean result;

        buttons[coords[0]][coords[1]].setIcon(icon);

        result = checkMatch(coords, ignorePositiveX);
        buttons[coords[0]][coords[1]].setIcon(backup);
        return result;
    }

    private void shiftDown(int up, int down, int[] coords) {
        int column = coords[1];
        int totalRemove = up + down + 1;  // 1 is for newly switched button

        for (int row = coords[0] + down; row >= 0; row--) {
            int[] aboveCoords = new int[]{row - totalRemove, column};
            JButton current = buttons[row][column];
            Icon above;

            if (outOfBounds(aboveCoords)) {
                above = getRandom(icons);
            } else {
                above = getButton(aboveCoords).getIcon();
            }

            // TODO: possibly move into invokeLater
            current.setIcon(above);
            emojiPanel.validate();
        }
    }

    private boolean buttonsAdjacent(int[] cd1, int[] cd2) {
        boolean rowCheck = cd1[0] == cd2[0] && (
                cd2[1] - 1 == cd1[1] || cd2[1] + 1 == cd1[1]
                );
        boolean colCheck = cd1[1] == cd2[1] && (
                cd1[0] == cd2[0] - 1 || cd1[0] == cd2[0] + 1
                );
        return rowCheck || colCheck;
    }

    private JButton getButton(int[] cds) { return buttons[cds[0]][cds[1]]; }

    private void swapIcons(int[] cd1, int[] cd2) {
        System.out.println("Is event dispatch thread: " + SwingUtilities.isEventDispatchThread());
        JButton b1 = getButton(cd1);
        JButton b2 = getButton(cd2);

        Icon i1 = b1.getIcon();

        b1.setIcon(b2.getIcon());
        b2.setIcon(i1);

        emojiPanel.validate();
    }

    private int countMatches(int[] cds, MatchDirection dir, Optional<Icon> icon) {
        int matchCount = 0;
        Icon currentIcon = icon.orElse(getButton(cds).getIcon());

        switch (dir) {
            case UP:
                for(int r = cds[0] - 1; r >= 0; r--)
                    if (buttons[r][cds[1]].getIcon().equals(currentIcon))
                        matchCount++;
                    else
                        break;
                break;

            case DOWN:
                for(int r = cds[0] + 1; r < numRows; r++)
                    if (buttons[r][cds[1]].getIcon().equals(currentIcon))
                        matchCount++;
                    else
                        break;
                break;

            case LEFT:
                for(int c = cds[1] - 1; c >= 0; c--)
                    if (buttons[cds[0]][c].getIcon().equals(currentIcon))
                        matchCount++;
                    else
                        break;
                break;

            case RIGHT:
                for(int c = cds[1] + 1; c < numColumns; c++)
                    if (buttons[cds[0]][c].getIcon().equals(currentIcon))
                        matchCount++;
                    else
                        break;
                break;
        }
        return matchCount;
    }

    private JButton createButton() {
        JButton current = new JButton(getRandom(icons));
        current.addActionListener(this::actionPerformed);
        current.setFocusPainted(false);
        current.setBackground(Color.WHITE);
        return current;
    }

    private boolean outOfBounds(int[] cds) {
        boolean retVal = cds[0] < 0 || cds[0] >= numRows || cds[1] < 0 || cds[1] >= numColumns;
        if (retVal)
            System.err.println("Coordinates [" + cds[0] + ", " + cds[1] + "] out of bounds!");

        return retVal;
    }
}
