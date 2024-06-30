package dataManagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DataApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Data Management Tool");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/datamanagement/view/DataView.fxml"));
        AnchorPane root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
