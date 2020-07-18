package eu.androw.rancher.client.model;

/**
 * Created by Androw on 16/05/2016.
 */
public class Container {
    private String name;
    private String state;

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Container{" +
                "name='" + name + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
