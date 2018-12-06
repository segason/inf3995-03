package applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.model;

/**
 * this class is the model of user
 */
public class User {

    private int id;
    private String name;
    private String ip;
    private String mac;
    private boolean isBlocked;

    public User(String name, String ip, String mac, int id) {
        this.name = name;
        this.ip = ip;
        this.mac = mac;
        this.id = id;
    }

    public User(String ip, String mac, int id) {
        this.ip = ip;
        this.mac = mac;
        this.id = id;
    }

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public User(String name, String ip,String mac,boolean isBlocked) {
        this.name = name;
        this.ip = ip;
        this.mac = mac;
        this.isBlocked = isBlocked;
    }

    public int getId() {
        return id;
    }

    public boolean getIsBlocked(){return isBlocked;}

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
