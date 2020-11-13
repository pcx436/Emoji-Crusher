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

    private void createDB() {
        try {
            Statement connection1 = null;
            Statement connection2 = null;
            database = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Opened Database successfully");

            connection1 = database.createStatement();
            String command = "CREATE TABLE `scoreboard` (" +
                    "    `id`    INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "    `name`    TEXT NOT NULL," +
                    "    `score`    INTEGER NOT NULL" +
                    ");";
            connection1.executeUpdate(command);

            connection2 = database.createStatement();
            command = "CREATE TABLE `EmojiPool` (\n" +
                    "    `id`    INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    `path`    TEXT NOT NULL UNIQUE\n" +
                    ");";
            connection2.executeUpdate(command);

            System.out.println("Created both tables.");

        } catch (Exception e) {
            System.out.println("ERROR: Database Connection" + e.getMessage());
        }
    }

}
