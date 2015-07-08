package mx.softux.androidweardemo;

/**
 * Created by juan on 7/7/15.
 */
public class Device {
    public Integer id;
    public String token;

    public Device() {
    }

    public Device(String token) {
        this.token = token;
    }

    public Device(Integer id, String token) {
        this.id = id;
        this.token = token;
    }
}
