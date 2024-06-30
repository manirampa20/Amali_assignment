module com.example.regexproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.regexproject to javafx.fxml;
    exports com.example.regexproject;
}