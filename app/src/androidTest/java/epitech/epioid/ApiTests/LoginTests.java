package epitech.epioid.ApiTests;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.LargeTest;

import junit.framework.Assert;

import epitech.epioid.API.Epitech;

/**
 * Created by michelantoine on 14/01/15.
 */
public class LoginTests extends ApplicationTestCase<Application> {
    public LoginTests() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @LargeTest
    public void testShouldConnect() {
        Epitech.login("antoin_m", "");

        Assert.assertNotNull(Epitech.getToken());
    }

    @LargeTest
    public void testShouldNotConnect() {
        Epitech.login("antoin_m", "lol");

        Assert.assertNull(Epitech.getToken());
    }
}
