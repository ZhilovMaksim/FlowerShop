package sample.flowershop;
import sample.flowershop.Product;



import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

public class CartController {
    @FXML
    private AnchorPane cart;

    @FXML
    private Label cart_addBtn;

    @FXML
    private ImageView cart_img;

    @FXML
    private Label cart_name;

    @FXML
    private Label cart_price;

    public void setCartData(Product product) {
        System.out.println(product.getUrlImage());
        Image image = new Image(CartController.class.getResourceAsStream(product.getUrlImage()));

        cart_img.setImage(image);
        cart_name.setText(product.getName());
        cart_price.setText(String.valueOf(product.getPrice()));
    }
}
