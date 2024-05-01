package sample.flowershop;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class CartController {
    @FXML
    private AnchorPane cart;

    @FXML
    private Button cart_addBtn;

    @FXML
    private ImageView cart_img;

    @FXML
    private Label cart_name;

    @FXML
    private Label cart_price;
    private MainSceneContoller mainSceneContoller;
    private Product cartProduct;


    public void setCartData(Product product) throws URISyntaxException {
        System.out.println(product.getUrlImage());
        Image image = new Image(new File(product.getUrlImage()).toURI().toString());

        cart_img.setImage(image);
        cart_name.setText(product.getName());
        cart_price.setText(String.valueOf(product.getPrice()));
        cartProduct = product;
    }

    public void setCartDataForShop(Product product) throws URISyntaxException {
        System.out.println(product.getUrlImage());
        Image image = new Image(new File(product.getUrlImage()).toURI().toString());

//        cart.setPrefWidth(200);
//        cart.setMaxWidth(200);

        cart_img.setImage(image);
        cart_name.setText(product.getName());
        cart_price.setText(String.valueOf(product.getPrice()));
        cartProduct = product;
    }

    public void setMainContoller(MainSceneContoller mainSceneContoller) {
        this.mainSceneContoller = mainSceneContoller;
    }

    @FXML
    public void addToShoppingCart(ActionEvent event) {
        ShoppingCart shoppingCart = mainSceneContoller.shoppingCart;

        shoppingCart.addProduct(cartProduct);
        System.out.println(shoppingCart.getItems());
        mainSceneContoller.updateShoppingCartDisplay(cartProduct);
        System.out.println("s");
    }
}
