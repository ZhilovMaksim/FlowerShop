package sample.flowershop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class MainSceneContoller {
    @FXML
    private HBox bestSelers_container;

    @FXML
    private GridPane cart_Container;

    @FXML
    private Label homeBtn;

    @FXML
    private ScrollPane homePage;

    @FXML
    private Label shopBtn;

    @FXML
    private ScrollPane shopPage;

    @FXML
    private ScrollPane shoppingCartPane;

    @FXML
    private VBox shoppingCart_container;

    @FXML
    private ScrollPane shoppingCart_emty;

    @FXML
    private Label korzinaBtn;


    private List<Product> bestSelers;
    private List<Product> cards_flowers;
    ShoppingCart shoppingCart = new ShoppingCart();

//    private List<Product> shoppingList = new ArrayList<>();
    @FXML
    private void onClickShopBtn() {
        homePage.setVisible(false);
        shopPage.setVisible(true);
        shoppingCartPane.setVisible(false);
    }
    @FXML
    private void onClickHomeBtn() {
        shopPage.setVisible(false);
        homePage.setVisible(true);
        shoppingCartPane.setVisible(false);
    }
    @FXML
    private void onClickKorzina() {
        shopPage.setVisible(false);
        homePage.setVisible(false);
        shoppingCartPane.setVisible(true);
    }


    public void initialize() {
        bestSelers = new ArrayList<>(getBestSelers());
        cards_flowers = new ArrayList<>(flowers());

        int column = 0;
        int row = 0;

        for (Product product: bestSelers) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("cart.fxml"));
                Parent Hbox = fxmlLoader.load();
                CartController cartController = fxmlLoader.getController();
                cartController.setCartData(product);
                cartController.setMainContoller(this);

                bestSelers_container.getChildren().add(Hbox);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }

        for (Product product: cards_flowers) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("cart.fxml"));
                Parent cartRoot = fxmlLoader.load();
                CartController cartController = fxmlLoader.getController();
                cartController.setCartDataForShop(product);
                cartController.setMainContoller(this);


                if (column == 4) {
                    column = 0;
                    row++;
                }

                cart_Container.add(cartRoot, column++, row);
                GridPane.setMargin(cart_Container, new Insets(114, 0, 0, 0));

            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void updateShoppingCartDisplay(Product product) {
        List<Product> cartItems = shoppingCart.getItems();
        try {
            System.out.println(product.getId());
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("shopping-cart.fxml"));
            Parent cartRoot = fxmlLoader.load();
            ShoppingCartController shoppingCartController = fxmlLoader.getController();
            shoppingCartController.setShoppingCart_Data(product);

            shoppingCart_container.getChildren().add(cartRoot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getBestSelers() {
        List<Product> bestSelers = new ArrayList<>();
        Product product = new Product(1, "Daisy", 5, "Huinya");
        product.setUrlImage("src/main/resources/sample/flowershop/img/bestSeler-1.png");
        bestSelers.add(product);

        Product product2 = new Product(1, "Daisy", 5, "Huinya");
        product2.setUrlImage("src/main/resources/sample/flowershop/img/fourth-img.png");
        bestSelers.add(product2);

        Product product3 = new Product(1, "Daisy", 5, "Huinya");
        product3.setUrlImage("src/main/resources/sample/flowershop/img/bestSeler-2.png");
        bestSelers.add(product3);

        Product product4 = new Product(1, "Daisy", 5, "Huinya");
        product4.setUrlImage("src/main/resources/sample/flowershop/img/bestSeler-3.png");
        bestSelers.add(product4);

        return bestSelers;
    }

    private List<Product> flowers() {
        List<Product> fl = new ArrayList<>();

        Product product = new Product(1, "Daisy", 5, "Huinya");
        product.setUrlImage("src/main/resources/sample/flowershop/img/bestSeler-1.png");
        fl.add(product);

        Product product2 = new Product(1, "Daisy", 5, "Huinya");
        product2.setUrlImage("src/main/resources/sample/flowershop/img/fourth-img.png");
        fl.add(product2);

        Product product3 = new Product(1, "Daisy", 5, "Huinya");
        product3.setUrlImage("src/main/resources/sample/flowershop/img/bestSeler-2.png");
        fl.add(product3);

        Product product4 = new Product(1, "Daisy", 5, "Huinya");
        product4.setUrlImage("src/main/resources/sample/flowershop/img/bestSeler-3.png");
        fl.add(product4);

        return fl;
    }
}
