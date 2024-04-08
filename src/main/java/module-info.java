module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.Deadlock;
    opens com.example.demo.Deadlock to javafx.fxml;
}