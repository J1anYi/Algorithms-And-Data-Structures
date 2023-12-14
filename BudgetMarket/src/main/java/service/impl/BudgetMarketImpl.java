package service.impl;

import model.Client;
import model.Product;
import model.Store;
import service.iBudgetMarket;
import utils.Vector;

/**
 * implementation of iBudgetMarket interface
 * Created by Jian Yi on 2023-11-23.
 * @author zhoujianyi
 */
public class BudgetMarketImpl implements iBudgetMarket {

    private Vector<Store> stores;
    private Vector<Product> products;
    private Vector<Client> clients;

    public BudgetMarketImpl() {
        stores = new Vector<>();
        products = new Vector<>();
        clients = new Vector<>();
    }

    /************************** PART 1 ***************************/
    /*
     * Add a new store with given parameters: store's name and name of the street.
     *
     * @param name of the store
     *
     * @param street address
     *
     * @return ID of the store
     */
    @Override
    public int addStore(String name, String street) {
        Store store = new Store(name, street);
        stores.add(store);
        return store.getId();
    }

    /*
     * Add a product with given parameters: category, price, and store in which this
     * product is available.
     *
     * @param category of the product, e.g. apple, orange, chicken, ham, etc.
     *
     * @param price
     *
     * @param storeID where this product is available
     *
     * @return ID of the product
     */
    @Override
    public int addProduct(String category, Float price, int storeID) {
        Product product = new Product(category, price);
        products.add(product);

        Store store = findStore(storeID);
        if (store == null) {
            throw new RuntimeException("Store with id " + storeID + " does not exist");
        }
        store.getProducts().add(product);

        return product.getId();
    }

    /*
     * Add a new client with given parameters: name, email address, and street
     * address.
     *
     * @param client's name
     *
     * @param client's email address
     *
     * @param client's street address
     *
     * @return ID of the Client
     */
    @Override
    public int addClient(String name, String email, String street) {
        Client client = new Client(name, email, street);
        clients.add(client);

        return client.getId();
    }

    /*
     * Print all stores in the following format:
     * "store ID, store name, store address"
     */
    @Override
    public void printAllStores() {
        for (int i = 0; i < stores.size(); i++) {
            Store store = stores.get(i);
            System.out.println(store);
        }
    }

    /*
     * Print all products in the following format:
     * "product ID, category, price"
     */
    @Override
    public void printAllProducts() {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.println(product);
        }
    }

    /*
     * Print all clients in the following format:
     * "client ID, name, email, address, shopping list"
     */
    @Override
    public void printAllClients() {
        for (int i = 0; i < clients.size(); i++) {
            Client client = clients.get(i);
            System.out.println(client);
        }
    }

    /*
     * Return store based on storeID or null if store does not exist.
     *
     * @param storeID
     *
     * @return Store object
     */
    @Override
    public Store findStore(int storeID) {
        for (int i = 0; i < stores.size(); i++) {
            Store store = stores.get(i);
            if (store.getId() == storeID) {
                return store;
            }
        }

        return null;
    }

    /*
     * Return product based on productID or null if product does not exist.
     *
     * @param productID
     *
     * @return Product object
     */
    @Override
    public Product findProduct(int productID) {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getId() == productID) {
                return product;
            }
        }

        return null;
    }

    /*
     * Return client based on clientID or null if client does not exist.
     *
     * @param clientID
     *
     * @return Client object
     */
    @Override
    public Client findClient(int clientID) {
        for (int i = 0; i < clients.size(); i++) {
            Client client = clients.get(i);
            if (client.getId() == clientID) {
                return client;
            }
        }

        return null;
    }

    /************************** end of PART 1 ***************************/

    /************************** PART 2 ***************************/

    /*
     * Add product to client's shopping list.
     *
     * @param client id
     *
     * @param product category
     *
     * @return true if successful, false otherwise
     */
    @Override
    public boolean addProductToShoppingList(int clientID, String productCategory) {
        // find client
        Client client = findClient(clientID);
        if (client == null) {
            System.out.println("Client with id " + clientID + " does not exist");
            return false;
        }

        // find product
        Vector<Product> addProduct = new Vector<>();
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (p.getCategory().equals(productCategory)) {
                addProduct.add(p);
            }
        }
        if (addProduct.size() == 0) {
            System.out.println("Product with category " + productCategory + " does not exist");
            return false;
        }

        // add product to client's shopping list
        for (int i = 0; i < addProduct.size(); i++) {
            Product p = addProduct.get(i);
            client.getShoppingList().add(p);
        }

        return true;
    }

    /*
     * Return stores the client needs to visit to buy (ideally all) products from
     * their shopping list.
     *
     * @param client ID
     *
     * @return Vector of all stores the client needs to visit.
     */
    @Override
    public Vector buyProducts(int clientID) {
        // find client
        Client client = findClient(clientID);
        if (client == null) {
            System.out.println("Client with id " + clientID + " does not exist");
            return null;
        }

        // find all products in client's shopping list
        Vector<Product> shoppingList = client.getShoppingList();
        if (shoppingList.size() == 0) {
            System.out.println("Client with id " + clientID + " does not have any product in shopping list");
            return null;
        }

        // clone shopping list, prevent changing the original shopping list
        Vector<Product> cloneShoppingList = new Vector<>();
        for (int i = 0; i < shoppingList.size(); i++) {
            Product p = shoppingList.get(i);
            Product cloneProduct = new Product(p.getCategory(), p.getPrice());
            cloneProduct.setId(p.getId());
            cloneShoppingList.add(cloneProduct);
        }

        // remove products in shopping list of client
        shoppingList.clear();

        return cloneShoppingList;
    }

    /*
     * Remove product offered in the store.
     *
     * @param product category
     *
     * @param store ID
     *
     * @return true if successful, false otherwise
     */
    @Override
    public boolean removeProductFromStore(String category, int storeID) {
        // find store
        Store store = findStore(storeID);
        if (store == null) {
            System.out.println("Store with id " + storeID + " does not exist");
            return false;
        }

        // find product
        Vector<Product> removeProduct = new Vector<>();
        for (int i = 0; i < store.getProducts().size(); i++) {
            Product p = store.getProducts().get(i);
            if (p.getCategory().equals(category)) {
                removeProduct.add(p);
            }
        }
        if (removeProduct.size() == 0) {
            System.out.println("Product with category " + category + " does not exist");
            return false;
        }

        // remove product from store
        for (int i = 0; i < removeProduct.size(); i++) {
            Product p = removeProduct.get(i);

            // remove product from store
            store.getProducts();
            for (int j = 0; j < store.getProducts().size(); j++) {
                Product product = store.getProducts().get(j);
                if (product.getId() == p.getId()) {
                    store.getProducts().remove(j);
                    break;
                }
            }
        }

        return true;
    }

    /************************** end of PART 2 ***************************/

    /************************** PART 3 ***************************/

    /*
     * Add a street.
     *
     * @param name of the street
     */
    @Override
    public void addStreet(String street) {

    }

    /*
     * Connects two streets.
     *
     * @param street1
     *
     * @param street2
     *
     * @param distance
     */
    @Override
    public void connectStreets(String street1, String street2, int distance) {

    }

    /*
     * Return stores, within given distance, the client needs to visit to buy
     * products from their shopping list.
     *
     * @param clientID
     *
     * @param distance
     *
     * @return Vector of stores to visit
     *
     */
    @Override
    public Vector getShoppingDirections(int clientID, int distance) {
        return null;
    }

    /************************** end of PART 3 ***************************/

    public Vector<Store> getStores() {
        return stores;
    }

    public void setStores(Vector<Store> stores) {
        this.stores = stores;
    }

    public Vector<Product> getProducts() {
        return products;
    }

    public void setProducts(Vector<Product> products) {
        this.products = products;
    }

    public Vector<Client> getClients() {
        return clients;
    }

    public void setClients(Vector<Client> clients) {
        this.clients = clients;
    }
}
