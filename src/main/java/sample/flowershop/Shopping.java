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
}

class ShoppingCart{
    private List<Product> items;
    public ShoppingCart(){
        this.items = new ArrayList<>();
    }
    public void addProduct(Product product){
        items.add(product);
    }
    public void removeProduct(int productId){
        items.removeIf(product -> product.getId()==productId);
    }
    public void displayCart(){
        //MAX SDELAY HUYNYU
    }

    private double calculateTotal(){
        double total = 0;
        for(Product item: items){
            total+=item.getPrice();
        }
        return total;
    }
}
public class Shopping {

}
