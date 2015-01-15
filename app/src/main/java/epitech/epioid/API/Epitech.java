package epitech.epioid.API;

import com.loopj.android.http.AsyncHttpClient;

/**
 * Created by michelantoine on 14/01/15.
 */
public class Epitech {
    public static final String URL = "https://epitech-api.herokuapp.com";

    private static String token = null;
    public static String getToken() {
        return token;
    }
    public static void setToken(String token) {
        Epitech.token = token;
    }

    static AsyncHttpClient client = new AsyncHttpClient();

    public static void login(String login, String password, EpitechApiCallback callback) {
        new LoginTask().execute(login, password, callback);
    }

    public static void getInfos(EpitechApiCallback callback) {
        new InfosTask().execute(callback);
    }
}