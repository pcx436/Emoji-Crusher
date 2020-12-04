package com.emojiCrusher;

import javax.swing.*;

public abstract class ViewInterface {

    // attributes
    protected JPanel mainPanel;
    protected JButton quitButton;
    protected JFrame frame;

    // constructors
    public ViewInterface(String title) {
        frame = new JFrame(title);
    }

    // getter and setter
    public JFrame getFrame() { return this.frame; }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JButton getQuitButton() {
        return quitButton;
    }

    public void setQuitGame(JButton quitGame) {
        this.quitButton = quitGame;
    }

    // magic
    protected void postSetup(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(false);
    }

    abstract void $$$setupUI$$$();

}
