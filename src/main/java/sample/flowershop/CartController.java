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
        String price = String.valueOf(product.getPrice());
        cart_price.setText(price.substring(0, price.indexOf(".")) + "$");
        cart_price.getStyleClass().add("price-label");
        this.cartProduct = product;
    }

    public void setCartDataForShop(Product product) throws URISyntaxException {
        System.out.println(product.getUrlImage());
        Image image = new Image(new File(product.getUrlImage()).toURI().toString());

//        cart.setPrefWidth(200);
//        cart.setMaxWidth(200);

        cart_img.setImage(image);
        cart_name.setText(product.getName());
        String price = String.valueOf(product.getPrice());
        cart_price.setText(price.substring(0, price.indexOf(".")) + "$");
        cart_price.getStyleClass().add("price-label");
        this.cartProduct = product;
    }

    public void setMainContoller(MainSceneContoller mainSceneContoller) {
        this.mainSceneContoller = mainSceneContoller;
    }

    private int shoppCartSize = 0;
    public void addToShoppingCart(ActionEvent event) {
        System.out.println("Отработка метода addToShoppingCart(): ");
        ShoppingCart shoppingCart = mainSceneContoller.shoppingCart;
        System.out.println("Размер до добавления товара: " + shoppingCart.getItems().size());
        int currentSize = shoppingCart.getItems().size();
        shoppingCart.addProduct(this.cartProduct);
        System.out.println("Айди продукта который хочу добавить: " + this.cartProduct.getId());
        int newSize = shoppingCart.getItems().size();
        System.out.println("Размер после добавления товара: " + shoppingCart.getItems().size());

        boolean isNewProductAdded = newSize > currentSize;
        if (isNewProductAdded) {
            mainSceneContoller.updateShoppingCartDisplay(this.cartProduct); // 1h 2h
            shoppCartSize++;
        }
        isNewProductAdded = false;
//        System.out.println("s");
    }
}
