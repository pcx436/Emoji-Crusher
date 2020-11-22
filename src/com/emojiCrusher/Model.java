package com.emojiCrusher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Model {

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
        loadDB();
    }

    public void setEmojis(List<String> emojis) {
        Statement connection1 = null;
        String command = "DELETE FROM EmojiPool Where 1=1";
        try {
            connection1 = database.createStatement();
            connection1.executeUpdate(command);
            System.out.println("clear old pool");

        } catch (SQLException e) {
            System.out.println("ERROR: Couldn't clear old pool: " + e.getMessage());
            System.exit(0);
        }

        Statement connection2 = null;
        try {
            connection2 = database.createStatement();
            String s = "(\"" + String.join("\"),(\"", emojis) + "\");";
            command = "INSERT INTO EmojiPool (path) " + s;
            connection2.executeUpdate(command);
            System.out.println("Added in new Paths");

        } catch (SQLException e) {
            System.out.println("ERROR: Couldn't add new Paths" + e.getMessage());
            System.exit(0);
        }

    }

    public List<List<String>> getScoreTable() {
        return scoreTable;
    }

    public List<String> getEmojis() {
        return emojis;
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

        } catch (SQLException e) {
            System.out.println("Error: Database Connection" + e.getMessage());
        }
    }

    public void loadDB() {
        Statement connection1 = null;
        String command = "SELECT name, score FROM scoreboard ORDER BY score DESC LIMIT "+
                String.valueOf(maxScoreDisplay) + ";";
        scoreTable.clear();
        try {
            connection1 = database.createStatement();
            ResultSet res = connection1.executeQuery(command);
            while (res.next()) {
                List<String> row = new ArrayList<>();
                row.add(res.getString("name"));
                row.add(Integer.toString(Integer.parseInt(res.getString("score"))));
                scoreTable.add(row);
            }
            System.out.println("Load from Database successfully");

        } catch (SQLException e) {
            System.out.println("ERROR: Can't Load Database: " + e.getMessage());
            System.exit(0);
        }

    }

}
