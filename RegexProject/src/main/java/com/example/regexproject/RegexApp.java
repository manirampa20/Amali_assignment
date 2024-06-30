package com.example.regexproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexApp extends Application {

    private RegexModule regexModule = new RegexModule();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Text Processing Tool");

        // UI Components
        TextField regexField = new TextField();
        regexField.setPromptText("Enter regex pattern");

        TextArea inputTextArea = new TextArea();
        inputTextArea.setPromptText("Enter text here");

        TextField replacementField = new TextField();
        replacementField.setPromptText("Enter replacement text");

        TextArea resultTextArea = new TextArea();
        resultTextArea.setEditable(false);

        Button searchButton = new Button("Search");
        Button matchButton = new Button("Match");
        Button replaceButton = new Button("Replace");
        Button highlightButton = new Button("Highlight");

        Label searchResultLabel = new Label();

        // TextFlow for highlighted text
        TextFlow resultTextFlow = new TextFlow();

        // Layout
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Regex Pattern:"), 0, 0);
        grid.add(regexField, 1, 0);
        grid.add(new Label("Replacement Text:"), 0, 1);
        grid.add(replacementField, 1, 1);
        grid.add(new Label("Input Text:"), 0, 2);
        grid.add(inputTextArea, 1, 2, 2, 1);
        grid.add(searchButton, 0, 3);
        grid.add(matchButton, 1, 3);
        grid.add(replaceButton, 2, 3);
        grid.add(searchResultLabel, 1, 4, 2, 1);
        grid.add(new Label("Result Text:"), 0, 5);
        grid.add(resultTextArea, 1, 5, 2, 1);
        grid.add(highlightButton, 0, 6, 3, 1); // Spanning across three columns

        // Action handlers
        searchButton.setOnAction(e -> {
            String text = inputTextArea.getText();
            String regex = regexField.getText();
            boolean found = regexModule.search(text, regex);
            resultTextArea.setText("Search result: " + found);
        });

        matchButton.setOnAction(e -> {
            String text = inputTextArea.getText();
            String regex = regexField.getText();
            boolean matched = regexModule.match(text, regex);
            resultTextArea.setText("Match result: " + matched);
        });

        replaceButton.setOnAction(e -> {
            String text = inputTextArea.getText();
            String regex = regexField.getText();
            String replacement = replacementField.getText();
            String result = regexModule.replace(text, regex, replacement);
            resultTextArea.setText(result);
        });

        highlightButton.setOnAction(e -> {
            resultTextFlow.getChildren().clear();
            String text = inputTextArea.getText();
            String regex = regexField.getText();

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);

            int lastEnd = 0;
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();

                // Add preceding text
                if (start > lastEnd) {
                    resultTextFlow.getChildren().add(new Text(text.substring(lastEnd, start)));
                }

                // Add matched text with highlight
                Text highlightedText = new Text(text.substring(start, end));
                highlightedText.setStyle("-fx-fill: red; -fx-font-weight: bold;");
                resultTextFlow.getChildren().add(highlightedText);

                lastEnd = end;
            }

            // Add remaining text
            if (lastEnd < text.length()) {
                resultTextFlow.getChildren().add(new Text(text.substring(lastEnd)));
            }
        });

        // Add components to scene
        grid.add(resultTextFlow, 1, 6, 2, 1); // Adding TextFlow below the buttons

        Scene scene = new Scene(grid, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
