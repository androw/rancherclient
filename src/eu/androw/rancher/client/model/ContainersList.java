package eu.androw.rancher.client.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Androw on 16/05/2016.
 */
public class ContainersList implements AndrowList {
    private String type;

    @SerializedName("data")
    private List<Container> list;

    public String getType() {
        return type;
    }

    public List<Container> getList() {
        return list;
    }
}
