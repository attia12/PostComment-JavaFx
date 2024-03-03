module com.example.rafafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;



    opens com.example.rafafx to javafx.fxml;
    exports com.example.rafafx;
}