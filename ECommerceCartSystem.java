import java.util.HashMap;
import java.util.Map;

// Base class for products
abstract class Product {
    private String name;
    private double price;
    private boolean available;

    public Product(String name, double price, boolean available) {
        this.name = name;
        this.price = price;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    @Override
    public String toString() {
        return name + " - $" + String.format("%.2f", price);
    }
}

// Concrete product classes
class Laptop extends Product {
    public Laptop(String name, double price, boolean available) {
        super(name, price, available);
    }
}

class Headphones extends Product {
    public Headphones(String name, double price, boolean available) {
        super(name, price, available);
    }
}

// Shopping cart
class ShoppingCart {
    private Map<Product, Integer> cartItems = new HashMap<>();

    public void addToCart(Product product, int quantity) {
        if (product.isAvailable()) {
            cartItems.put(product, cartItems.getOrDefault(product, 0) + quantity);
        } else {
            System.out.println(product.getName() + " is not available.");
        }
    }

    public void removeFromCart(Product product, int quantity) {
        if (cartItems.containsKey(product)) {
            int currentQuantity = cartItems.get(product);
            if (currentQuantity <= quantity) {
                cartItems.remove(product);
            } else {
                cartItems.put(product, currentQuantity - quantity);
            }
        }
    }

    public double calculateTotalBill() {
        double totalBill = 0;
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            totalBill += product.getPrice() * quantity;
        }
        return totalBill;
    }

    public void displayCart() {
        System.out.println("Cart Items:");
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println("You have " + quantity + " " + product.getName() + " in your cart.");
        }
        System.out.println("Total Bill: Your total bill is $" + String.format("%.2f", calculateTotalBill()));
    }
}

public class ECommerceCartSystem {
    public static void main(String[] args) {
        Laptop laptop = new Laptop("Laptop", 1000, true);
        Headphones headphones = new Headphones("Headphones", 50, true);

        ShoppingCart cart = new ShoppingCart();
        cart.addToCart(laptop, 2);
        cart.addToCart(headphones, 1);
        cart.displayCart();
    }
}
