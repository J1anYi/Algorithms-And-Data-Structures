package model;

import utils.IDGenerator;

/*
 * a product with given parameters: category, price, and store in which this
 * product is available.
 *
 * @author zhoujianyi
 */
public class Product {
    private int id;
    private String category;
    private float price;

    public Product(String category, float price) {
        this.id = IDGenerator.getInstance().generateProductId();
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}
