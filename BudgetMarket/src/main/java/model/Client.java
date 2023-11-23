package model;


/*
 * Add a new client with given parameters: name, email address, and street
 * address.
 *
 * @author zhoujianyi
 */
public class Client {
    public static int autoincrement = 0;
    private int id;
    private String name;
    private String email;
    private String address;

    public Client() {
    }

    public Client(String name, String email, String address) {
        this.id = autoincrement++;
        this.name = name;
        this.email = email;
        this.address = address;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString(){
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                '}';
    }
}
