package epitech.epioid.API;

import android.util.Log;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

import epitech.epioid.API.Items.EpitechItem;

/**
 * Created by michelantoine on 14/01/15.
 */
public class Epitech {
    public static final String URL = "https://epitech-api.herokuapp.com";
    private static final String TAG = "Epitech";

    private static String token = null;
    public static String getToken() {
        return token;
    }
    public static void setToken(String token) {
        Epitech.token = token;
    }

    static AsyncHttpClient client = new AsyncHttpClient();
    static ObjectMapper mapper = new ObjectMapper();

    static EpitechItem parseJSON(JSONObject obj, Class toGet) {
        EpitechItem ret;

        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            ret = (EpitechItem)mapper.readValue(obj.toString(), toGet);
        } catch (Exception e) {
            Log.e(TAG, e.toString(), e);
            ret = null;
        }
        return ret;
    }

    static JsonHttpResponseHandler getHandler(final Class klass, final EpitechApiCallback callback) {
        return (new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.callBack(Epitech.parseJSON(response, klass));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                callback.callBack(null);
            }
        });
    }

    public static void login(String login, String password, EpitechApiCallback callback) {
        LoginHelper.connect(login, password, callback);
    }

    public static void getInfos(EpitechApiCallback callback) {
        InfosHelper.getInfos(callback);
    }
}