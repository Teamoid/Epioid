package epitech.epioid.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import epitech.epioid.API.Epitech;
import epitech.epioid.API.EpitechApiCallback;
import epitech.epioid.API.Items.EpitechItem;
import epitech.epioid.API.Items.Information;
import epitech.epioid.API.Items.SusiePlanning;
import epitech.epioid.R;
import epitech.epioid.fragments.HomeFragment;
import epitech.epioid.fragments.SusiesFragment;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends ActionBarActivity {

    private final String TAG = "MainActivity";
    private Information information = new Information();

    private HomeFragment    mHomeFragment;
    private View            mHomeFragmentView;

    private SusiesFragment  susiesFragment;
    private View            susiesFragmentView;

    private View            mProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        susiesFragmentView = findViewById(R.id.susie_linear_layout);
        susiesFragment = (SusiesFragment) getSupportFragmentManager().findFragmentById(R.id.susies_fragment_view);

        mProgressView = findViewById(R.id.main_activity_login_progress);
        mHomeFragmentView = findViewById(R.id.home_view_main_linear_layout);
        mHomeFragment = (HomeFragment)
                getSupportFragmentManager().findFragmentById(R.id.home_fragment_view);
        mHomeFragment.setTv_login((TextView) findViewById(R.id.home_view_login));
        mHomeFragment.setTv_fullname((TextView) findViewById(R.id.home_view_student_full_name));
        mHomeFragment.setTv_promo((TextView) findViewById(R.id.home_view_student_promo));
        mHomeFragment.setTv_semester((TextView) findViewById(R.id.home_view_student_full_semester));
        mHomeFragment.setIv_image((ImageView) findViewById(R.id.home_view_student_photo));
        mHomeFragment.setTv_history((TextView) findViewById(R.id.home_view_history));
        showProgress(true);
        Epitech.getInfos(new EpitechApiCallback() {
            @Override
            public void callBack(EpitechItem obj) {
                try {
                    information = (Information) obj;
                    Log.v(TAG, "Image location: " + information.infos.picture);

                    mHomeFragment.getTv_login().setText(information.infos.login);
                    mHomeFragment.getTv_fullname().setText(information.infos.firstname + " " + information.infos.lastname);
                    mHomeFragment.getTv_promo().setText(information.infos.promo);
                    mHomeFragment.getTv_semester().setText(information.infos.semester);
                    mHomeFragment.getTv_history().setText(Html.fromHtml(information.history.get(0).title.replaceFirst("=\"/", "=\"https://intra.epitech.eu/")));
                    mHomeFragment.getTv_history().setMovementMethod(LinkMovementMethod.getInstance());
                    Picasso.with(getApplicationContext()).load("https://cdn.local.epitech.eu/userprofil/profilview/" + information.infos.login + ".jpg").into(mHomeFragment.getIv_image());
                    showProgress(false);
                } catch (Exception e) {
                    Log.e(TAG, e.toString(), e);
                    showProgress(false);
                }
            }
        });
        Calendar cal = Calendar.getInstance();
        Log.d("DATE NOW: ", new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
        cal.add(Calendar.WEEK_OF_MONTH, 1);
        Log.d("DATE NEXT WEEK: ", new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
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

    public String extractLink(String rawStringContainingHTML)
    {
        Pattern pattern = Pattern.compile("href=\"(.*?)\"");
        Matcher matcher = pattern.matcher(rawStringContainingHTML);
        while (matcher.find()) {
            Log.v(TAG, "Group: " + matcher.group(1));
            if (matcher.group(1) != null && matcher.group(1).startsWith("/")) {
                return (matcher.group(1));
            }
        }
        return (null);
    }

    public String replaceLink(String rawString, String link)
    {
        Pattern pattern = Pattern.compile("<a.*<\\/a>");
        Matcher matcher = pattern.matcher(rawString);
        return (matcher.replaceAll(link));
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mHomeFragmentView.setVisibility(show ? View.GONE : View.VISIBLE);
            mHomeFragmentView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mHomeFragmentView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mHomeFragmentView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}
