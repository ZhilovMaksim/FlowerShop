module sample.flowershop {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens sample.flowershop to javafx.fxml;
    exports sample.flowershop;
}