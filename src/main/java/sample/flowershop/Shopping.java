package sample.flowershop;
import java.util.ArrayList;
import java.util.List;
class Product{
    private int id;
    private String name;
    private double price;
    private String description;
    public Product(int id, String name, double price, String description){
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
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
class Flower extends Product {
    public Flower(int id, String name, double price, String description) {
        super(id, name, price, description);
    }
}

class Bouquet extends Product {
    private List<Flower> flowers;

    public Bouquet(int id, String name, double price, String description) {
        super(id, name, price, description);
        this.flowers = new ArrayList<>();
    }

    public void addFlower(Flower flower) {
        flowers.add(flower);
    }

    public void removeFlower(Flower flower) {
        flowers.remove(flower);
    }

    public List<Flower> getFlowers() {
        return flowers;
    }
}
class ShoppingCart {
    private List<Product> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addProduct(Product product) {
        items.add(product);
    }

    public void removeProduct(int productId) {
        items.removeIf(product -> product.getId() == productId);
    }

    public void displayCart() {
        //MAX SDELAY HUYNYU
    }

    private double calculateTotal() {
        double total = 0;
        for (Product item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public void updateProduct(int productId, String newName, double newPrice, String newDescription) {
        for (Product item : items) {
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
