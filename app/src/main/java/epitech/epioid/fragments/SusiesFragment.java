package epitech.epioid.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import epitech.epioid.API.Epitech;
import epitech.epioid.API.EpitechApiCallback;
import epitech.epioid.API.Items.EpitechItem;
import epitech.epioid.API.Items.SusiePlanning;
import epitech.epioid.R;
import epitech.epioid.adapters.SusieListAdapter;

/**
 * Created by Ganitzsh on 18/01/2015.
 */
public class SusiesFragment extends Fragment {

    ListView susieList;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.susies_view, container, false);
        susieList = (ListView) rootView.findViewById(R.id.susieListView);
        Calendar now = Calendar.getInstance();
        Calendar nextWeek = Calendar.getInstance();
        nextWeek.add(Calendar.WEEK_OF_MONTH, 1);
        context = this.getActivity();
        Epitech.getSusiesFor(new SimpleDateFormat("yyyy-MM-dd").format(now.getTime()),
                new SimpleDateFormat("yyyy-MM-dd").format(nextWeek.getTime()), new EpitechApiCallback() {
                    @Override
                    public void callBack(EpitechItem obj) {
                        SusiePlanning susiePlanning = (SusiePlanning) obj;
                        SusieListAdapter adapter = new SusieListAdapter(context, susiePlanning.items);
                        susieList.setAdapter(adapter);
                    }
                });
        return rootView;
    }
}
