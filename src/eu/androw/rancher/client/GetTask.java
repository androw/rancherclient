package eu.androw.rancher.client;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import eu.androw.rancher.client.model.AndrowList;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Androw on 21/05/2016.
 */
public class GetTask<T extends AndrowList> extends AsyncTask<Call<T>, Void, T> {
    private Activity activity;
    private ListView lv;

    public GetTask (Activity activity, ListView lv) {
        this.activity = activity;
        this.lv = lv;
    }

    protected T doInBackground(Call<T>... calls) {
        T body = null;
        try {
            Response<T> response = calls[0].execute();
            if (response.isSuccessful()) {
                body = response.body();
            } else {
                throw new Exception(response.errorBody().string());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return body;
    }
    protected void onPostExecute(T result) {
        if (result != null) {
            ArrayAdapter adapter = new ArrayAdapter(activity.getApplicationContext(), R.layout.line, result.getList());
            lv.setAdapter(adapter);
        }
    }
}