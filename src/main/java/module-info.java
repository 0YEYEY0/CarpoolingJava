module com.example.carpoolingjava {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.carpoolingjava to javafx.fxml;
    exports com.example.carpoolingjava;
}