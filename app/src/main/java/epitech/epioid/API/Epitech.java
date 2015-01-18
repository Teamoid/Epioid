package epitech.epioid.API;

import android.util.Log;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import epitech.epioid.API.Items.EpitechContainer;
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
        EpitechItem ret = null;

        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            ret = (EpitechItem)mapper.readValue(obj.toString(), toGet);
        } catch (Exception e) {
            Log.e(TAG, e.toString(), e);
        }
        return ret;
    }

    static JsonHttpResponseHandler getHandler(final Class klass, final EpitechApiCallback callback) {
        return (new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response)
            {
                callback.callBack(Epitech.parseJSON(response, klass));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                callback.callBack(null);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                callback.callBack(null);
            }
        });
    }

    static JsonHttpResponseHandler getArrayHandler(final Class klass, final Class subClass, final EpitechApiCallback callback) {
        return (new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.callBack(null);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response)
            {
                try {
                    EpitechContainer ec = (EpitechContainer)klass.getConstructor().newInstance();

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            ec.addItem(Epitech.parseJSON(response.getJSONObject(i), subClass));
                        } catch (JSONException e) {
                            Log.e(TAG, e.toString(), e);
                        }
                    }
                    callback.callBack(ec);
                } catch (Exception e) {
                    callback.callBack(null);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                callback.callBack(null);
            }
        });
    }

    static RequestParams getBaseRequestParams() {
        RequestParams requestParams = new RequestParams();

        requestParams.put("token", Epitech.getToken());
        return requestParams;
    }

    public static void login(String login, String password, EpitechApiCallback callback) {
        LoginHelper.connect(login, password, callback);
    }

    public static void getInfos(EpitechApiCallback callback) {
        InformationHelper.getInfos(callback);
    }

    public static void getPlanningFor(String start, String end, EpitechApiCallback callback) {
        PlanningHelper.getPlanningFor(start, end, callback);
    }

    public static void getSusiesFor(String start, String end, String get, EpitechApiCallback callback) {
        SusieHelper.getSusiesFor(start, end, get, callback);
    }

    public static void getSusiesFor(String start, String end, EpitechApiCallback callback) {
        SusieHelper.getSusiesFor(start, end, SusieHelper.Get.ALL, callback);
    }

    //TODO doesn't work... Event with curl. Just gets a Susie object filled with null attributes.
    public static void getSusieById(String id, EpitechApiCallback callback) {
        SusieHelper.getSusieById(id, callback);
    }

    //TODO not implemented
    public static void subscribeToSusie(String id, EpitechApiCallback callback) {
        SusieHelper.subscribeToSusie(id, callback);
    }

    //TODO not implemented
    public static void unsubscribeToSusie(String id, EpitechApiCallback callback) {
        SusieHelper.unsubscribeToSuse(id, callback);
    }

    public static void getProjects(EpitechApiCallback callback) {
        ProjectHelper.getProjects(callback);
    }

    //TODO Not tested.
    public static void getProject(String scolaryear, String codemodule, String codeinstance, String codeacti, EpitechApiCallback callback) {
        ProjectHelper.getProject(scolaryear, codemodule, codeinstance, codeacti, callback);
    }

    //TODO not implemented
    public static void subscribeToProject(String scolaryear, String codemodule, String codeinstance, String codeacti, EpitechApiCallback callback) {

    }

    //TODO not implemented
    public static void unsubscribeToProject(String scolaryear, String codemodule, String codeinstance, String codeacti, EpitechApiCallback callback) {

    }

    public static void getMarks(EpitechApiCallback callback) {
        InformationHelper.getMarks(callback);
    }

    //TODO not tested, webservice is down...
    public static void getMessages(EpitechApiCallback callback) {
        InformationHelper.getMessages(callback);
    }

    //TODO not tested
    public static void getAlerts(EpitechApiCallback callback) {
        InformationHelper.getAlerts(callback);
    }
}