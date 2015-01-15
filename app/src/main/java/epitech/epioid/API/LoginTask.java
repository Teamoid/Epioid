package epitech.epioid.API;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import epitech.epioid.API.Epitech;
import epitech.epioid.API.EpitechApiCallback;

/**
 * Created by michelantoine on 14/01/15.
 */
class LoginTask {
    public static final String TAG = "LoginTask";

    private static final String url = Epitech.URL + "/login";

    public void execute(String login, String password, final EpitechApiCallback callback) {
        RequestParams requestParams = new RequestParams();

        try {
            requestParams.put("login", login);
            requestParams.put("password", password);
        } catch (NullPointerException e) {
            Log.e(TAG, "Missing Parameter");
            return ;
        }

        Epitech.client.post(url, requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    Epitech.setToken(response.getString("token"));
                    Log.v(TAG, "Connected with : " + Epitech.getToken());
                } catch (JSONException e) {
                    Log.e(TAG, "Invalid JSON");
                }
                callback.callBack(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Epitech.setToken(null);
                Log.i(TAG, "Bad credentials");
                callback.callBack(null);
            }
        });
    }

}
