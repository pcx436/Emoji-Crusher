package com.emojiCrusher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.plaf.nimbus.State;

public class Model {

    // attributes
    private int maxEmojis;
    private int maxScoreDisplay;
    private List<String> emojis;
    private Connection database;
    private List<List<String>> scoreTable;
    private final String path;

    // constructor
    public Model(int maxEmojis, int maxScores, String defaultPath) {
        this.maxEmojis = maxEmojis;
        this.maxScoreDisplay = maxScores;
        this.path = defaultPath;
        scoreTable = new ArrayList<List<String>>();
        createDB();
        loadScoreDB();
    }

    // getter and setters
    public List<List<String>> getScoreTable() {
        return scoreTable;
    }

    public List<ImageIcon> getEmojis() {
        Statement connection = null;
        List<ImageIcon> icons = new ArrayList<>();

        try {
            connection = database.createStatement();
            String command = "SELECT path FROM EmojiPool;";
            ResultSet resultSet = connection.executeQuery(command);
            while (resultSet.next()) {
                icons.add(new ImageIcon(resultSet.getString("path")));
            }
        } catch (SQLException e) {
            System.out.println("ERROR: Couldn't retrieve Paths" + e.getMessage());
        }
        return icons;
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
            command = "INSERT INTO EmojiPool (path) VALUES " + s;
            connection2.executeUpdate(command);
            System.out.println("Added in new Paths");

        } catch (SQLException e) {
            System.out.println("ERROR: Couldn't add new Paths" + e.getMessage());
            System.exit(0);
        }

    }

    // database interaction
    private void createDB() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("Couldn't find java driver for JDBC");
            System.exit(1);
        }

        List<String> startingEmoji = new ArrayList<>();
        startingEmoji.add(path + "/alien.png");
        startingEmoji.add(path + "/ghost.png");
        startingEmoji.add(path + "/top-hat.png");
        startingEmoji.add(path + "/pile-of-poo.png");
        startingEmoji.add(path + "/zombie.png");

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

            Statement connection3 = database.createStatement();
            String s = "(\"" + String.join("\"),(\"", startingEmoji) + "\");";
            command = "INSERT INTO `EmojiPool` (path) VALUES " + s;
            connection3.executeUpdate(command);

        } catch (SQLException e) {
            System.out.println("Error: Database Connection" + e.getMessage());
        }
    }

    public List<Icon> loadEmojisDB(){
        List<Icon> icons = new ArrayList<>();
        Statement connection;
        try {
            database = DriverManager.getConnection("jdbc:sqlite:test.db");
            connection = database.createStatement();
            String command = "Select * FROM EmojiPool;";
            ResultSet saved_Emoji = connection.executeQuery(command);
            while(saved_Emoji.next()){
                icons.add(new ImageIcon(saved_Emoji.getString("path")));
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Couldn't load saved emoji" + e.getMessage());
            System.exit(0);
        }
        return icons;
    }

    public void loadScoreDB() {
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

    public void saveScoreDB(int totalScore, String name) {
        String command = "INSERT INTO scoreBoard(name, score) VALUES (\"" + name + "\", " + String.valueOf(totalScore) + ");";
        Statement connection2 = null;
        try {
            connection2 = database.createStatement();
            connection2.executeUpdate(command);
            System.out.println("INSERTED SCORE and NAME into database");

        } catch (SQLException e) {
            System.out.println("ERROR: Score machine broke" + e.getMessage());
            System.exit(0);
        }
        loadScoreDB();

    }

}
