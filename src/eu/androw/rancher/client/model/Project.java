package eu.androw.rancher.client.model;

/**
 * Created by Androw on 16/05/2016.
 */
public class Project {
    private String id;
    private String name;

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
