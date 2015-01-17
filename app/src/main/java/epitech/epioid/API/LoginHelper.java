package epitech.epioid.API;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by michelantoine on 14/01/15.
 */
class LoginHelper {
    public static final String TAG = "LoginTask";

    public static void connect(String login, String password, final EpitechApiCallback callback) {
        RequestParams requestParams = Epitech.getBaseRequestParams();
        requestParams.put("password", password);

        Epitech.client.post(Epitech.URL + "/login", requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    Epitech.setToken(response.getString("token"));
                    Log.v(TAG, "Connected with : " + Epitech.getToken());
                } catch (JSONException e) {
                    Log.e(TAG, "Invalid JSON");
                }
                callback.callBack(null);
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
