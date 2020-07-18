package eu.androw.rancher.client;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.*;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.androw.rancher.client.model.ApplicationsList;
import eu.androw.rancher.client.model.ContainersList;
import eu.androw.rancher.client.model.HostsList;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RancherClient extends FragmentActivity {
    private static SharedPreferences settings;
    private Gson gson;

    static RancherService rancherService;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe);

        AppSectionsPagerAdapter pagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);

        settings = PreferenceManager.getDefaultSharedPreferences(this);

        gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getURL())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        rancherService = retrofit.create(RancherService.class);
    }

    @Override
    public void onResume() {
        super.onResume();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getURL())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        rancherService = retrofit.create(RancherService.class);
    }

    protected static String getToken() {
        String username = settings.getString("publickey", "_YOUR_RANCHER_PUBKEY_");
        String password = settings.getString("privatekey", "_YOUR_RANCHER_SECKEY_");

        String credentials = username + ":" + password;
        return ("Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP));
    }

    protected static String getURL() {
        return settings.getString("url", "_YOUR_RANCHER_URL_");
    }

    protected static String getEnv() {
        return settings.getString("env", "1a5");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.env:
                return true;
            case R.id.settings:
                Intent intent = new Intent(this, RancherSettings.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    public static class Hosts extends Fragment {
@Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.hosts, container, false);

            return rootView;
        }

        @Override
        public void onResume() {
            super.onResume();
            final ListView listview = (ListView) getActivity().findViewById(R.id.hosts);

            Call<HostsList> hosts = rancherService.listHosts(getToken(), getEnv());

            GetTask<HostsList> gt = new GetTask<>(getActivity(), listview);

            gt.execute(hosts);
        }
    }

    public static class Stacks extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.stacks, container, false);

            return rootView;
        }

        @Override
        public void onResume() {
            super.onResume();
            final ListView listview = (ListView) getActivity().findViewById(R.id.stacks);

            Call<ApplicationsList> stacks = rancherService.listApplications(getToken(), getEnv());

            GetTask<ApplicationsList> gt = new GetTask<>(getActivity(), listview);

            gt.execute(stacks);
        }
    }

    public static class Containers extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.containers, container, false);

            return rootView;
        }

        @Override
        public void onResume() {
            super.onResume();
            final ListView listview = (ListView) getActivity().findViewById(R.id.containers);

            Call<ContainersList> containers = rancherService.listContainers(getToken(), getEnv());

            GetTask<ContainersList> gt = new GetTask<>(getActivity(), listview);

            gt.execute(containers);
        }
    }

    public class AppSectionsPagerAdapter extends FragmentPagerAdapter {

        public AppSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return new RancherClient.Stacks();
                case 1:
                    return new RancherClient.Hosts();
                case 2:
                    return new RancherClient.Containers();
                default:
                    return new RancherClient.Containers();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.stacks);
                case 1:
                    return getString(R.string.hosts);
                case 2:
                    return getString(R.string.containers);
                default:
                    return getString(R.string.containers);
            }
        }
    }
}
