package epitech.epioid.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import epitech.epioid.API.Epitech;
import epitech.epioid.API.EpitechApiCallback;
import epitech.epioid.API.Items.EpitechItem;
import epitech.epioid.API.Items.Planning;
import epitech.epioid.API.Items.SusiePlanning;
import epitech.epioid.R;
import epitech.epioid.adapters.CalendarAdapter;
import epitech.epioid.adapters.SusieListAdapter;
import epitech.epioid.interfaces.IEpiFragment;

public class CalendarFragment extends Fragment implements IEpiFragment {

    private ListView calendarList;
    private CalendarAdapter adapter;
    private Context context;
    private Calendar now;
    private TextView date;

    private ProgressBar mProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.calendar_view, container, false);

        calendarList = (ListView) rootView.findViewById(R.id.calendar_listview);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.calendar_view_progress);
        date = (TextView) rootView.findViewById(R.id.date_planning);
        now = Calendar.getInstance();
        context = this.getActivity();
        Button prev = (Button) rootView.findViewById(R.id.prev);
        prev.setOnClickListener(new PrevListener());
        Button next = (Button) rootView.findViewById(R.id.next);
        next.setOnClickListener(new NextListener());

        loadActivity();

        return rootView;
    }

    public void loadActivity() {
        date.setText(new SimpleDateFormat("dd/MM/yyyy").format(now.getTime()));
        showProgress(true);
        Epitech.getPlanningFor(new SimpleDateFormat("yyyy-MM-dd").format(now.getTime()),
                new SimpleDateFormat("yyyy-MM-dd").format(now.getTime()), new EpitechApiCallback() {
                    @Override
                    public void callBack(EpitechItem obj) {
                        try {
                            Planning planning = (Planning) obj;
                            adapter = new CalendarAdapter(context, planning.items);
                            calendarList.setAdapter(adapter);
                            showProgress(false);
                        } catch (Exception e) {
                            showProgress(false);
                        }
                    }
                });
    }

    private class NextListener implements View.OnClickListener {

        public void onClick(View v) {
            now.add(Calendar.DAY_OF_WEEK, 1);
            loadActivity();
        }
    }

    private class PrevListener implements View.OnClickListener {

        public void onClick(View v) {
            now.add(Calendar.DAY_OF_WEEK, -1);
            loadActivity();
        }
    }

    public void refresh() {

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
            calendarList.setVisibility(show ? View.GONE : View.VISIBLE);
            calendarList.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    calendarList.setVisibility(show ? View.GONE : View.VISIBLE);
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
            calendarList.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

}
