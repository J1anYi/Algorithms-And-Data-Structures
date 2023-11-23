package model;
import utils.Vector;
/*
 * a new store with given parameters: store's name and name of the street.
 */
public class Store {

    public static int autoincrement = 0;
    private int id;
    private String name;

    private String address;

    private Vector<Product> products;

    public Store() {
    }
    public Store(String name, String address) {
        this.id = autoincrement++;
        this.name = name;
        this.address = address;
        products = new Vector<>();
    }

    public Vector<Product> getProducts() {
        return products;
    }

    public void setProducts(Vector<Product> products) {
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", products=" + products +
                '}';
    }
}
