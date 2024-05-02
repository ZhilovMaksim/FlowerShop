package sample.flowershop;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Product{
    private int id;
    private String name;
    private double price;
    private String description;
    private String urlImage;
    private int itemCount = 1;
    public Product(int id, String name, double price, String description){
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

class ShoppingCart {
    private Map<Integer, Product> items;

    private double totalPrice = 0;

    public ShoppingCart() {
        this.items = new HashMap<>();
    }

    public Map<Integer, Product> getItems() {
        return items;
    }

    public void addProduct(Product product) {
        items.put(product.getId(), product);
    }

    public void removeProduct(int productId) {
        items.remove(productId);
    }

    public void displayCart() {
        //MAX SDELAY HUYNYU
    }

    public double calculateTotal() {
        totalPrice = 0;
        for (Product item : items.values()) {
            totalPrice += (item.getPrice() * item.getItemCount());
        }
        return totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void updateProduct(int productId, String newName, double newPrice, String newDescription) {
        for (Product item : items.values()) {
            if (item.getId() == productId) {
                item.setName(newName);
                item.setPrice(newPrice);
                item.setDescription(newDescription);
                return;
            }
        }
    }
}
public class Shopping {
    public static void main(String[] args) {

    }
}
