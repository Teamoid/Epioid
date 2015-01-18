package epitech.epioid.API;

import com.loopj.android.http.RequestParams;

import epitech.epioid.API.Items.Information;
import epitech.epioid.API.Items.Marks;
import epitech.epioid.API.Items.Messages;

/**
 * Created by michelantoine on 15/01/15.
 */
class InformationHelper {
    public static final String TAG = "InfosTask";

    public static void getInfos(final EpitechApiCallback callback) {
        RequestParams requestParams = Epitech.getBaseRequestParams();

        Epitech.client.post(Epitech.URL + "/infos", requestParams, Epitech.getHandler(Information.class, callback));
    }

    public static void getMarks(final EpitechApiCallback callback) {
        RequestParams requestParams = Epitech.getBaseRequestParams();

        Epitech.client.get(Epitech.URL + "/marks", requestParams, Epitech.getArrayHandler(Marks.class, Marks.Mark.class, callback));
    }

    public static void getMessages(final EpitechApiCallback callback) {
        RequestParams requestParams = Epitech.getBaseRequestParams();

        Epitech.client.get(Epitech.URL + "/marks", requestParams, Epitech.getArrayHandler(Messages.class, Messages.Message.class, callback));
    }
}
