package model;

/*
 * a product with given parameters: category, price, and store in which this
 * product is available.
 */
public class Product {
    public static int autoincrement = 0;
    private int id;
    private String category;
    private float price;

    public Product(){}

    public Product(String category, float price) {
        this.id = autoincrement++;
        this.category = category;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
