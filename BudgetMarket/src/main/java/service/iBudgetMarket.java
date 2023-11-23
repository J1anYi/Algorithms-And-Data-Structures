import model.Client;
import model.Product;
import model.Store;
import utils.Vector;

public interface iBudgetMarket {

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
	public int addStore(String name, String street);

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
	public int addProduct(String category, Float price, int storeID);

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
	public int addClient(String name, String email, String street);

	/*
	 * Print all stores in the following format:
	 * "store ID, store name, store address"
	 */
	public void printAllStores();

	/*
	 * Print all products in the following formta: "product ID, category, price, "
	 */
	public void printAllProducts();

	/*
	 * Print all clients in the following format:
	 * "client ID, name, email, address, shopping list"
	 *
	 */
	public void printAllClients();

	/*
	 * Return store based on storeID or null if store does not exist.
	 * 
	 * @param storeID
	 * 
	 * @return Store object
	 */
	public Store findStore(int storeID);

	/*
	 * Return product based on productID or null if product does not exist.
	 * 
	 * @param productID
	 * 
	 * @return Product object
	 */
	public Product findProduct(int productID);

	/*
	 * Return client based on clientID or null if client does not exist.
	 * 
	 * @param clientID
	 * 
	 * @return Client object
	 */
	public Client findClient(int clientID);

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
	public boolean addProductToShoppingList(int clientID, String productCategory);

	/*
	 * Return stores the client needs to visit to buy (ideally all) products from
	 * their shopping list.
	 * 
	 * @param client ID
	 * 
	 * @return Vector of all stores the client needs to visit.
	 */
	public Vector buyProducts(int clientID);

	/*
	 * Remove product offered in the store.
	 * 
	 * @param product category
	 * 
	 * @param store ID
	 * 
	 * @return true if successful, false otherwise
	 */

	public boolean removeProductFromStore(String category, int storeID);

	/************************** end of PART 2 ***************************/

	/************************** PART 3 ***************************/

	/*
	 * Add a street.
	 * 
	 * @param name of the street
	 */
	public void addStreet(String street);

	/*
	 * Connects two streets.
	 * 
	 * @param street1
	 * 
	 * @param street2
	 * 
	 * @param distance
	 */
	public void connectStreets(String street1, String street2, int distance);

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
	public Vector getShoppingDirections(int clientID, int distance);

	/************************** end of PART 3 ***************************/
}