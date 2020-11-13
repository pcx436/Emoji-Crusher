package com.emojiCrusher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Model {
    public List<List<String>> getScoreTable() {
        return scoreTable;
    }

    private int maxEmojis;
    private int maxScoreDisplay;
    private ImageIcon[] emojis;
    private Connection database;
    private List<List<String>> scoreTable;

    public Model(int maxEmojis, int maxScores) {
        this.maxEmojis = maxEmojis;
        this.maxScoreDisplay = maxScores;
        scoreTable = new ArrayList();
        createDB();
    }

}
