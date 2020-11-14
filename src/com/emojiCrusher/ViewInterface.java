package com.emojiCrusher;

import javax.swing.*;

public abstract class ViewInterface {
    protected JPanel mainPanel;
    protected JButton quitButton;
    protected JFrame frame;

    protected void postSetup(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(false);
    }

    public ViewInterface(String title) {
        frame = new JFrame(title);
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JFrame getFrame() { return this.frame; }

    public JButton getQuitButton() {
        return quitButton;
    }

    public void setQuitGame(JButton quitGame) {
        this.quitButton = quitGame;
    }

    abstract void $$$setupUI$$$();

}
