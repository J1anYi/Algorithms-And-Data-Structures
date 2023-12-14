import model.Client;
import model.Product;
import model.Store;
import service.impl.BudgetMarketImpl;
import utils.Vector;

/**
 * Created by Jian Yi on 2023-11-23.
 * @author zhoujianyi
 */
public class Main {

    public static void main(String[] args) {
        BudgetMarketImpl budgetMarket = new BudgetMarketImpl();

        // Part 1 Test
        // test store
        System.out.println("------------------Test Store------------------");
        int storeID = budgetMarket.addStore("Store 1", "Street 1");
        budgetMarket.addStore("Store 2", "Street 2");
        for (int i = 3; i < 15; i++) {
            budgetMarket.addStore("Store " + i, "Street " + i);
        }
        Store store = budgetMarket.findStore(storeID);
        budgetMarket.getStores().remove(7);
        System.out.println(store);
        budgetMarket.printAllStores();

        // test product
        System.out.println("------------------Test Product------------------");
        budgetMarket.addProduct("Product 1", 1.0f, storeID);
        budgetMarket.addProduct("Product 2", 2.0f, storeID);
        Product product = budgetMarket.findProduct(1);
        budgetMarket.printAllStores();
        System.out.println(product);
        budgetMarket.printAllProducts();

        // test client
        System.out.println("------------------Test Client------------------");
        int productId = budgetMarket.addProduct("Product 1", 1.0f, storeID);
        budgetMarket.addClient("jianyi", "jianyizhou@vub.be", "Street 1");
        budgetMarket.addClient("John", "123@gmail.com", "Street 2");
        Client client = budgetMarket.findClient(1);
        System.out.println(client);
        budgetMarket.printAllClients();

        // Part 2 Test
        System.out.println("------------------Test Part 2------------------");
        // test add product to shopping list
        System.out.println("------------------Test add product to shopping list------------------");
        budgetMarket.addProductToShoppingList(1, "Product 1");
        budgetMarket.addProduct("Product 2", 3.0f, 13);
        budgetMarket.addProductToShoppingList(1, "Product 2");
        // print shopping list
        Vector<Product> shoppingList = budgetMarket.findClient(1).getShoppingList();
        System.out.println("Shopping list of client 1:");
        for (int i = 0; i < shoppingList.size(); i++) {
            System.out.println(shoppingList.get(i));
        }

        // test buy product
        System.out.println("------------------Test buy product------------------");
        Vector stores = budgetMarket.buyProducts(1);
        // print stores to buy products
        System.out.println("Stores to buy products:");
        for (int i = 0; i < stores.size(); i++) {
            System.out.println(stores.get(i));
        }

        // test remove product
        System.out.println("------------------Test remove product------------------");
        Vector<Product> products = budgetMarket.findStore(storeID).getProducts();
        System.out.println("Original products in store " + storeID + ":");
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i));
        }
        System.out.println("------------------After remove product------------------");
        budgetMarket.removeProductFromStore("Product 1", storeID);
        System.out.println("Products in store " + storeID + ":");
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i));
        }

    }
}
