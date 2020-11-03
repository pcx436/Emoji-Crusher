package com.emojiCrusher;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;

public class gameInterface {
    private JButton[][] buttons;
    private JButton quitGame;
    private JTextPane scoreValue;
    private JPanel emojiPanel;
    private JPanel yeenus;
    private JPanel subPanel;
    private JProgressBar timeBar;
    private JLabel ScoreLabel;

    // https://stackoverflow.com/a/40087987
    private static <E> Optional<E> getRandom(Collection<E> e) {
        return e.stream()
                .skip((int) (e.size() * Math.random()))
                .findFirst();
    }

    private void createUIComponents() {
        int numRows = 4;
        emojiPanel = new JPanel();
        buttons = new JButton[numRows][numRows];
        emojiPanel.setLayout(new GridLayout(numRows, numRows));

        String parent = "./emoji/png/labeled/64/";
        List<File> dirs = new ArrayList<>();

        List<ImageIcon> icons = new ArrayList<>();

        dirs.add(new File(parent + "people"));

        //List of all files and directories
        for (File d : dirs) {
            for (File icon : Objects.requireNonNull(d.listFiles())) {
                icons.add(new ImageIcon(icon.getAbsolutePath()));
            }
        }

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numRows; j++) {
                Optional<ImageIcon> e = getRandom(icons);
                if (e.isPresent()) {
                    JButton current = new JButton(e.get());
                    current.setFocusPainted(false);
                    current.setBackground(Color.white);
                    buttons[i][j] = current;
                    emojiPanel.add(current);
                } else {
                    throw new RuntimeException("Uh oh! Someone did a bad! Time to kill the wizard!");
                }
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

    public void setData(boundForm data) {
    }

    public void getData(boundForm data) {
    }

    public boolean isModified(boundForm data) {
        return false;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("gameInterface");
        frame.setContentPane(new gameInterface().yeenus);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        yeenus = new JPanel();
        yeenus.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        subPanel = new JPanel();
        subPanel.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        yeenus.add(subPanel, new GridConstraints(0, 1, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ScoreLabel = new JLabel();
        Font ScoreLabelFont = this.$$$getFont$$$(null, -1, 24, ScoreLabel.getFont());
        if (ScoreLabelFont != null) ScoreLabel.setFont(ScoreLabelFont);
        ScoreLabel.setText("Score");
        subPanel.add(ScoreLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        scoreValue.setEditable(false);
        Font scoreValueFont = this.$$$getFont$$$(null, -1, 20, scoreValue.getFont());
        if (scoreValueFont != null) scoreValue.setFont(scoreValueFont);
        scoreValue.setText("4,600");
        subPanel.add(scoreValue, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final Spacer spacer1 = new Spacer();
        subPanel.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        subPanel.add(spacer2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        quitGame = new JButton();
        quitGame.setText("Quit");
        yeenus.add(quitGame, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        yeenus.add(emojiPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(572, 24), null, 0, false));
        timeBar = new JProgressBar();
        timeBar.setBackground(new Color(-4980731));
        timeBar.setIndeterminate(false);
        timeBar.setMinimum(0);
        timeBar.setString("0%");
        timeBar.setStringPainted(false);
        timeBar.setValue(30);
        yeenus.add(timeBar, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ScoreLabel.setLabelFor(scoreValue);
    }

    /**
     * @noinspection ALL
     */
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

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return yeenus;
    }

}