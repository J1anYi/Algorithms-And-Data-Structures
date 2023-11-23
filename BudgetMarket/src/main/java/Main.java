import model.Client;
import model.Product;
import model.Store;
import service.impl.BudgetMarketImpl;

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
    }
}
