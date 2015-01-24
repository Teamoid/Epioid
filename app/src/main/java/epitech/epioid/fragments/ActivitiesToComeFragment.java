package epitech.epioid.fragments;

import epitech.epioid.API.Epitech;
import epitech.epioid.API.EpitechApiCallback;
import epitech.epioid.API.Items.EpitechItem;
import epitech.epioid.R;
import epitech.epioid.interfaces.IEpiFragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ganitzsh on 18/01/2015.
 */
public class ActivitiesToComeFragment extends Fragment implements IEpiFragment {

    private View mRootView;
    private Context mContext;

    private LinearLayout llInnerLayout;

    private ProgressBar pbProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.home_view, container, false);
        mContext = this.getActivity();
        pbProgressBar = (ProgressBar) mRootView.findViewById(R.id.planning_view_progress);
        llInnerLayout = (LinearLayout) mRootView.findViewById(R.id.planning_view_inner_layout);
        setStuff();
        return inflater.inflate(R.layout.activities_view, container, false);
    }

    public void setStuff() {
        showProgress(true);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Epitech.getPlanningFor(dateFormat.format(date), dateFormat.format(date), new EpitechApiCallback() {
            @Override
            public void callBack(EpitechItem obj) {
                try {
                    showProgress(false);
                } catch (Exception e) {
                    showProgress(false);
                }
            }
        });
    }

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
        setStuff();
    }
}
