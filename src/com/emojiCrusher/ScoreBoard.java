package com.emojiCrusher;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ScoreBoard extends ViewInterface {
    private JTable scoreTable;
    private JLabel ScoreBoardLabel;
    private DefaultTableModel tableModel;

    // FIXME: table cells are editable???
    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    private void createUIComponents() {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Score");
        scoreTable = new JTable(tableModel);
    }

    public JTable getScoreTable() {
        return scoreTable;
    }

    public void setScoreTable(JTable scoreTable) {
        this.scoreTable = scoreTable;
        this.mainPanel.revalidate();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.mainPanel.revalidate();
    }

    public JLabel getScoreBoardLabel() {
        return ScoreBoardLabel;
    }

    public void setScoreBoardLabel(JLabel scoreBoardLabel) {
        ScoreBoardLabel = scoreBoardLabel;
        this.mainPanel.revalidate();
    }

    public ScoreBoard() {
        super("scoreBoard");
        $$$setupUI$$$();
        frame.setContentPane(mainPanel);
        postSetup();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    protected void $$$setupUI$$$() {
        createUIComponents();
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        scoreTable.setAutoCreateRowSorter(false);
        scoreTable.setAutoResizeMode(4);
        scoreTable.setFillsViewportHeight(true);
        Font scoreTableFont = this.$$$getFont$$$(null, -1, 16, scoreTable.getFont());
        if (scoreTableFont != null) scoreTable.setFont(scoreTableFont);
        scoreTable.setShowHorizontalLines(false);
        scoreTable.setShowVerticalLines(false);
        mainPanel.add(scoreTable, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 150), new Dimension(150, 150), 0, false));
        final Spacer spacer1 = new Spacer();
        mainPanel.add(spacer1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(14, 458), null, 0, false));
        final Spacer spacer2 = new Spacer();
        mainPanel.add(spacer2, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(14, 458), null, 0, false));
        ScoreBoardLabel = new JLabel();
        Font ScoreBoardLabelFont = this.$$$getFont$$$(null, -1, 24, ScoreBoardLabel.getFont());
        if (ScoreBoardLabelFont != null) ScoreBoardLabel.setFont(ScoreBoardLabelFont);
        ScoreBoardLabel.setText("Scoreboard");
        mainPanel.add(ScoreBoardLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        quitButton = new JButton();
        quitButton.setText("Quit");
        mainPanel.add(quitButton, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
