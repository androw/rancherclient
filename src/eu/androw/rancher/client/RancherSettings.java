package eu.androw.rancher.client;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.androw.rancher.client.model.HostsList;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RancherSettings extends Activity {

    private SharedPreferences settings;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        settings = PreferenceManager.getDefaultSharedPreferences(this);

        setContentView(R.layout.settings);

        EditText url = (EditText) findViewById(R.id.url);
        EditText publicKey = (EditText) findViewById(R.id.publickey);
        EditText privateKey = (EditText) findViewById(R.id.privatekey);
        url.setText(settings.getString("url", "_YOUR_RANCHER_URL_"));
        publicKey.setText(settings.getString("publickey", "_YOUR_RANCHER_PUBKEY_"));
        privateKey.setText(settings.getString("privatekey", "_YOUR_RANCHER_SECKEY_"));

    }

    protected void saveSettings() {
        SharedPreferences.Editor editor = settings.edit();

        try {
            editor.putString("url", ((EditText) findViewById(R.id.url)).getText().toString());
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }
        try {
            editor.putString("publickey", ((EditText) findViewById(R.id.publickey)).getText().toString());
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }
        try {
            editor.putString("privatekey", ((EditText) findViewById(R.id.privatekey)).getText().toString());
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }

        editor.apply();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.save:
                saveSettings();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);
        return true;
    }

}
