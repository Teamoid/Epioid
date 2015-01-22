package epitech.epioid.fragments;

import epitech.epioid.API.Epitech;
import epitech.epioid.API.EpitechApiCallback;
import epitech.epioid.API.Items.EpitechItem;
import epitech.epioid.API.Items.Information;
import epitech.epioid.R;
import epitech.epioid.interfaces.IEpiFragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Ganitzsh on 18/01/2015.
 */
public class HomeFragment extends Fragment implements IEpiFragment {

    private Context mContext;
    private Information information = new Information();

    private TextView    tvLogin;
    private TextView    tvFullname;
    private TextView    tvGpa;
    private TextView    tvSemester;
    private TextView    tvCredit;
    private TextView    tvPromo;
    private TextView    tvHistory;
    private ImageView   ivPhoto;

    private LinearLayout    llInnerLayout;
    private ProgressBar     pbProgressBar;

    public HomeFragment() {
        super();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_view, container, false);
        mContext = this.getActivity();
        tvLogin = (TextView) rootView.findViewById(R.id.home_view_login);
        ivPhoto = (ImageView) rootView.findViewById(R.id.home_view_student_photo);
        tvSemester = (TextView) rootView.findViewById(R.id.home_view_student_full_semester);
        tvFullname = (TextView) rootView.findViewById(R.id.home_view_student_full_name);
        tvPromo = (TextView) rootView.findViewById(R.id.home_view_student_promo);
        tvHistory = (TextView) rootView.findViewById(R.id.home_view_history);
        llInnerLayout = (LinearLayout) rootView.findViewById(R.id.home_view_inner_layout);
        pbProgressBar = (ProgressBar) rootView.findViewById(R.id.home_view_progress);
        showProgress(true);
        Epitech.getInfos(new EpitechApiCallback() {
            @Override
            public void callBack(EpitechItem obj) {
                try {
                    information = (Information) obj;
                    tvPromo.setText(information.infos.promo);
                    tvLogin.setText(information.infos.login);
                    tvFullname.setText(information.infos.firstname + " " + information.infos.lastname);
                    tvSemester.setText(information.infos.semester);
                    tvHistory.setText(Html.fromHtml(information.history.get(0).title.replaceFirst("=\"/", "=\"https://intra.epitech.eu/")));
                    tvHistory.setMovementMethod(LinkMovementMethod.getInstance());
                    Picasso.with(mContext).load("https://cdn.local.epitech.eu/userprofil/profilview/" + information.infos.login + ".jpg").into(ivPhoto);
                    showProgress(false);
                } catch (Exception e) {
                    showProgress(false);
                }
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TEST", "Testing da resume");
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
            llInnerLayout.setVisibility(show ? View.GONE : View.VISIBLE);
            llInnerLayout.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    llInnerLayout.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });
            pbProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            pbProgressBar.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    pbProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            pbProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            llInnerLayout.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void refresh() {

    }
}
