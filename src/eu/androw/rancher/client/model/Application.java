package eu.androw.rancher.client.model;

/**
 * Created by Androw on 16/05/2016.
 */
public class Application {
    private String name;
    private String state;
    private String healthState;

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public String getHealthState() {
        return healthState;
    }

    @Override
    public String toString() {
        return "Application{" +
                "name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", healthState='" + healthState + '\'' +
                '}';
    }
}
