package epitech.epioid.API;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import epitech.epioid.API.Items.EpitechItem;
import epitech.epioid.API.Items.Planning;

/**
 * Created by michelantoine on 17/01/15.
 */
public class PlanningHelper extends EpitechItem {
    private static final String url = Epitech.URL + "/planning";
    private static final String TAG = "PlanningHelper";

    public static void getPlanningFor(String start, String end, final EpitechApiCallback callback) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("token", Epitech.getToken());
        requestParams.put("start", start);
        requestParams.put("end", end);

        Epitech.client.post(url, requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response)
            {
                callback.callBack(null);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response)
            {
                Planning planning = new Planning();

                for (int i = 0; i < response.length(); i++) {
                    try {
                        planning.items.add((Planning.PlanningItem)Epitech.parseJSON(response.getJSONObject(i), Planning.PlanningItem.class));
                    } catch (JSONException e) {
                        Log.e(TAG, e.toString(), e);
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                callback.callBack(null);
            }
        });
    }
}
