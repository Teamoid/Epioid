package epitech.epioid.API;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import epitech.epioid.API.Items.Projects;

/**
 * Created by michelantoine on 17/01/15.
 */
public class ProjectHelper {
    private static final String TAG = "ProjectHelper";

    public static void getProjects(final EpitechApiCallback callback) {
        Epitech.client.get(Epitech.URL + "/projects?token=" + Epitech.getToken(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.callBack(null);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Projects projects = new Projects();

                for (int i = 0; i < response.length(); i++) {
                    try {
                        projects.items.add((Projects.Project) Epitech.parseJSON(response.getJSONObject(i), Projects.Project.class));
                    } catch (Exception e) {
                        Log.e(TAG, e.toString(), e);
                    }
                }
                callback.callBack(projects);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                callback.callBack(null);
            }
        });
    }
}
