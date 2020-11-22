package com.emojiCrusher;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
import java.util.List;

public class EmojiSelect extends ViewInterface {
    private JButton[][] buttons;
    private JPanel SelectionMenu;
    private int numSelected;
    private final int numRows;
    private final int numColumns;

    public List<String> getEmojiPaths() {
        return emojiPaths;
    }

    private List <String> emojiPaths;

    public EmojiSelect() {
        super("emojiSelect");
        numRows = 4;
        numColumns = 5;
        $$$setupUI$$$();
        frame.setContentPane(mainPanel);
        postSetup();
    }

    public void loadPaths(List<ImageIcon> icons) {
        emojiPaths.clear();

        for(ImageIcon icon: icons)
            emojiPaths.add(icon.toString());

        for (int i = 0; i < numRows; i++)
            for (int j = 0; j < numColumns; j++)
                if (emojiPaths.contains(buttons[i][j].getIcon().toString()))
                    buttons[i][j].setBackground(Color.GREEN);
                else
                    buttons[i][j].setBackground(Color.WHITE);
    }

    private void createUIComponents() {
        numSelected = 0;
        emojiPaths = new ArrayList<>();
        SelectionMenu = new JPanel();
        buttons = new JButton[numRows][numColumns];
        SelectionMenu.setLayout(new GridLayout(numRows, numColumns));

        String parent = "./emoji/png/labeled/Pool/";
        List<File> dirs = new ArrayList<>();

        List<ImageIcon> icons = new ArrayList<>();

        dirs.add(new File(parent));
        //List of all files and directories
        for (File d : dirs) {
            for (File icon : Objects.requireNonNull(d.listFiles())) {
                icons.add(new ImageIcon(icon.getAbsolutePath()));
            }
        }

        int currentIcon = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++, currentIcon++) {
                JButton current = new JButton(icons.get(currentIcon));
                current.setOpaque(true);
                current.setFocusPainted(false);
                current.setBackground(Color.WHITE);
                current.addActionListener(actionEvent -> {
                    JButton C = (JButton) actionEvent.getSource();
                    //handles toggling the background green highlight on/off when selected
                    if(current.getBackground() == Color.white && numSelected < 5) {
                        //FIXME: replace 5 with a variable
                        C.setBackground(Color.green);
                        emojiPaths.add(C.getIcon().toString());
                        System.out.println(emojiPaths);
                        numSelected++;
                    }
                    else if (current.getBackground() == Color.green) {
                        C.setBackground(Color.white);
                        emojiPaths.remove(C.getIcon().toString());
                        System.out.println(emojiPaths);
                        numSelected--;
                    }
                });

                buttons[i][j] = current;

                SelectionMenu.add(current);
            }
        }

        SelectionMenu.setVisible(true);
    }

    public JButton[][] getButtons() {
        return buttons;
    }

    public void setButtons(JButton[][] buttons) {
        this.buttons = buttons;
    }

    public JPanel getSelectionMenu() {
        return SelectionMenu;
    }

    public void setSelectionMenu(JPanel selectionMenu) {
        SelectionMenu = selectionMenu;
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
        mainPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        quitButton = new JButton();
        quitButton.setText("Quit");
        mainPanel.add(quitButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mainPanel.add(SelectionMenu, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
