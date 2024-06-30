package com.example.regexproject;

import dataManagement.DataModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloController {

    @FXML
    private TextField dataInputField;
    @FXML
    private ListView<String> dataListView;


    private DataModel dataModel;

    @FXML
    public void initialize() {
        dataModel = new DataModel();
        dataListView.setItems(dataModel.getDataList());

        // Add listener to update input field when a list item is selected
        dataListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                dataInputField.setText(newValue);
            }
        });
    }

    // Method to handle adding data to the collection
    @FXML
    public void handleAdd() {
        String data = dataInputField.getText();
        if (!data.isEmpty()) {
            dataModel.addData(data);
            dataInputField.clear();
        }
    }

    // Method to handle updating the selected data in the collection
    @FXML
    public void handleUpdate() {
        String selectedData = dataListView.getSelectionModel().getSelectedItem();
        String newData = dataInputField.getText();
        if (selectedData != null && !newData.isEmpty()) {
            int selectedIndex = dataListView.getSelectionModel().getSelectedIndex();
            dataModel.updateData(selectedIndex, newData);
            dataInputField.clear();
        }
    }

    // Method to handle deleting data from the collection
    @FXML
    public void handleDelete() {
        String selectedData = dataListView.getSelectionModel().getSelectedItem();
        if (selectedData != null) {
            dataModel.removeData(selectedData);
            dataInputField.clear();
        }
    }

    // Method to handle clearing the input field
    @FXML
    public void handleClear() {
        dataInputField.clear();
    }

    // Method to handle finding data in the collection
    @FXML
    public void handleFind() {
        // Implementation for finding data
        String dataToFind = dataInputField.getText();
        if (!dataToFind.isEmpty()) {
            int index = dataModel.findData(dataToFind);
            if (index >= 0) {
                dataListView.getSelectionModel().select(index);
            } else {
                dataInputField.setText("Not found");
            }
        }
    }

    @FXML
    private TextField regexField;
    @FXML
    private TextField replacementField;
    @FXML
    private TextArea inputTextArea;
    @FXML
    private TextArea resultTextArea;
    @FXML
    private TextFlow resultTextFlow;

    private RegexModule regexModule = new RegexModule();

    @FXML
    public void handleSearch() {
        String text = inputTextArea.getText();
        String regex = regexField.getText();
        boolean found = regexModule.search(text, regex);
        resultTextArea.setText("Search result: " + found);
    }

    @FXML
    public void handleMatch() {
        String text = inputTextArea.getText();
        String regex = regexField.getText();
        boolean matched = regexModule.match(text, regex);
        resultTextArea.setText("Match result: " + matched);
    }

    @FXML
    public void handleReplace() {
        String text = inputTextArea.getText();
        String regex = regexField.getText();
        String replacement = replacementField.getText();
        String result = regexModule.replace(text, regex, replacement);
        resultTextArea.setText(result);
    }

    @FXML
    public void handleHighlight() {
        resultTextFlow.getChildren().clear();
        String text = inputTextArea.getText();
        String regex = regexField.getText();

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        int lastEnd = 0;
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();


            if (start > lastEnd) {
                resultTextFlow.getChildren().add(new Text(text.substring(lastEnd, start)));
            }

            Text highlightedText = new Text(text.substring(start, end));
            highlightedText.setStyle("-fx-fill: red; -fx-font-weight: bold;");
            resultTextFlow.getChildren().add(highlightedText);

            lastEnd = end;
        }

        if (lastEnd < text.length()) {
            resultTextFlow.getChildren().add(new Text(text.substring(lastEnd)));
        }
    }
}
