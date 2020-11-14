package com.emojiCrusher;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends ViewInterface {
    private JButton playGameButton;
    private JButton emojiPickerButton;
    private JButton scoreboardButton;
    private JTextPane emojiCrushTextPane;
    private JButton quitButton;
    private JPanel mainPanel;

    public JButton getPlayGameButton() {
        return playGameButton;
    }

    public void setPlayGameButton(JButton playGameButton) {
        this.playGameButton = playGameButton;
    }

    public JButton getEmojiPickerButton() {
        return emojiPickerButton;
    }

    public void setEmojiPickerButton(JButton emojiPickerButton) {
        this.emojiPickerButton = emojiPickerButton;
    }

    public JButton getScoreboardButton() {
        return scoreboardButton;
    }

    public void setScoreboardButton(JButton scoreboardButton) {
        this.scoreboardButton = scoreboardButton;
    }

    public JTextPane getEmojiCrushTextPane() {
        return emojiCrushTextPane;
    }

    public void setEmojiCrushTextPane(JTextPane emojiCrushTextPane) {
        this.emojiCrushTextPane = emojiCrushTextPane;
    }

    public JButton getQuitButton() {
        return quitButton;
    }

    public void setQuitButton(JButton quitButton) {
        this.quitButton = quitButton;
    }
    public MainMenu() {
        super("mainMenu");
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
        postSetup();
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
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(8, 1, new Insets(0, 0, 0, 0), -1, -1));
        playGameButton = new JButton();
        playGameButton.setText("Play Game");
        mainPanel.add(playGameButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        emojiPickerButton = new JButton();
        emojiPickerButton.setText("Emoji Picker");
        mainPanel.add(emojiPickerButton, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        scoreboardButton = new JButton();
        scoreboardButton.setText("Scoreboard");
        mainPanel.add(scoreboardButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        mainPanel.add(spacer1, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        mainPanel.add(spacer2, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        mainPanel.add(spacer3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        emojiCrushTextPane = new JTextPane();
        emojiCrushTextPane.setBackground(new Color(-1));
        emojiCrushTextPane.setEditable(false);
        emojiCrushTextPane.setEnabled(true);
        Font emojiCrushTextPaneFont = this.$$$getFont$$$(null, -1, 24, emojiCrushTextPane.getFont());
        if (emojiCrushTextPaneFont != null) emojiCrushTextPane.setFont(emojiCrushTextPaneFont);
        emojiCrushTextPane.setText("Emoji Crush!");
        mainPanel.add(emojiCrushTextPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        quitButton = new JButton();
        quitButton.setText("Exit");
        mainPanel.add(quitButton, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        return mainPanel;
    }
}
