package epitech.epioid.API;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import epitech.epioid.API.Items.Information;
import epitech.epioid.API.Items.Marks;
import epitech.epioid.API.Items.Messages;

/**
 * Created by michelantoine on 15/01/15.
 */
class InformationHelper {
    public static final String TAG = "InfosTask";

    public static void getInfos(final EpitechApiCallback callback) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("token", Epitech.getToken());

        Epitech.client.post(Epitech.URL + "/infos", requestParams, Epitech.getHandler(Information.class, callback));
    }

    public static void getMarks(final EpitechApiCallback callback) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("token", Epitech.getToken());

        Epitech.client.get(Epitech.URL + "/marks", requestParams, Epitech.getHandler(Marks.class, callback));
    }

    public static void getMessages(final EpitechApiCallback callback) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("token", Epitech.getToken());

        Epitech.client.get(Epitech.URL + "/marks", requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response)
            {
                callback.callBack(null);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response)
            {
                Messages messages = new Messages();

                for (int i = 0; i < response.length(); i++) {
                    try {
                        messages.items.add((Messages.Message)Epitech.parseJSON(response.getJSONObject(i), Messages.Message.class));
                    } catch (JSONException e) {
                        Log.e(TAG, e.toString(), e);
                    }
                }
                callback.callBack(messages);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                callback.callBack(null);
            }
        });
    }
}
