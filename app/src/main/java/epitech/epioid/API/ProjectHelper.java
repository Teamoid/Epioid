package epitech.epioid.API;

import com.loopj.android.http.RequestParams;

import epitech.epioid.API.Items.Project;
import epitech.epioid.API.Items.ProjectContainer;

/**
 * Created by michelantoine on 17/01/15.
 */
public class ProjectHelper {
    private static final String TAG = "ProjectHelper";

    public static void getProjects(final EpitechApiCallback callback) {
        Epitech.client.get(Epitech.URL + "/projects?token=" + Epitech.getToken(), Epitech.getArrayHandler(ProjectContainer.class, ProjectContainer.Project.class, callback));
    }

    public static void getProject(String scolaryear, String codemodule, String codeinstance, String codeacti, EpitechApiCallback callback) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("token", Epitech.getToken());
        requestParams.put("scolaryear", scolaryear);
        requestParams.put("codemodule", codemodule);
        requestParams.put("codeinstance", codeinstance);
        requestParams.put("codeacti", codeacti);

        Epitech.client.get(Epitech.URL + "/project", requestParams, Epitech.getHandler(Project.class, callback));
    }

    public static void subscribeToProject(String scolaryear, String codemodule, String codeinstance, String codeacti, EpitechApiCallback callback) {

    }

    public static void unsubscribeToProject(String scolaryear, String codemodule, String codeinstance, String codeacti, EpitechApiCallback callback) {

    }
}