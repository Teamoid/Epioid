package epitech.epioid.fragments;

import epitech.epioid.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ganitzsh on 18/01/2015.
 */
public class HomeFragment extends Fragment {

    private TextView    tvLogin;
    private TextView    tvFullname;
    private TextView    tvGpa;
    private TextView    tvSemester;
    private TextView    tvCredit;
    private TextView    tvPromo;
    private TextView    tvHistory;
    private ImageView   ivPhoto;

    public HomeFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_view, container, false);
    }
}
