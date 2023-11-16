module com.example.carpoolingjava {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires com.google.gson;


    opens com.example.carpoolingjava;
    exports com.example.carpoolingjava;

}