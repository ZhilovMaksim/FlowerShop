package sample.flowershop;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainSceneContoller {
    @FXML
    private HBox bestSelers_container;

    private List<Product> bestSelers;

    public void initialize() {
        bestSelers = new ArrayList<>(getBestSelers());

        for (Product product: bestSelers) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("cart.fxml"));
                Parent cartRoot = fxmlLoader.load();
                CartController cartController = fxmlLoader.getController();
                cartController.setCartData(product);

                bestSelers_container.getChildren().add(cartRoot);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Product> getBestSelers() {
        List<Product> bestSelers = new ArrayList<>();
        Product product = new Product(1, "Daisy", 5, "Huinya");
        product.setUrlImage("img/bestSeler-1.png");
        bestSelers.add(product);
        return bestSelers;
    }
}
