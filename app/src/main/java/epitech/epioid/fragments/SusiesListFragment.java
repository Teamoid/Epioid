package epitech.epioid.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import epitech.epioid.API.Epitech;
import epitech.epioid.API.EpitechApiCallback;
import epitech.epioid.API.Items.EpitechItem;
import epitech.epioid.API.Items.SusiePlanning;
import epitech.epioid.R;
import epitech.epioid.adapters.SusieListAdapter;
import epitech.epioid.interfaces.IEpiFragment;

/**
 * Created by Ganitzsh on 18/01/2015.
 */
public class SusiesListFragment extends Fragment implements IEpiFragment {

    private ListView susieList;
    private SusieListAdapter adapter;
    private Context context;

    private ProgressBar mProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.susies_view, container, false);
        susieList = (ListView) rootView.findViewById(R.id.susieListView);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.susie_view_progress);
        showProgress(true);
        Calendar now = Calendar.getInstance();
        Calendar nextWeek = Calendar.getInstance();
        nextWeek.add(Calendar.WEEK_OF_MONTH, 1);
        context = this.getActivity();
        susieList.setOnItemClickListener(new ItemClickListener());
        Epitech.getSusiesFor(new SimpleDateFormat("yyyy-MM-dd").format(now.getTime()),
                new SimpleDateFormat("yyyy-MM-dd").format(nextWeek.getTime()), new EpitechApiCallback() {
                    @Override
                    public void callBack(EpitechItem obj) {
                        try {
                            SusiePlanning susiePlanning = (SusiePlanning) obj;
                            adapter = new SusieListAdapter(context, susiePlanning.items);
                            susieList.setAdapter(adapter);
                            showProgress(false);
                        } catch (Exception e) {
                            showProgress(false);
                        }
                    }
                });
        return rootView;
    }

    public void refresh() {

    }

    private class ItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            SusieInfoFragment susieInfoFragment = new SusieInfoFragment();
            susieInfoFragment.setSusie((SusiePlanning.SusiePlanningItem) adapter.getItem(position));
            FragmentManager frgManager = ((FragmentActivity) context).getSupportFragmentManager();
            frgManager.beginTransaction().replace(R.id.content_frame, susieInfoFragment).addToBackStack(null).commit();
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
            susieList.setVisibility(show ? View.GONE : View.VISIBLE);
            susieList.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    susieList.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });
            mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressBar.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            susieList.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}
