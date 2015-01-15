package epitech.epioid.ApiTests;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.Assert;

import epitech.epioid.API.Epitech;

/**
 * Created by michelantoine on 14/01/15.
 */
public class ApiTests extends ApplicationTestCase<Application> {
    public ApiTests() {
        super(Application.class);
    }

    @SmallTest
    public void baseTests() {
        Assert.assertEquals("Should be null", null, Epitech.getToken());
    }
}
