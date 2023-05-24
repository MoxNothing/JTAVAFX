module com.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.javafx to javafx.fxml;
    exports com.example.javafx;
    exports controladores.control;
    opens controladores.control to javafx.fxml;
    exports utilidad;
    opens utilidad to javafx.fxml;
    exports modelo;
    opens modelo to javafx.fxml;
}