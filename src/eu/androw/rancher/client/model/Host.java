package eu.androw.rancher.client.model;

/**
 * Created by Androw on 16/05/2016.
 */
public class Host {
    public String getHostname() {
        return hostname;
    }

    public String getState() {
        return state;
    }

    private String hostname;
    private String state;

    @Override
    public String toString() {
        return "Host{" +
                "hostname='" + hostname + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
