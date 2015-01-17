package epitech.epioid.API;

import com.loopj.android.http.RequestParams;

import epitech.epioid.API.Items.Information;

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
}
