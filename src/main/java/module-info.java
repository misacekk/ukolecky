module com.example.ukolnicek {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ukolnicek to javafx.fxml;
    exports com.example.ukolnicek;
}