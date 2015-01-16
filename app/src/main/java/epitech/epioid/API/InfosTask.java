package epitech.epioid.API;

import com.loopj.android.http.RequestParams;
import epitech.epioid.API.Items.Information;

/**
 * Created by michelantoine on 15/01/15.
 */
class InfosTask {
    public static final String TAG = "InfosTask";

    private static final String url = Epitech.URL + "/infos";

    public void execute(final EpitechApiCallback callback) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("token", Epitech.getToken());

        Epitech.client.post(url, requestParams, Epitech.getHandler(Information.class, callback));
    }
}
