package utils;

public class IDGenerator {

    private int autoincrementClient = 0;
    private int autoincrementProduct = 0;
    private int autoincrementStore = 0;

    // signleton
    private static IDGenerator instance = null;

    private IDGenerator() {

    }

    public static IDGenerator getInstance() {
        if (instance == null) {
            instance = new IDGenerator();
        }
        return instance;
    }

    public int generateClientId() {
        return autoincrementClient++;
    }

    public int generateProductId() {
        return autoincrementProduct++;
    }

    public int generateStoreId() {
        return autoincrementStore++;
    }

}
