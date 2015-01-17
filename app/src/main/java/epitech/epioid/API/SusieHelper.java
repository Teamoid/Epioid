package epitech.epioid.API;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import epitech.epioid.API.Items.Susie;
import epitech.epioid.API.Items.SusiePlanning;

/**
 * Created by michelantoine on 17/01/15.
 */
public class SusieHelper {
    private static final String TAG = "SusieHelper";

    public static class Get {
        public static final String ALL = "all";
        public static final String FREE = "free";
        public static final String REGISTERED = "registered";
    }

    public static void getSusiesFor(String start, String end, String get, final EpitechApiCallback callback) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("token", Epitech.getToken());
        requestParams.put("start", start);
        requestParams.put("end", end);
        requestParams.put("get", get);

        Epitech.client.post(Epitech.URL + "/susies", requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response)
            {
                callback.callBack(null);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response)
            {
                SusiePlanning planning = new SusiePlanning();

                for (int i = 0; i < response.length(); i++) {
                    try {
                        planning.items.add((SusiePlanning.SusiePlanningItem)Epitech.parseJSON(response.getJSONObject(i), SusiePlanning.SusiePlanningItem.class));
                    } catch (JSONException e) {
                        Log.e(TAG, e.toString(), e);
                    }
                }
                callback.callBack(planning);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                callback.callBack(null);
            }
        });
    }

    public static void getSusieById(String id, final EpitechApiCallback callback) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("token", Epitech.getToken());
        requestParams.put("id", id);

        Epitech.client.get(Epitech.URL + "/susie", requestParams, Epitech.getHandler(Susie.class, callback));
    }

    public static void subscribeToSusie(String id, final EpitechApiCallback callback) {

    }

    public static void unsubscribeToSuse(String id, final EpitechApiCallback callback) {

    }
}
