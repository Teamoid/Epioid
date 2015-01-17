package epitech.epioid.API;

import com.loopj.android.http.RequestParams;

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

        Epitech.client.post(url, requestParams, Epitech.getArrayHandler(Planning.class, Planning.PlanningItem.class, callback));
    }
}
