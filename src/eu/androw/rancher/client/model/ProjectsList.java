package eu.androw.rancher.client.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Androw on 16/05/2016.
 */
public class ProjectsList implements AndrowList {
    private String type;

    @SerializedName("data")
    private List<Project> list;

    public String getType() {
        return type;
    }

    public List<Project> getList() {
        return list;
    }
}
