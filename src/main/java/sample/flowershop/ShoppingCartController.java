package sample.flowershop;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class ShoppingCartController {
    @FXML
    private ImageView shoppingCart_deleteItem;

    @FXML
    private ImageView shoppingCart_img;

    @FXML
    private Label shoppingCart_name;

    @FXML
    private Label shoppingCart_price;

    @FXML
    private Label shoppingCart_total;


    public void setShoppingCart_Data(Product product) {
        Image image = new Image(new File(product.getUrlImage()).toURI().toString());

        shoppingCart_img.setImage(image);
        shoppingCart_name.setText(product.getName());
        shoppingCart_price.setText(String.valueOf(product.getPrice()));
    }
}
