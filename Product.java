import java.io.Serializable;

public class Product implements Serializable {

    private static final long serialVersionUID = 1L; // Ensures compatibility during serialization
    private String name;
    private double price;
    private double quantity; // Quantity in kg or number
    private String unit; // Unit for measurement (e.g., kg or piece)

    // Constructor to initialize a product
    public Product(String name, double price, double quantity, String unit) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.unit = unit;
    }

    // Getters for product properties
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    // Method to calculate the total price (quantity * price)
    public double getTotalPrice() {
        return quantity * price;
    }

    // Override toString() to display product details in the text area
    @Override
    public String toString() {
        return name + " - " + quantity + " " + unit + " - ₹" + price + " each - Total: ₹" + getTotalPrice();
    }
    public String getUnitSymbol() {
        switch (unit) {
            case "grams":
                return "g";
            case "kilograms":
                return "kg";
            case "pieces":
                return "pcs";
            case "dozens":
                return "dz";
            default:
                return "";
        }
}
}
