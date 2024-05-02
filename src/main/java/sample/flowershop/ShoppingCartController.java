package sample.flowershop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class ShoppingCartController {
    @FXML
    private AnchorPane shoppingCartAnchorPane;
    @FXML
    private Button shoppingCart_deleteItem;
    @FXML
    private ImageView shoppingCart_img;

    @FXML
    private Label shoppingCart_name;

    @FXML
    private Label shoppingCart_price;

    @FXML
    private Label shoppingCart_total;

    @FXML
    private Button decrementBtn;

    @FXML
    private Button incrementBtn;
    @FXML
    private Label counterText;

    private Product product;
    private MainSceneContoller mainSceneContoller;


    public void setShoppingCart_Data(Product product) {
        Image image = new Image(new File(product.getUrlImage()).toURI().toString());

        shoppingCart_img.setImage(image);
        shoppingCart_name.setText(product.getName());
        String price = String.valueOf(product.getPrice());
        shoppingCart_total.setText(price.substring(0, price.indexOf(".")) + "$");
        this.product = product;
    }

    public void deleteFromShoppingCart(ActionEvent event) {
        ShoppingCart shoppingCart = mainSceneContoller.shoppingCart;
        shoppingCart.removeProduct(product.getId());
        System.out.println("Отработка метода deleteFromSHoppingCart(): ");
        System.out.println();
        System.out.println("Размер хэш мапы после удаления товара внутри Shopping cart: " + shoppingCart.getItems().size()
                            + ",           id продукта который удаляют: " + product.getId());
        mainSceneContoller.removeProductCart(shoppingCartAnchorPane, this.product);
    }

    public void setMainContoller(MainSceneContoller mainSceneContoller) {
        this.mainSceneContoller = mainSceneContoller;
    }
    private int countProduct = 1;
    public int getCountProduct() {return this.countProduct;};
    public void incrementProduct(ActionEvent event) {
        product.setItemCount(++countProduct);
//        System.out.println("Item count after increment: " + product.getItemCount());
        counterText.setText(String.valueOf(countProduct));
        String total = String.valueOf(product.getPrice() * product.getItemCount());
//        System.out.println("Total after increment: " + total);
        shoppingCart_total.setText(total.substring(0, total.indexOf(".")) + "$");
//        mainSceneContoller.setTotal();
        mainSceneContoller.updateTotalDisplay();
    }

    public void decrementProduct(ActionEvent event) {
        if (countProduct != 0) {
            product.setItemCount(--countProduct);
//            System.out.println("Item count after decrement: " + product.getItemCount());
            counterText.setText(String.valueOf(countProduct));
            String total = String.valueOf(product.getPrice() * product.getItemCount());
//            System.out.println(total);
            shoppingCart_total.setText(total.substring(0, total.indexOf(".")) + "$");
            mainSceneContoller.updateTotalDisplay();
        }
    }
}
