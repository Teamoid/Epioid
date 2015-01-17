package epitech.epioid;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import epitech.epioid.API.Epitech;
import epitech.epioid.API.EpitechApiCallback;
import epitech.epioid.API.Items.EpitechItem;
import epitech.epioid.API.Items.Information;
import epitech.epioid.API.Items.Planning;
import epitech.epioid.API.Items.SusiePlanning;

public class MainActivity extends ActionBarActivity {

    private final String TAG = "MainActivity";
    private Information information = new Information();
    private Planning planning = new Planning();
    private SusiePlanning susiePlanning = new SusiePlanning();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Epitech.getInfos(new EpitechApiCallback() {
            @Override
            public void callBack(EpitechItem obj) {
                try {
                    information = (Information) obj;
                    Log.v(TAG, "Filled informations");
                } catch (Exception e) {
                    Log.e(TAG, e.toString(), e);
                }
            }
        });
        Epitech.getSusiesFor("2015-01-17", "2015-01-25", new EpitechApiCallback() {
            @Override
            public void callBack(EpitechItem obj) {
                try {
                    susiePlanning = (SusiePlanning) obj;
                    Log.v(TAG, "Created planning");
                } catch (Exception e) {
                    Log.e(TAG, e.toString(), e);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
