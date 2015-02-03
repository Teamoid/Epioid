package epitech.epioid.API;

import com.loopj.android.http.RequestParams;

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
        RequestParams requestParams = Epitech.getBaseRequestParams();
        requestParams.put("start", start);
        requestParams.put("end", end);
        requestParams.put("get", get);

        Epitech.client.post(Epitech.URL + "/susies", requestParams, Epitech.getArrayHandler(SusiePlanning.class, SusiePlanning.SusiePlanningItem.class, callback));
    }

    public static void getSusieById(String id, final EpitechApiCallback callback) {
        RequestParams requestParams = Epitech.getBaseRequestParams();
        requestParams.put("id", id);

        Epitech.client.get(Epitech.URL + "/susie", requestParams, Epitech.getHandler(Susie.class, callback));
    }

    public static void subscribeToSusie(String id, String calendar_id, final EpitechApiCallback callback) {
        RequestParams requestParams = Epitech.getBaseRequestParams();
        requestParams.put("id", id);
        requestParams.put("calendar_id", calendar_id);
    }

    public static void unsubscribeToSuse(String id, final EpitechApiCallback callback) {

    }
}
