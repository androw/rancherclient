package eu.androw.rancher.client;

import eu.androw.rancher.client.model.ApplicationsList;
import eu.androw.rancher.client.model.ContainersList;
import eu.androw.rancher.client.model.HostsList;
import eu.androw.rancher.client.model.ProjectsList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

import java.util.Collection;

/**
 * Created by Androw on 16/05/2016.
 */
public interface RancherService {
    @GET("v1/projects/{env}/environments")
    Call<ProjectsList> listProjects(@Header("Authorization") String auth);

    @GET("v1/projects/{env}/hosts")
    Call<HostsList> listHosts(@Header("Authorization") String auth, @Path("env") String env);

    @GET("v1/projects/{env}/containers")
    Call<ContainersList> listContainers(@Header("Authorization") String auth, @Path("env") String env);

    @GET("v1/projects/{env}/environments")
    Call<ApplicationsList> listApplications(@Header("Authorization") String auth, @Path("env") String env);
}
