package sample.flowershop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
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
import java.util.HashMap;
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

    @FXML
    private Label subtotalForItems;

    @FXML
    private Label totalPrice;
    private HashMap<Integer, Product> cartItems = new HashMap<>();


    private List<Product> bestSelers;
    private List<Product> cards_flowers;
    private ShoppingCartController shoppingCartController;
    private double total = 0;
    private String currentPage;
    ShoppingCart shoppingCart = new ShoppingCart();

//    private List<Product> shoppingList = new ArrayList<>();
    @FXML
    private void onClickShopBtn() {
        homePage.setVisible(false);
        shopPage.setVisible(true);
//        shoppingCart_emty.setVisible(false);
        shopBtn.getStyleClass().add("current-page-btn");
        homeBtn.getStyleClass().remove("current-page-btn");
        korzinaBtn.getStyleClass().remove("current-page-btn");
    }
    @FXML
    private void onClickHomeBtn() {
        shopPage.setVisible(false);
        homePage.setVisible(true);
        shoppingCart_emty.setVisible(false);
        homeBtn.getStyleClass().add("current-page-btn");
        shopBtn.getStyleClass().remove("current-page-btn");
        korzinaBtn.getStyleClass().remove("current-page-btn");
    }
    @FXML
    private void onClickKorzina() {
        shopPage.setVisible(false);
        homePage.setVisible(false);
        shoppingCart_emty.setVisible(true);
        homeBtn.getStyleClass().remove("current-page-btn");
        shopBtn.getStyleClass().remove("current-page-btn");
        korzinaBtn.getStyleClass().add("current-page-btn");
    }

//    private void setColorToPageBtn(){
//        if (homePage.isVisible()) {
//            homePage.getStyleClass().add("current-page-btn");
//            shopBtn.getStyleClass().remove("current-page-btn");
//            korzinaBtn.getStyleClass().remove("current-page-btn");
//        }
//        if (shopPage.isVisible()) {
//            homePage.getStyleClass().remove("current-page-btn");
//            shopBtn.getStyleClass().add("current-page-btn");
//            korzinaBtn.getStyleClass().remove("current-page-btn");
//        }
//        if (shoppingCart_emty.isVisible() || shoppingCartPane.isVisible()) {
//            homePage.getStyleClass().remove("current-page-btn");
//            shopBtn.getStyleClass().remove("current-page-btn");
//            korzinaBtn.getStyleClass().add("current-page-btn");
//        }
//    }



    public void initialize() {
//        setColorToPageBtn();
        homeBtn.getStyleClass().add("current-page-btn");
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
        shoppingCart_emty.setVisible(false);
        shoppingCartPane.setVisible(true);
        this.cartItems.put(product.getId(), product);
        System.out.println("Отработка метода updateShoppingCartDisplay(): ");
        for (Integer productId: cartItems.keySet()) {
            System.out.println(productId);
        }

        Product lastProduct = null;
        int lastProductId = 0;
        for (int productId: cartItems.keySet()) {
            lastProductId = productId;
        }
        lastProduct = cartItems.get(lastProductId);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("shopping-cart.fxml"));
            Parent cartRoot = fxmlLoader.load();
            ShoppingCartController shoppingCartController = fxmlLoader.getController();
            this.shoppingCartController = shoppingCartController;
            shoppingCartController.setShoppingCart_Data(lastProduct);

            shoppingCart_container.getChildren().add(cartRoot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        updateTotalDisplay();
    }

    public void removeProductCart(Node productCard, Product product) {
        if (productCard != null && shoppingCart_container.getChildren().contains(productCard)) {
            System.out.println("Отработка метода removeProduct(): ");
            this.cartItems.remove(product.getId());
            shoppingCart_container.getChildren().remove(productCard);
            System.out.println("Размер хэш мапы cartItems: " + cartItems.size()
                    + ",            id продукта который удаляют: " + product.getId());
            System.out.println(cartItems.size());
            updateTotalDisplay();
        }
        if (shoppingCart.getItems().isEmpty()) {
            shoppingCartPane.setVisible(false);
            shoppingCart_emty.setVisible(true);
        }
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void updateTotalDisplay(){
        total = 0;
        shoppingCartController.setMainContoller(this);
        total = shoppingCart.calculateTotal();
//        System.out.println("Counter inside updateTotalDisplay(): " + counter);
//        total = // lambda itemPirce, itemCounter: sum(itemPrice * itemCounter)
        int itemCounter = 0;
        for (Product item: cartItems.values()) {
            itemCounter += item.getItemCount();
        }
        subtotalForItems.setText("Subtotal for " + itemCounter + " items: ");
        String totalStr = String.valueOf(total);
        totalPrice.setText(totalStr.substring(0, totalStr.indexOf(".")) + "$");
//        System.out.println("Total inside updateTotalDisplay(): " + total);
    }

    public List<Product> getBestSelers() {
        List<Product> bestSelers = new ArrayList<>();
        Product product = new Product(1, "Daisy", 5, "Huinya");
        product.setUrlImage("src/main/resources/sample/flowershop/img/bestSeler-1.png");
        bestSelers.add(product);

        Product product2 = new Product(2, "Haisy", 5, "Huinya");
        product2.setUrlImage("src/main/resources/sample/flowershop/img/perwinkie.png");
        bestSelers.add(product2);

        Product product3 = new Product(3, "Pimp", 5, "Huinya");
        product3.setUrlImage("src/main/resources/sample/flowershop/img/bestSeler-2.png");
        bestSelers.add(product3);

        Product product4 = new Product(4, "Hrin", 5, "Huinya");
        product4.setUrlImage("src/main/resources/sample/flowershop/img/bestSeler-3.png");
        bestSelers.add(product4);

        return bestSelers;
    }

    private List<Product> flowers() {
        List<Product> fl = new ArrayList<>();

        Product product = new Product(1, "Daisy", 5, "Huinya");
        product.setUrlImage("src/main/resources/sample/flowershop/img/bestSeler-1.png");
        fl.add(product);

        Product product2 = new Product(2, "Daisy", 5, "Huinya");
        product2.setUrlImage("src/main/resources/sample/flowershop/img/perwinkie.png");
        fl.add(product2);

        Product product3 = new Product(3, "Daisy", 5, "Huinya");
        product3.setUrlImage("src/main/resources/sample/flowershop/img/bestSeler-2.png");
        fl.add(product3);

        Product product4 = new Product(4, "Daisy", 5, "Huinya");
        product4.setUrlImage("src/main/resources/sample/flowershop/img/bestSeler-3.png");
        fl.add(product4);

        return fl;
    }
}
