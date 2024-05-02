package sample.flowershop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
//        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        Image image = new Image(HelloApplication.class.getResourceAsStream("img/flower-pot-title.png"));
        stage.getIcons().add(image);
        stage.setTitle("Flower Shop Management System!");
        stage.setScene(scene);
//        stage.setMinHeight(450);
//        stage.setMinWidth(650);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}