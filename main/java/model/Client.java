package model;

public class Client {
    private int clientId;
    private String name;
    private String address;
    private String email;
    private String phone;

    public Client(int clientId, String name, String address, String email, String phone) {
        this.clientId = clientId;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }
    public Client( String name, String address, String email, String phone) {

        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    // Getters and setters
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Override
    public String toString()
    {
        return name;
    }
}
